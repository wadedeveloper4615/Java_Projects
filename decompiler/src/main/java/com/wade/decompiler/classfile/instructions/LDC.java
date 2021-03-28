package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.*;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LDC extends Instruction {
    private int index;

    public LDC(ConstantPool cp) {
        super(InstructionOpCodes.LDC, 3, cp);
    }

    public Type getType() {
        switch (this.constantPool.getConstant(index).getTag()) {
            case CONSTANT_String:
                return Type.STRING;
            case CONSTANT_Float:
                return Type.FLOAT;
            case CONSTANT_Integer:
                return Type.INTEGER;
            case CONSTANT_Class:
                return Type.CLASS;
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + index);
        }
    }

    public Object getValue() {
        ConstantPool cpg = this.constantPool;
        Constant c = cpg.getConstant(index);
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
                throw new IllegalArgumentException("Unknown or invalid constant type at " + index);
        }
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setLength(2);
        setIndex(bytes.readUnsignedByte());
        setSize();
    }

    protected void setSize() {
        if (index <= Const.MAX_BYTE) {
            super.setOpcode(InstructionOpCodes.LDC);
            super.setLength(2);
        } else {
            super.setOpcode(InstructionOpCodes.LDC_W);
            super.setLength(3);
        }
    }
}
