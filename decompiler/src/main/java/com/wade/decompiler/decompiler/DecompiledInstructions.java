package com.wade.decompiler.decompiler;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.generate.instructions.InstructionGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class DecompiledInstructions {
    private List<InstructionGen> instructions = new ArrayList<>();

    public DecompiledInstructions(Instruction[] instructions) {
        int offset = 0;
        for (Instruction instr : instructions) {
            InstructionGen instruction = InstructionGen.read(offset, instr);
            this.instructions.add(instruction);
            offset += instr.getLength();
        }
    }

    public List<InstructionGen> getInstructions() {
        return instructions;
    }
}
