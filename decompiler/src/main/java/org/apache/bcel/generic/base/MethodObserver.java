package org.apache.bcel.generic.base;

import org.apache.bcel.generic.gen.MethodGen;

public interface MethodObserver {
    void notify(MethodGen method);
}
