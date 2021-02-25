package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Signature extends Attribute {
    private int signatureIndex;

    public Signature(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, input.readUnsignedShort(), constantPool);
    }

    public Signature(int nameIndex, int length, int signatureIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SIGNATURE, nameIndex, length, constantPool);
        this.signatureIndex = signatureIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Signature other = (Signature) obj;
        if (signatureIndex != other.signatureIndex)
            return false;
        return true;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + signatureIndex;
        return result;
    }

    @Override
    public String toString() {
        return "Signature [signatureIndex=" + signatureIndex + "]";
    }
}
