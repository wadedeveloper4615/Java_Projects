package com.wade.app;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.wade.app.classfile.JavaClass;
import com.wade.app.constantpool.ConstantPool;

public class ClassParser {
    private static final int BUFSIZE = 8192;
    private String fileName;
    private Version version;
    private ConstantPool constantPool;

    public ClassParser(String fileName) {
        this.fileName = fileName;
    }

    public JavaClass parse() throws FileNotFoundException, IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName), BUFSIZE))) {
            readID(in);
            readVersion(in);
            readConstantPool(in);
        }
        return new JavaClass(version, constantPool);
    }

    private void readConstantPool(DataInputStream in) throws IOException {
        constantPool = new ConstantPool(in);
    }

    private void readID(DataInputStream in) throws IOException {
        if (in.readInt() != Const.JVM_CLASSFILE_MAGIC) {
            throw new ClassFormatException(fileName + " is not a Java .class file");
        }
    }

    private void readVersion(DataInputStream in) throws IOException {
        version = Version.read(in);
    }
}
