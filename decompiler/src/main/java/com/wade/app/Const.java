package com.wade.app;

import com.wade.app.enums.ClassFileConstants;

public class Const {
    public static final int JVM_CLASSFILE_MAGIC = 0xCAFEBABE;

    public static String getConstantName(ClassFileConstants tag) {
        return tag.getName();
    }
}
