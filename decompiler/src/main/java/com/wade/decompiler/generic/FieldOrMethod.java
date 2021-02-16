package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.ConstantCP;
import com.wade.decompiler.classfile.ConstantNameAndType;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public abstract class FieldOrMethod extends CPInstruction implements LoadClass {
    public FieldOrMethod() {
    }

    protected FieldOrMethod(final short opcode, final int index) {
        super(opcode, index);
    }

    @Deprecated
    public String getClassName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        final String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        if (className.startsWith("[")) {
            // Turn array classes into java.lang.Object.
            return "java.lang.Object";
        }
        return className.replace('/', '.');
    }

    @Deprecated
    public ObjectType getClassType(final ConstantPoolGen cpg) {
        return ObjectType.getInstance(getClassName(cpg));
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        final ReferenceType rt = getReferenceType(cpg);
        if (rt instanceof ObjectType) {
            return (ObjectType) rt;
        }
        throw new ClassGenException(rt.getClass().getCanonicalName() + " " + rt.getSignature() + " does not represent an ObjectType");
    }

    public String getName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        final ConstantNameAndType cnat = (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
        return ((ConstantUtf8) cp.getConstant(cnat.getNameIndex())).getBytes();
    }

    public ReferenceType getReferenceType(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        if (className.startsWith("[")) {
            return (ArrayType) Type.getType(className);
        }
        className = className.replace('/', '.');
        return ObjectType.getInstance(className);
    }

    public String getSignature(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        final ConstantNameAndType cnat = (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
        return ((ConstantUtf8) cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }
}
