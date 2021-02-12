package org.apache.bcel.generic.base;

import java.io.IOException;

import org.apache.bcel.exceptions.ClassFormatException;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public interface TypedInstruction {
    Type getType(ConstantPoolGen cpg) throws ClassFormatException, IOException;
}
