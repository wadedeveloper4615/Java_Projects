package com.wade.app;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.wade.app.constantpool.ConstantPool;

public class ClassParser {
    private static final int BUFSIZE = 8192;
    // private DataInputStream dataInputStream;
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

    public ClassParser(String fileName) {
        isZip = false;
        this.fileName = fileName;
        fileOwned = true;
    }

    public JavaClass parse() throws IOException, ClassFormatException {
        InputStream istream = null;
        if (fileOwned) {
            if (isZip) {
                ZipFile zip = new ZipFile(zipFile);
                ZipEntry entry = zip.getEntry(fileName);

                if (entry == null) {
                    throw new IOException("File " + fileName + " not found");
                }

                istream = zip.getInputStream(entry);
            } else {
                istream = new FileInputStream(fileName);
            }
            try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(istream, BUFSIZE))) {
                readID(dataInputStream);
                readVersion(dataInputStream);
                readConstantPool(dataInputStream);
                readClassInfo(dataInputStream);
                readInterfaces(dataInputStream);
                readFields(dataInputStream);
                readMethods(dataInputStream);
                readAttributes(dataInputStream);
            }
        }
        return new JavaClass(classNameIndex, superclassNameIndex, fileName, major, minor, accessFlags, constantPool, interfaces, fields, methods, attributes, isZip ? JavaClass.ZIP : JavaClass.FILE);
    }

    private void readAttributes(DataInputStream dataInputStream) {
    }

    private void readClassInfo(DataInputStream dataInputStream) {
    }

    private void readConstantPool(DataInputStream dataInputStream) throws IOException, ClassFormatException {
        constantPool = new ConstantPool(dataInputStream);
    }

    private void readFields(DataInputStream dataInputStream) {
    }

    private void readID(DataInputStream dataInputStream) throws IOException, ClassFormatException {
        if (dataInputStream.readInt() != Const.JVM_CLASSFILE_MAGIC) {
            throw new ClassFormatException(fileName + " is not a Java .class file");
        }
    }

    private void readInterfaces(DataInputStream dataInputStream) {
    }

    private void readMethods(DataInputStream dataInputStream) {
    }

    private void readVersion(DataInputStream dataInputStream) throws IOException {
        minor = dataInputStream.readUnsignedShort();
        major = dataInputStream.readUnsignedShort();
    }
}
