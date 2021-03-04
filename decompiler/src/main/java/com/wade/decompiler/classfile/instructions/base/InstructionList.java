package com.wade.decompiler.classfile.instructions.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
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
public class InstructionList {
    private List<Instruction> instructions = new ArrayList<>();

    public InstructionList(byte[] code, LocalVariableTableGen localVariableTable, ConstantPool constantPool) throws IOException {
        try (ByteSequence bytes = new ByteSequence(code)) {
            while (bytes.available() > 0) {
                Instruction instruction = InstructionFactory.readInstruction(bytes, localVariableTable, constantPool);
                // System.out.println(instruction.toString());
                instructions.add(instruction);
            }
        }
        // System.out.println();
    }

    @SuppressWarnings("unused")
    public InstructionList(Select instruction) {
    }

    public Instruction[] getInstructions() {
        return instructions.toArray(new Instruction[instructions.size()]);
    }
}
