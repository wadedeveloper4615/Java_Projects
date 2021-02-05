package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;
import com.wade.app.Const;
import com.wade.app.constantpool.ConstantClass;
import com.wade.app.constantpool.ConstantNameAndType;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.exception.ClassFormatException;

public class EnclosingMethod extends Attribute {
    private int classIndex;
    private int methodIndex;

    public EnclosingMethod(final int nameIndex, final int len, final DataInputStream input, final ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    private EnclosingMethod(final int nameIndex, final int len, final int classIdx, final int methodIdx, final ConstantPool cpool) {
        super(Const.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        classIndex = classIdx;
        methodIndex = methodIdx;
    }

    public final ConstantClass getEnclosingClass() throws ClassFormatException {
        final ConstantClass c = (ConstantClass) super.getConstantPool().getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
        return c;
    }

    public final int getEnclosingClassIndex() {
        return classIndex;
    }

    public final ConstantNameAndType getEnclosingMethod() throws ClassFormatException {
        if (methodIndex == 0) {
            return null;
        }
        final ConstantNameAndType nat = (ConstantNameAndType) super.getConstantPool().getConstant(methodIndex, ClassFileConstants.CONSTANT_NameAndType);
        return nat;
    }

    public final int getEnclosingMethodIndex() {
        return methodIndex;
    }
}
