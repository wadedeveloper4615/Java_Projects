package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;

public class LocalVariable {
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

    public LocalVariable(LocalVariable localVariable) {
        this(localVariable.getStartPC(), localVariable.getLength(), localVariable.getNameIndex(), localVariable.getSignatureIndex(), localVariable.getIndex(), localVariable.getConstantPool());
        this.origIndex = localVariable.getOrigIndex();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalVariable other = (LocalVariable) obj;
        if (constantPool == null) {
            if (other.constantPool != null)
                return false;
        } else if (!constantPool.equals(other.constantPool))
            return false;
        if (index != other.index)
            return false;
        if (length != other.length)
            return false;
        if (nameIndex != other.nameIndex)
            return false;
        if (origIndex != other.origIndex)
            return false;
        if (signatureIndex != other.signatureIndex)
            return false;
        if (startPc != other.startPc)
            return false;
        return true;
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

    public int getStartPC() {
        return startPc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((constantPool == null) ? 0 : constantPool.hashCode());
        result = prime * result + index;
        result = prime * result + length;
        result = prime * result + nameIndex;
        result = prime * result + origIndex;
        result = prime * result + signatureIndex;
        result = prime * result + startPc;
        return result;
    }

    @Override
    public String toString() {
        return "LocalVariable [startPc=" + startPc + ", length=" + length + ", nameIndex=" + nameIndex + ", signatureIndex=" + signatureIndex + ", index=" + index + ", origIndex=" + origIndex + "]";
    }
}
