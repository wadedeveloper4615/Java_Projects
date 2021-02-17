package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;

@java.lang.Deprecated
public interface AttributeReader {
    Attribute createAttribute(int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool);
}
