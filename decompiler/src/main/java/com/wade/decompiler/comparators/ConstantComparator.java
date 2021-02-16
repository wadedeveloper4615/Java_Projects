package com.wade.decompiler.comparators;

import java.util.Objects;

import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.util.BCELComparator;

public class ConstantComparator implements BCELComparator {
    @Override
    public boolean equals(final Object o1, final Object o2) {
        final Constant THIS = (Constant) o1;
        final Constant THAT = (Constant) o2;
        return Objects.equals(THIS.toString(), THAT.toString());
    }

    @Override
    public int hashCode(final Object o) {
        final Constant THIS = (Constant) o;
        return THIS.toString().hashCode();
    }
}
