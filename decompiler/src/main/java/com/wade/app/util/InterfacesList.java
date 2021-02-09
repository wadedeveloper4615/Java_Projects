package com.wade.app.util;

import java.util.Arrays;

import com.wade.app.classfile.ClassFileName;

public class InterfacesList {
    private ClassFileName[] interfaces;

    public InterfacesList(ClassFileName[] interfaces) {
        this.interfaces = interfaces;
    }

    public ClassFileName[] getInterfaces() {
        return interfaces;
    }

    @Override
    public String toString() {
        return Arrays.toString(interfaces);
    }
}
