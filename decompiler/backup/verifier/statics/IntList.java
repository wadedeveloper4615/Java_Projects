
package org.apache.bcel.verifier.statics;

import java.util.ArrayList;
import java.util.List;

public class IntList {

    private final List<Integer> theList;

    IntList() {
        theList = new ArrayList<>();
    }

    void add(final int i) {
        theList.add(Integer.valueOf(i));
    }

    boolean contains(final int i) {
        final Integer[] ints = new Integer[theList.size()];
        theList.toArray(ints);
        for (final Integer k : ints) {
            if (i == k.intValue()) {
                return true;
            }
        }
        return false;
    }
}
