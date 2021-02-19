package com.wade.decompiler.generic.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generic.Select;
import com.wade.decompiler.util.ByteSequence;

public class InstructionList {
    private List<Instruction> instructions = new ArrayList<>();

    public InstructionList(byte[] code, ConstantPool constantPool) throws IOException {
        try (ByteSequence bytes = new ByteSequence(code)) {
            while (bytes.available() > 0) {
                Instruction instruction = Instruction.readInstruction(bytes, constantPool);
                System.out.println(instruction.toString());
                instructions.add(instruction);
            }
        }
        System.out.println();
    }

    public InstructionList(Select instruction) {
    }

    public Instruction[] getInstructions() {
        return instructions.toArray(new Instruction[instructions.size()]);
    }
}
