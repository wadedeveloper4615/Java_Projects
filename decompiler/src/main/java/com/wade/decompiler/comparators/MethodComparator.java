package com.wade.decompiler.comparators;

import java.util.Objects;

import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.util.BCELComparator;

public class MethodComparator implements BCELComparator {
    @Override
    public boolean equals(Object o1, Object o2) {
        Method THIS = (Method) o1;
        Method THAT = (Method) o2;
        return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
    }

    @Override
    public int hashCode(Object o) {
        Method THIS = (Method) o;
        return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
    }
}
