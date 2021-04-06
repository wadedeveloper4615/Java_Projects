package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
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
public class ILOAD extends Instruction {
    private int index;
    @ToString.Exclude
    private LocalVariableTableGen localVariableTable;

    public ILOAD(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.ILOAD_0.add(n), 1, cp);
        this.index = n;
        this.localVariableTable = localVariableTable;
    }

    public ILOAD(LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LLOAD, 1, cp);
        this.localVariableTable = localVariableTable;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            if (((opcode.getOpcode() >= InstructionOpCodes.ILOAD.getOpcode()) && (opcode.getOpcode() <= InstructionOpCodes.ALOAD.getOpcode())) || ((opcode.getOpcode() >= InstructionOpCodes.ISTORE.getOpcode()) && (opcode.getOpcode() <= InstructionOpCodes.ASTORE.getOpcode()))) {
                index = bytes.readUnsignedByte();
                super.setLength(2);
            } else if (opcode.getOpcode() <= InstructionOpCodes.ALOAD_3.getOpcode()) {
                index = (opcode.getOpcode() - InstructionOpCodes.ILOAD_0.getOpcode()) % 4;
                super.setLength(1);
            } else {
                index = (opcode.getOpcode() - InstructionOpCodes.ISTORE_0.getOpcode()) % 4;
                super.setLength(1);
            }
        }
    }
}
