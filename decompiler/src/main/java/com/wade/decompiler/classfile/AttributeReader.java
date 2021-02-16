package com.wade.decompiler.classfile;

@java.lang.Deprecated
public interface AttributeReader {
    Attribute createAttribute(int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool);
}
