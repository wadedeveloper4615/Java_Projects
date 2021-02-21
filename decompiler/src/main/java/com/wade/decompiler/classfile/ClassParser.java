package com.wade.decompiler.classfile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.Version;

public class ClassParser {
    private static int BUFSIZE = 8192;
    private DataInputStream dataInputStream;
    private boolean fileOwned;
    private String fileName;
    private String zipFile;
    private int classNameIndex;
    private int superclassNameIndex;
    private ClassAccessFlagsList accessFlags;
    private int[] interfaces;
    private ConstantPool constantPool;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private boolean isZip;
    private Version version;

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

    public ClassParser(String zipFile, String fileName) {
        isZip = true;
        fileOwned = true;
        this.zipFile = zipFile;
        this.fileName = fileName;
    }

    public JavaClass parse() throws IOException, ClassFormatException {
        ZipFile zip = null;
        try {
            if (fileOwned) {
                if (isZip) {
                    zip = new ZipFile(zipFile);
                    ZipEntry entry = zip.getEntry(fileName);
                    if (entry == null) {
                        throw new IOException("File " + fileName + " not found");
                    }
                    dataInputStream = new DataInputStream(new BufferedInputStream(zip.getInputStream(entry), BUFSIZE));
                } else {
                    dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName), BUFSIZE));
                }
            }
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
        return new JavaClass(classNameIndex, superclassNameIndex, fileName, version, accessFlags, constantPool, interfaces, fields, methods, attributes);
    }

    private void readAttributes() throws IOException, ClassFormatException {
        int attributes_count = dataInputStream.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(dataInputStream, constantPool);
        }
    }

    private void readClassInfo() throws IOException, ClassFormatException {
        accessFlags = new ClassAccessFlagsList(dataInputStream);
        if (accessFlags.isInterface()) {
            accessFlags.addFlag(ClassAccessFlags.ACC_ABSTRACT);
        }
        if (accessFlags.isFinalAndAbstract()) {
            throw new ClassFormatException("Class " + fileName + " can't be both final and abstract");
        }
        classNameIndex = dataInputStream.readUnsignedShort();
        superclassNameIndex = dataInputStream.readUnsignedShort();
    }

    private void readConstantPool() throws IOException, ClassFormatException {
        constantPool = new ConstantPool(dataInputStream);
    }

    private void readFields() throws IOException, ClassFormatException {
        int fields_count = dataInputStream.readUnsignedShort();
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

    private void readInterfaces() throws IOException, ClassFormatException {
        int interfaces_count = dataInputStream.readUnsignedShort();
        interfaces = new int[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = dataInputStream.readUnsignedShort();
        }
    }

    private void readMethods() throws IOException, ClassFormatException {
        int methods_count = dataInputStream.readUnsignedShort();
        methods = new Method[methods_count];
        for (int i = 0; i < methods_count; i++) {
            methods[i] = new Method(dataInputStream, constantPool);
        }
    }

    private void readVersion() throws IOException, ClassFormatException {
        version = Version.read(dataInputStream.readUnsignedShort(), dataInputStream.readUnsignedShort());
    }
}
