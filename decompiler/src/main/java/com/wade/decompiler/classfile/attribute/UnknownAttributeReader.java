package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;

public interface UnknownAttributeReader {
    Attribute createAttribute(int name_index, int length, java.io.DataInput file, ConstantPool constant_pool);
}
