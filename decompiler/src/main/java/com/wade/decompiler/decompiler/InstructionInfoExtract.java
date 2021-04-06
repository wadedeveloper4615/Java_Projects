package com.wade.decompiler.decompiler;

import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.generate.instructions.InstructionGen;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InstructionInfoExtract {
    private List<InstructionGen> instructionsExtracted = new ArrayList<>();

    public InstructionInfoExtract(Instruction[] instructions) {
        int offset = 0;
        for (Instruction instr : instructions) {
            InstructionGen instruction = InstructionGen.read(offset, instr);
            this.instructionsExtracted.add(instruction);
            offset += instr.getLength();
        }
    }
}
