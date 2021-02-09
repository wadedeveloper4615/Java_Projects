package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class Signature extends Attribute {
    private int signatureIndex;

    public Signature(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public Signature(int name_index, int length, int signatureIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_SIGNATURE, name_index, length, constant_pool);
        this.signatureIndex = signatureIndex;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    @Override
    public String toString() {
        return "Signature [signatureIndex=" + signatureIndex + "]";
    }
}
