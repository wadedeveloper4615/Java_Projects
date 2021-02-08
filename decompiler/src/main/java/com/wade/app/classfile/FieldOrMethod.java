package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;

public class FieldOrMethod {
    private  int accessFlags;
    private  int nameIndex;
    private  int signatureIndex;
    private Attribute[] attributes;
    private  ConstantPool constantPool;
    private int attributesCount;

    public FieldOrMethod(DataInputStream in, ConstantPool constantPool) throws IOException {
        this(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), null, constantPool);
        this.attributesCount = in.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.readAttribute(in, constantPool);
        }
    }

    protected FieldOrMethod(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.attributes = attributes;
        this.constantPool = constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }
}
