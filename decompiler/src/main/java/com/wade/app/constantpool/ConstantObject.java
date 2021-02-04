package com.wade.app.constantpool;

import com.wade.app.exception.ClassFormatException;

public interface ConstantObject {
    Object getConstantValue(ConstantPool cp) throws ClassFormatException;
}
