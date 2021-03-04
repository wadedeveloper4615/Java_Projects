package com.wade.decompiler.decompiler;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.instructions.base.Instruction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class DecompiledInstructions {
    private List<String> instructions = new ArrayList<>();

    @SuppressWarnings("unused")
    public DecompiledInstructions(Instruction[] instructions) {
    }

    public List<String> getInstructions() {
        return instructions;
    }
}
