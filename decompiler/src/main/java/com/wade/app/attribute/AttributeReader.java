package com.wade.app.attribute;

import com.wade.app.constantpool.ConstantPool;

public interface AttributeReader {
    Attribute createAttribute(int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool);
}
