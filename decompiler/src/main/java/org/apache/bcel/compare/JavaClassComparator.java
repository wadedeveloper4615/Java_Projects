package org.apache.bcel.compare;

import java.util.Objects;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.BCELComparator;

public class JavaClassComparator implements BCELComparator {
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
