package com.wade.decompiler.classfile;

public interface UnknownAttributeReader {
    Attribute createAttribute(int name_index, int length, java.io.DataInput file, ConstantPool constant_pool);
}