package com.wade.decompiler.classfile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;

public abstract class FieldOrMethod {
    protected Attribute[] attributes;
    protected int nameIndex;
    protected int signatureIndex;
    protected ConstantPool constantPool;
    protected int accessFlags;

    protected FieldOrMethod(DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool);
        int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
        }
    }

    protected FieldOrMethod(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.constantPool = constantPool;
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldOrMethod other = (FieldOrMethod) obj;
        if (accessFlags != other.accessFlags)
            return false;
        if (!Arrays.equals(attributes, other.attributes))
            return false;
        if (constantPool == null) {
            if (other.constantPool != null)
                return false;
        } else if (!constantPool.equals(other.constantPool))
            return false;
        if (nameIndex != other.nameIndex)
            return false;
        if (signatureIndex != other.signatureIndex)
            return false;
        return true;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accessFlags;
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + ((constantPool == null) ? 0 : constantPool.hashCode());
        result = prime * result + nameIndex;
        result = prime * result + signatureIndex;
        return result;
    }
}
