package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantInvokeDynamic;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class INVOKEDYNAMIC extends Instruction {
    private int index;
    private Type type;

    public INVOKEDYNAMIC(ConstantPool cp) {
        super(InstructionOpCodes.INVOKEDYNAMIC, 3, cp);
        type = new ObjectType(Object.class.getName());
    }

    public String getClassName(ConstantPool cp) {
        ConstantInvokeDynamic cid = (ConstantInvokeDynamic) cp.getConstant(index, ClassFileConstants.CONSTANT_InvokeDynamic);
        return ((ConstantNameAndType) cp.getConstant(cid.getNameAndTypeIndex())).getName(cp);
    }

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setLength(5);
        bytes.readByte();
        bytes.readByte();
    }
}
