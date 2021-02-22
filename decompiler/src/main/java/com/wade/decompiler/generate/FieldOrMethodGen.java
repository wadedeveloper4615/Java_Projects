package com.wade.decompiler.generate;

import java.util.Arrays;

import com.wade.decompiler.classfile.FieldOrMethod;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

public class FieldOrMethodGen {
    protected String name;
    protected String signature;
    protected ClassAccessFlagsList accessFlags;
    protected Attribute[] attributes;

    public FieldOrMethodGen(FieldOrMethod value, ConstantPool constantPool) {
        this.name = ((ConstantUtf8) constantPool.getConstant(value.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(value.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.accessFlags = new ClassAccessFlagsList(value.getAccessFlags());
        this.attributes = value.getAttributes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldOrMethodGen other = (FieldOrMethodGen) obj;
        if (accessFlags == null) {
            if (other.accessFlags != null)
                return false;
        } else if (!accessFlags.equals(other.accessFlags))
            return false;
        if (!Arrays.equals(attributes, other.attributes))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (signature == null) {
            if (other.signature != null)
                return false;
        } else if (!signature.equals(other.signature))
            return false;
        return true;
    }

    public ClassAccessFlagsList getAccessFlags() {
        return accessFlags;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessFlags == null) ? 0 : accessFlags.hashCode());
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((signature == null) ? 0 : signature.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FieldOrMethodGen [name=" + name + ", signature=" + signature + ", accessFlags=" + accessFlags + ", attributes=" + Arrays.toString(attributes) + "]";
    }
}
