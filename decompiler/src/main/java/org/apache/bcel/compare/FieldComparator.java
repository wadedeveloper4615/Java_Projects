package org.apache.bcel.compare;

import java.util.Objects;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.util.BCELComparator;

public class FieldComparator implements BCELComparator {
    @Override
    public boolean equals(final Object o1, final Object o2) {
        final Field THIS = (Field) o1;
        final Field THAT = (Field) o2;
        return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
    }

    @Override
    public int hashCode(final Object o) {
        final Field THIS = (Field) o;
        return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
    }
}
