package com.wade.app.classfile;

import com.wade.app.Version;
import com.wade.app.constantpool.ConstantPool;

public class JavaClass {
    private Version version;
    private ConstantPool constantPool;

    public JavaClass(Version version, ConstantPool constantPool) {
        this.version = version;
        this.constantPool = constantPool;
    }

    @Override
    public String toString() {
        return "JavaClass [version=" + version + ", constantPool=\n" + constantPool + "]";
    }
}
