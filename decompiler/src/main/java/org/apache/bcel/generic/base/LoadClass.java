
package org.apache.bcel.generic.base;

import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public interface LoadClass {

    ObjectType getLoadClassType(ConstantPoolGen cpg);

    Type getType(ConstantPoolGen cpg);
}
