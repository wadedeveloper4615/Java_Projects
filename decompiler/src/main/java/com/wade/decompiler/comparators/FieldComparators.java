package com.wade.decompiler.comparators;

import java.util.Objects;

import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.util.BCELComparator;

public class FieldComparators implements BCELComparator {
    @Override
    public boolean equals(Object o1, Object o2) {
        Field THIS = (Field) o1;
        Field THAT = (Field) o2;
        return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
    }

    @Override
    public int hashCode(Object o) {
        Field THIS = (Field) o;
        return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
    }
}
