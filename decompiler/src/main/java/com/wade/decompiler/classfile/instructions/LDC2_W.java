package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantDouble;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
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
public class LDC2_W extends Instruction {
    private int index;
    @ToString.Exclude
    private LocalVariableTableGen localVariableTable;

    public LDC2_W(LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LDC2_W, 1, cp);
        this.localVariableTable = localVariableTable;
    }

    public Type getType() {
        Constant c = this.constantPool.getConstant(index);
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Type.LONG;
            case CONSTANT_Double:
                return Type.DOUBLE;
            default:
                throw new IllegalArgumentException("Unknown constant type " + super.getOpcode());
        }
    }

    public Number getValue() {
        Constant c = constantPool.getConstant(index);
        if (c == null) return null;
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Long.valueOf(((ConstantLong) c).getBytes());
            case CONSTANT_Double:
                return Double.valueOf(((ConstantDouble) c).getBytes());
            default:
                throw new IllegalArgumentException("Unknown or invalid constant type at " + index);
        }
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setLength(3);
    }
}
