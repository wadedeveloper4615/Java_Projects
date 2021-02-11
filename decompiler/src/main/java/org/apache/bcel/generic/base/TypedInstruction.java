
package org.apache.bcel.generic.base;

import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public interface TypedInstruction {

    Type getType(ConstantPoolGen cpg);
}
