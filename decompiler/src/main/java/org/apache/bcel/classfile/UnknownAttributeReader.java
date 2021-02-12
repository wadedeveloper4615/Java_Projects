package org.apache.bcel.classfile;

import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.ConstantPool;

public interface UnknownAttributeReader {
    Attribute createAttribute(int name_index, int length, java.io.DataInput file, ConstantPool constant_pool);
}
