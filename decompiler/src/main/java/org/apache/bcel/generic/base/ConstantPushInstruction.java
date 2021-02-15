package org.apache.bcel.generic.base;

//import org.apache.bcel.generic.ICONST;
//import org.apache.bcel.generic.SIPUSH;
public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {
    Number getValue();
}
