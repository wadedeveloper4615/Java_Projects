
package org.apache.bcel.generic.base;

import org.apache.bcel.generic.FieldGen;

public interface FieldObserver {

    void notify(FieldGen field);
}
