package com.wade.decompiler.decompiler;

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
public class DecompileInstructions {
    List<String> instructionDecompiled = new ArrayList<>();

    public DecompileInstructions(List<InstructionGen> instructionExtracted) {
        ExpressionStack stack = new ExpressionStack();
        for (InstructionGen instr : instructionExtracted) {
            String result = instr.decompile(stack);
            if (result != null) {
                this.instructionDecompiled.add(result);
            }
        }
    }
}
