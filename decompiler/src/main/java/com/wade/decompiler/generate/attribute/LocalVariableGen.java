package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class LocalVariableGen {
    private int startPc;
    private int length;
    private String name;
    private String signature;
    private int index;
    private int origIndex;

    public LocalVariableGen(LocalVariable attribute, ConstantPool constantPool) {
        this.startPc = attribute.getStartPC();
        this.length = attribute.getLength();
        this.name = ((ConstantUtf8) constantPool.getConstant(attribute.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(attribute.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.index = attribute.getIndex();
        this.origIndex = attribute.getIndex();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalVariableGen other = (LocalVariableGen) obj;
        if (index != other.index)
            return false;
        if (length != other.length)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (origIndex != other.origIndex)
            return false;
        if (signature == null) {
            if (other.signature != null)
                return false;
        } else if (!signature.equals(other.signature))
            return false;
        if (startPc != other.startPc)
            return false;
        return true;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public String getSignature() {
        return signature;
    }

    public int getStartPc() {
        return startPc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result + length;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + origIndex;
        result = prime * result + ((signature == null) ? 0 : signature.hashCode());
        result = prime * result + startPc;
        return result;
    }

    @Override
    public String toString() {
        return "LocalVariableGen [startPc=" + startPc + ", length=" + length + ", name=" + name + ", signature=" + signature + ", index=" + index + ", origIndex=" + origIndex + "]";
    }
}
