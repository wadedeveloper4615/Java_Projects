package com.wade.decompiler;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.repository.SyntheticRepository;

import nl.jqno.equalsverifier.EqualsVerifier;

public abstract class AbstractTest {
    protected static final String PACKAGE_BASE_NAME = AbstractTest.class.getPackage().getName();

    protected JavaClass getTestClass(String name) throws ClassNotFoundException {
        System.out.println(name);
        return SyntheticRepository.getInstance().loadClass(name);
    }

    public void IsEquals(Object c) {
        EqualsVerifier.forClass(c.getClass()).verify();
    }
}
