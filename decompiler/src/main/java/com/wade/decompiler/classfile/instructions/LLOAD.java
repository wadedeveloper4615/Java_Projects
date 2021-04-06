package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LLOAD extends Instruction {
    private int index;
    @ToString.Exclude
    private final LocalVariableTableGen localVariableTable;

    public LLOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LLOAD_0.add(n), 1, cp);
        this.index = n;
        this.localVariableTable = localVariableTable;
    }

    public LLOAD(LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LLOAD, 1, cp);
        this.localVariableTable = localVariableTable;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else if (opcode == InstructionOpCodes.LLOAD) {
            index = bytes.readUnsignedByte();
            super.setLength(2);
        }
    }
}
