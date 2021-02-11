
package org.apache.bcel.generic.base;

import org.apache.bcel.generic.gen.ClassGen;

public interface ClassObserver {

    void notify(ClassGen clazz);
}
