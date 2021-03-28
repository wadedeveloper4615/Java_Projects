package com.wade.decompiler.generate;

import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generate.attribute.AttributeGen;
import com.wade.decompiler.generate.attribute.CodeGen;
import com.wade.decompiler.generate.attribute.LineNumberTableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import com.wade.decompiler.generate.instructions.InstructionGen;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class MethodGen extends FieldOrMethodGen {
    private CodeGen code;
    private LineNumberTableGen lineNumberTable;
    private LocalVariableTableGen localVariableTable;
    private List<InstructionGen> instructions;

    public MethodGen(Method value, ConstantPool constantPool) throws IOException {
        super(value, constantPool);
        for (AttributeGen attr : super.getAttributes()) {
            if (attr instanceof CodeGen) {
                code = (CodeGen) attr;
                lineNumberTable = code.getLineNumberTable();
                localVariableTable = code.getLocalVariableTable();
                instructions = code.getInstructionExtracted();
            }
        }
    }
}
