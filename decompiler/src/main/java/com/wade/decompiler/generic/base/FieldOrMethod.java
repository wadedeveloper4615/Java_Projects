package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.ArrayType;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.ReferenceType;
import com.wade.decompiler.generic.type.Type;

public abstract class FieldOrMethod extends CPInstruction implements LoadClass {
    public FieldOrMethod() {
    }

    public FieldOrMethod(InstructionOpCodes opcode, ConstantPool constantPool, int index) {
        super(opcode, constantPool, index);
    }

    public String getClassName(ConstantPool cp) {
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        if (className.startsWith("[")) {
            // Turn array classes into java.lang.Object.
            return "java.lang.Object";
        }
        return className.replace('/', '.');
    }

    public ObjectType getClassType(ConstantPool cp) {
        return ObjectType.getInstance(getClassName(cp));
    }

    @Override
    public ObjectType getLoadClassType(ConstantPool cpg) {
        ReferenceType rt = getReferenceType(cpg);
        if (rt instanceof ObjectType) {
            return (ObjectType) rt;
        }
        throw new ClassGenException(rt.getClass().getCanonicalName() + " " + rt.getSignature() + " does not represent an ObjectType");
    }

    public String getName(ConstantPool cp) {
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        ConstantNameAndType cnat = (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
        return ((ConstantUtf8) cp.getConstant(cnat.getNameIndex())).getBytes();
    }

    public ReferenceType getReferenceType(ConstantPool cp) {
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        if (className.startsWith("[")) {
            return (ArrayType) Type.getType(className);
        }
        className = className.replace('/', '.');
        return ObjectType.getInstance(className);
    }

    public String getSignature(ConstantPool cp) {
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        ConstantNameAndType cnat = (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
        return ((ConstantUtf8) cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }
}
