package com.wade.decompiler;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.repository.SyntheticRepository;

public abstract class AbstractTest {
    protected JavaClass getTestClass(final String name) throws ClassNotFoundException {
        return SyntheticRepository.getInstance().loadClass(name);
    }
}
