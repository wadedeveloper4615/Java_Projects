package com.wade.decompiler.comparators;

import java.util.Objects;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.util.BCELComparator;

public class ConstantComparator implements BCELComparator {
    @Override
    public boolean equals(Object o1, Object o2) {
        Constant THIS = (Constant) o1;
        Constant THAT = (Constant) o2;
        return Objects.equals(THIS.toString(), THAT.toString());
    }

    @Override
    public int hashCode(Object o) {
        Constant THIS = (Constant) o;
        return THIS.toString().hashCode();
    }
}
