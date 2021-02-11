package org.apache.bcel.compare;

import java.util.Objects;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.BCELComparator;

public class MethdComparator implements BCELComparator {
    @Override
    public boolean equals(final Object o1, final Object o2) {
        final Method THIS = (Method) o1;
        final Method THAT = (Method) o2;
        return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
    }

    @Override
    public int hashCode(final Object o) {
        final Method THIS = (Method) o;
        return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
    }
}
