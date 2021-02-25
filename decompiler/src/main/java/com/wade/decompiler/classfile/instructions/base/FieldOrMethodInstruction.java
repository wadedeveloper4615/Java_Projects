package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantConstantPool;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.ReferenceType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class FieldOrMethodInstruction extends CPInstruction implements LoadClass {
    protected FieldOrMethodInstruction(InstructionOpCodes opcode, ConstantPool cp, int index) {
        super(opcode, cp, index);
    }

    @Override
    public ObjectType getLoadClassType() {
        final ReferenceType rt = getReferenceType(this.constantPool);
        if (rt instanceof ObjectType) {
            return (ObjectType) rt;
        }
        throw new ClassGenException(rt.getClass().getCanonicalName() + " " + rt.getSignature() + " does not represent an ObjectType");
    }

    public ReferenceType getReferenceType(ConstantPool cp) {
        final ConstantConstantPool cmr = (ConstantConstantPool) cp.getConstant(super.getIndex());
        String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        if (className.startsWith("[")) {
            return (ArrayType) Type.getType(className);
        }
        className = className.replace('/', '.');
        return new ObjectType(className);
    }
}
