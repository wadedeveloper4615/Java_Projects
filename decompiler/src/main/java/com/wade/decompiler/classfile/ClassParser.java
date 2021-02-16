package com.wade.decompiler.classfile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.wade.decompiler.Const;

public final class ClassParser {
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

    public ClassParser(final String fileName) {
        isZip = false;
        this.fileName = fileName;
        fileOwned = true;
    }

    public ClassParser(final String zipFile, final String fileName) {
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
                    final ZipEntry entry = zip.getEntry(fileName);
                    if (entry == null) {
                        throw new IOException("File " + fileName + " not found");
                    }
                    dataInputStream = new DataInputStream(new BufferedInputStream(zip.getInputStream(entry), BUFSIZE));
                } else {
                    dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName), BUFSIZE));
                }
            }
            // Check magic tag of class file
            readID();
            // Get compiler version
            readVersion();
            // Read constant pool entries
            readConstantPool();
            // Get class information
            readClassInfo();
            // Get interface information, i.e., implemented interfaces
            readInterfaces();
            // Read class fields, i.e., the variables of the class
            readFields();
            // Read class methods, i.e., the functions in the class
            readMethods();
            // Read class attributes
            readAttributes();
            // Check for unknown variables
            // Unknown[] u = Unknown.getUnknownAttributes();
            // for (int i=0; i < u.length; i++)
            // System.err.println("WARNING: " + u[i]);
            // Everything should have been read now
            // if(file.available() > 0) {
            // int bytes = file.available();
            // byte[] buf = new byte[bytes];
            // file.read(buf);
            // if(!(isZip && (buf.length == 1))) {
            // System.err.println("WARNING: Trailing garbage at end of " + fileName);
            // System.err.println(bytes + " extra bytes: " + Utility.toHexString(buf));
            // }
            // }
        } finally {
            // Read everything of interest, so close the file
            if (fileOwned) {
                try {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                } catch (final IOException ioe) {
                    // ignore close exceptions
                }
            }
            try {
                if (zip != null) {
                    zip.close();
                }
            } catch (final IOException ioe) {
                // ignore close exceptions
            }
        }
        // Return the information we have gathered in a new object
        return new JavaClass(classNameIndex, superclassNameIndex, fileName, major, minor, accessFlags, constantPool, interfaces, fields, methods, attributes, isZip ? JavaClass.ZIP : JavaClass.FILE);
    }

    private void readAttributes() throws IOException, ClassFormatException {
        final int attributes_count = dataInputStream.readUnsignedShort();
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

    private void readFields() throws IOException, ClassFormatException {
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

    private void readInterfaces() throws IOException, ClassFormatException {
        final int interfaces_count = dataInputStream.readUnsignedShort();
        interfaces = new int[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = dataInputStream.readUnsignedShort();
        }
    }

    private void readMethods() throws IOException, ClassFormatException {
        final int methods_count = dataInputStream.readUnsignedShort();
        methods = new Method[methods_count];
        for (int i = 0; i < methods_count; i++) {
            methods[i] = new Method(dataInputStream, constantPool);
        }
    }

    private void readVersion() throws IOException, ClassFormatException {
        minor = dataInputStream.readUnsignedShort();
        major = dataInputStream.readUnsignedShort();
    }
}
