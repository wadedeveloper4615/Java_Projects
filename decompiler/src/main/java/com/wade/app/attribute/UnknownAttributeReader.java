package com.wade.app.attribute;

import java.io.DataInputStream;

import com.wade.app.constantpool.ConstantPool;

public interface UnknownAttributeReader {
    Attribute createAttribute(int name_index, int length, DataInputStream file, ConstantPool constant_pool);
}
