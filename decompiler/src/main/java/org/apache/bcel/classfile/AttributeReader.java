
package org.apache.bcel.classfile;

@java.lang.Deprecated
public interface AttributeReader {

    Attribute createAttribute(int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool);
}
