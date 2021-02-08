package com.wade.app;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.wade.app.classfile.ClassFileName;
import com.wade.app.classfile.FieldVariable;
import com.wade.app.classfile.JavaClass;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassAccessFlags;
import com.wade.app.enums.Version;
import com.wade.app.util.ClassAccessFlagsList;
import com.wade.app.util.FieldsList;
import com.wade.app.util.InterfacesList;

public class ClassParser {
    private static int BUFSIZE = 8192;
    private String fileName;
    private Version version;
    private ConstantPool constantPool;
    private ClassAccessFlagsList accessFlags;
    private ClassFileName className;
    private ClassFileName superclassName;
    private InterfacesList interfaces;
    private FieldsList fields;

    public ClassParser(String fileName) {
        this.fileName = fileName;
    }

    public ClassAccessFlagsList getAccessFlags() {
        return accessFlags;
    }

    public ClassFileName getClassName() {
        return className;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public FieldsList getFields() {
        return fields;
    }

    public String getFileName() {
        return fileName;
    }

    public InterfacesList getInterfaces() {
        return interfaces;
    }

    public ClassFileName getSuperclassName() {
        return superclassName;
    }

    public Version getVersion() {
        return version;
    }

    public JavaClass parse() throws FileNotFoundException, IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName), BUFSIZE))) {
            readID(in);
            readVersion(in);
            readConstantPool(in);
            readClassInfo(in);
            readInterfaces(in);
            readFields(in);
        }
        return new JavaClass(version, constantPool, accessFlags, className, superclassName, interfaces);
    }

    private void readClassInfo(DataInputStream in) throws IOException {
        accessFlags = new ClassAccessFlagsList(ClassAccessFlags.read(in));
        className = new ClassFileName(in, constantPool);
        superclassName = new ClassFileName(in, constantPool);
    }

    private void readConstantPool(DataInputStream in) throws IOException {
        constantPool = new ConstantPool(in);
    }

    private void readFields(DataInputStream in) throws IOException {
        int fieldsCount = in.readUnsignedShort();
        FieldVariable[] fields = new FieldVariable[fieldsCount];
        for (int i = 0; i < fieldsCount; i++) {
            fields[i] = new FieldVariable(in, constantPool);
        }
        this.fields = new FieldsList(fields);

    }

    private void readID(DataInputStream in) throws IOException {
        if (in.readInt() != Const.JVM_CLASSFILE_MAGIC) {
            throw new ClassFormatException(fileName + " is not a Java .class file");
        }
    }

    private void readInterfaces(DataInputStream in) throws IOException {
        int interfaces_count = in.readUnsignedShort();
        ClassFileName[] interfaces = new ClassFileName[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = new ClassFileName(in, constantPool);
        }
        this.interfaces = new InterfacesList(interfaces);
    }

    private void readVersion(DataInputStream in) throws IOException {
        version = Version.read(in);
    }
}
