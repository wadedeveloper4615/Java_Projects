package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantInteger;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantString;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.instructions.base.CPInstruction;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.constants.ExceptionConst;
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
public class LDC extends CPInstruction {
    public LDC(int index, ConstantPool cp) {
        super(InstructionOpCodes.LDC_W, cp, index);
        setSize();
    }

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_STRING_RESOLUTION);
    }

    @Override
    public Type getType() {
        switch (this.constantPool.getConstant(super.getIndex()).getTag()) {
            case CONSTANT_String:
                return Type.STRING;
            case CONSTANT_Float:
                return Type.FLOAT;
            case CONSTANT_Integer:
                return Type.INT;
            case CONSTANT_Class:
                return Type.CLASS;
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }

    public Object getValue(ConstantPool cpg) {
        Constant c = cpg.getConstant(super.getIndex());
        switch (c.getTag()) {
            case CONSTANT_String:
                int i = ((ConstantString) c).getStringIndex();
                c = cpg.getConstant(i);
                return ((ConstantUtf8) c).getBytes();
            case CONSTANT_Float:
                return Float.valueOf(((ConstantFloat) c).getBytes());
            case CONSTANT_Integer:
                return Integer.valueOf(((ConstantInteger) c).getBytes());
            case CONSTANT_Class:
                int nameIndex = ((ConstantClass) c).getNameIndex();
                c = cpg.getConstant(nameIndex);
                return new ObjectType(((ConstantUtf8) c).getBytes());
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(2);
        super.setIndex(bytes.readUnsignedByte());
    }

    @Override
    public void setIndex(int index) {
        super.setIndex(index);
        setSize();
    }

    // Adjust to proper size
    protected void setSize() {
        if (super.getIndex() <= Const.MAX_BYTE) { // Fits in one byte?
            super.setOpcode(InstructionOpCodes.LDC);
            super.setLength(2);
        } else {
            super.setOpcode(InstructionOpCodes.LDC_W);
            super.setLength(3);
        }
    }
}
