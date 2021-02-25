package com.wade.decompiler.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.util.Utility;

public class Method extends FieldOrMethod {
    public Method(DataInputStream file, ConstantPool constantPool) throws IOException, ClassFormatException {
        super(file, constantPool);
    }

    public Method(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) {
        super(accessFlags, nameIndex, signatureIndex, attributes, constantPool);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "\n\tMethod [nameIndex=" + nameIndex + ", signatureIndex=" + signatureIndex + ", accessFlags=" + accessFlags + " attributes=" + Utility.toString(attributes) + "]";
    }
}
