package com.wade.decompiler;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.repository.SyntheticRepository;

public abstract class AbstractTest {
    protected static final String PACKAGE_BASE_NAME = AbstractTest.class.getPackage().getName();

    protected JavaClass getTestClass(String name) throws ClassNotFoundException {
        System.out.println(name);
        return SyntheticRepository.getInstance().loadClass(name);
    }
}
