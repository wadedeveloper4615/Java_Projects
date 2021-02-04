package com.wade.app;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.wade.app.attribute.Attribute;
import com.wade.app.classfile.Field;
import com.wade.app.classfile.JavaClass;
import com.wade.app.classfile.Method;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.exception.ClassFormatException;

public class ClassParser {
    private static final int BUFSIZE = 8192;
    private DataInputStream dataInputStream;
    private boolean fileOwned;
    private String fileName;
    private String zipFile;
    private int classNameIndex;
    private int superclassNameIndex;
    private Version version;
    private int accessFlags;
    private int[] interfaces;
    private ConstantPool constantPool;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private boolean isZip;

    public ClassParser(InputStream inputStream, String fileName) {
        this.fileName = fileName;
        fileOwned = false;
        String clazz = inputStream.getClass().getName();
        isZip = clazz.startsWith("java.util.zip.") || clazz.startsWith("java.util.jar.");
        if (inputStream instanceof DataInputStream) {
            this.dataInputStream = (DataInputStream) inputStream;
        } else {
            this.dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, BUFSIZE));
        }
    }

    public ClassParser(String fileName) {
        isZip = false;
        this.fileName = fileName;
        fileOwned = true;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public int getClassNameIndex() {
        return classNameIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public Field[] getFields() {
        return fields;
    }

    public String getFileName() {
        return fileName;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public Method[] getMethods() {
        return methods;
    }

    public int getSuperclassNameIndex() {
        return superclassNameIndex;
    }

    public Version getVersion() {
        return version;
    }

    public String getZipFile() {
        return zipFile;
    }

    public boolean isFileOwned() {
        return fileOwned;
    }

    public boolean isZip() {
        return isZip;
    }

    public JavaClass parse() throws IOException, ClassFormatException {
        InputStream istream = null;
        ZipFile zip = null;
        if (fileOwned) {
            try {
                if (isZip) {
                    zip = new ZipFile(zipFile);
                    ZipEntry entry = zip.getEntry(fileName);

                    if (entry == null) {
                        throw new IOException("File " + fileName + " not found");
                    }

                    istream = zip.getInputStream(entry);
                } else {
                    istream = new FileInputStream(fileName);
                }
                dataInputStream = new DataInputStream(new BufferedInputStream(istream, BUFSIZE));
                readID();
                readVersion();
                readConstantPool();
                readClassInfo();
                readInterfaces();
                readFields();
                readMethods();
                readAttributes();
            } finally {
                if (fileOwned) {
                    try {
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                    } catch (IOException ioe) {
                    }
                }
                try {
                    if (zip != null) {
                        zip.close();
                    }
                } catch (IOException ioe) {
                }
            }
        }
        return new JavaClass(classNameIndex, superclassNameIndex, fileName, version, accessFlags, constantPool, interfaces, fields, methods, attributes, isZip ? JavaClass.ZIP : JavaClass.FILE);
    }

    private void readAttributes() throws IOException {
        int attributes_count = dataInputStream.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(dataInputStream, constantPool);
        }
    }

    private void readClassInfo() throws IOException, ClassFormatException {
        accessFlags = dataInputStream.readUnsignedShort();
        if ((accessFlags & Const.ACC_INTERFACE) != 0) {
            accessFlags |= Const.ACC_ABSTRACT;
        }
        if (((accessFlags & Const.ACC_ABSTRACT) != 0) && ((accessFlags & Const.ACC_FINAL) != 0)) {
            throw new ClassFormatException("Class " + fileName + " can't be both final and abstract");
        }
        classNameIndex = dataInputStream.readUnsignedShort();
        superclassNameIndex = dataInputStream.readUnsignedShort();
    }

    private void readConstantPool() throws IOException, ClassFormatException {
        constantPool = new ConstantPool(dataInputStream);
    }

    private void readFields() throws IOException {
        final int fields_count = dataInputStream.readUnsignedShort();
        fields = new Field[fields_count];
        for (int i = 0; i < fields_count; i++) {
            fields[i] = new Field(dataInputStream, constantPool);
        }
    }

    private void readID() throws IOException, ClassFormatException {
        if (dataInputStream.readInt() != Const.JVM_CLASSFILE_MAGIC) {
            throw new ClassFormatException(fileName + " is not a Java .class file");
        }
    }

    private void readInterfaces() throws IOException {
        final int interfaces_count = dataInputStream.readUnsignedShort();
        interfaces = new int[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = dataInputStream.readUnsignedShort();
        }
    }

    private void readMethods() throws IOException {
        final int fields_count = dataInputStream.readUnsignedShort();
        fields = new Field[fields_count];
        for (int i = 0; i < fields_count; i++) {
            fields[i] = new Field(dataInputStream, constantPool);
        }
    }

    private void readVersion() throws IOException {
        version = Version.read(dataInputStream);
    }
}
