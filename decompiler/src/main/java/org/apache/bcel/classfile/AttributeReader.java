
package org.apache.bcel.classfile;

import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.ConstantPool;

@java.lang.Deprecated
public interface AttributeReader {

    Attribute createAttribute(int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool);
}
