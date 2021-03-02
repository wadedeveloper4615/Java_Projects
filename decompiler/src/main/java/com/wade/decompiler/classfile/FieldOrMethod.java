package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
public abstract class FieldOrMethod {
    Attribute[] attributes;
    protected int nameIndex;
    protected int signatureIndex;
    protected int accessFlags;

    public FieldOrMethod() {
        super();
    }

    protected FieldOrMethod(DataInput file, ConstantPool constantPool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null);
        int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constantPool);
        }
    }

    protected FieldOrMethod(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
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
        if (nameIndex != other.nameIndex)
            return false;
        if (signatureIndex != other.signatureIndex)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accessFlags;
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + nameIndex;
        result = prime * result + signatureIndex;
        return result;
    }
}
