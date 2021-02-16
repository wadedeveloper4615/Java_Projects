package com.wade.decompiler.comparators;

import java.util.Objects;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.util.BCELComparator;

public class JavClassComparator implements BCELComparator {
    @Override
    public boolean equals(Object o1, Object o2) {
        JavaClass THIS = (JavaClass) o1;
        JavaClass THAT = (JavaClass) o2;
        return Objects.equals(THIS.getClassName(), THAT.getClassName());
    }

    @Override
    public int hashCode(Object o) {
        JavaClass THIS = (JavaClass) o;
        return THIS.getClassName().hashCode();
    }
}
