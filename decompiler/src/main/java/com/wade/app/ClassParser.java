package com.wade.app;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.wade.app.attribute.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.exception.ClassFormatException;

public class ClassParser {
    private static final int BUFSIZE = 8192;
    private DataInputStream dataInputStream;
    private final boolean fileOwned;
    private final String fileName;
    private String zipFile;
    private int classNameIndex;
    private int superclassNameIndex;
    private int major; // Compiler version
    private int minor; // Compiler version
    private int accessFlags; // Access rights of parsed class
    private int[] interfaces; // Names of implemented interfaces
    private ConstantPool constantPool; // collection of constants
    private Field[] fields; // class fields, i.e., its variables
    private Method[] methods; // methods defined in the class
    private Attribute[] attributes; // attributes defined in the class
    private final boolean isZip; // Loaded from zip file

    public ClassParser(final InputStream inputStream, final String fileName) {
        this.fileName = fileName;
        fileOwned = false;
        final String clazz = inputStream.getClass().getName(); // Not a very clean solution ...
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
                    } catch (final IOException ioe) {
                    }
                }
                try {
                    if (zip != null) {
                        zip.close();
                    }
                } catch (final IOException ioe) {
                }
            }
        }
        return new JavaClass(classNameIndex, superclassNameIndex, fileName, major, minor, accessFlags, constantPool, interfaces, fields, methods, attributes, isZip ? JavaClass.ZIP : JavaClass.FILE);
    }

    private void readAttributes() {
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

    private void readMethods() {
    }

    private void readVersion() throws IOException {
        minor = dataInputStream.readUnsignedShort();
        major = dataInputStream.readUnsignedShort();
    }
}
