package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;

public  class LocalVariable {
    private int startPc;
    private int length;
    private int nameIndex;
    private int signatureIndex;
    private int index;
    private ConstantPool constantPool;
    private int origIndex;

    public LocalVariable(DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constant_pool);
    }

    public LocalVariable(int startPc, int length, int nameIndex, int signatureIndex, int index, ConstantPool constantPool) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = index;
    }

    public LocalVariable(int startPc, int length, int nameIndex, int signatureIndex, int index, ConstantPool constantPool, int origIndex) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = origIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public int getStartPc() {
        return startPc;
    }
}
