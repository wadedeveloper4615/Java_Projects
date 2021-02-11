
package org.apache.bcel.generic;

import org.apache.bcel.generic.gen.ConstantPoolGen;

public interface StackProducer {

    int produceStack(ConstantPoolGen cpg);
}
