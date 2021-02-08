package com.wade.app.classfile;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.Version;
import com.wade.app.util.ClassAccessFlagsList;
import com.wade.app.util.InterfacesList;

public class JavaClass {
    private Version version;
    private ConstantPool constantPool;
    private ClassFileName className;
    private ClassFileName superclassName;
    private ClassAccessFlagsList accessFlags;
    private InterfacesList interfaces;

    public JavaClass(Version version, ConstantPool constantPool, ClassAccessFlagsList accessFlags, ClassFileName className, ClassFileName superclassName, InterfacesList interfaces) {
        this.version = version;
        this.constantPool = constantPool;
        this.accessFlags = accessFlags;
        this.className = className;
        this.superclassName = superclassName;
        this.interfaces = interfaces;
    }

    @Override
    public String toString() {
        return "JavaClass [version=" + version + ", constantPool=" + constantPool + ", \nclassName=" + className + ", \nsuperclassName=" + superclassName + ", \naccessFlags=" + accessFlags + ", \ninterfaces=" + interfaces + "\n]";
    }
}
