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
public class FLOAD extends Instruction {
    private int index;
    @ToString.Exclude
    private LocalVariableTableGen localVariableTable;

    public FLOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.FLOAD_0.add(n), 1, cp);
        this.index = n;
        this.localVariableTable = localVariableTable;
    }

    public FLOAD(LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.FLOAD, 1, cp);
        this.localVariableTable = localVariableTable;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else if (opcode == InstructionOpCodes.FLOAD) {
            index = bytes.readUnsignedByte();
            super.setLength(2);
        }
    }
}
