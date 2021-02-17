package com.wade.decompiler.classfile.constant;

public interface ConstantObject {
    Object getConstantValue(ConstantPool cp);
}
