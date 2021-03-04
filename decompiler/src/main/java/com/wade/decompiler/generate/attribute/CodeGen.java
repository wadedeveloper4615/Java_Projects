package com.wade.decompiler.generate.attribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.CodeException;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.InstructionList;
import com.wade.decompiler.decompiler.DecompiledInstructions;
import com.wade.decompiler.generate.instructions.InstructionGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class CodeGen extends AttributeGen {
    private int maxStack;
    private int maxLocals;
    private Instruction[] instructions;
    private AttributeGen[] attributes;
    private CodeException[] codeException;
    private LineNumberTableGen lineNumberTable;
    private LocalVariableTableGen localVariableTable;
    private List<InstructionGen> decompiledInstructions = new ArrayList<>();

    public CodeGen(Code attribute, ConstantPool constantPool) throws IOException {
        super(attribute, constantPool);
        this.maxStack = attribute.getMaxStack();
        this.maxLocals = attribute.getMaxLocals();
        this.codeException = attribute.getExceptionTable();
        Attribute[] attributes = attribute.getAttributes();
        this.attributes = new AttributeGen[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            this.attributes[i] = AttributeGen.readAttribute(attributes[i], constantPool);
            if (this.attributes[i] instanceof LineNumberTableGen) {
                lineNumberTable = (LineNumberTableGen) this.attributes[i];
            }
            if (this.attributes[i] instanceof LocalVariableTableGen) {
                localVariableTable = (LocalVariableTableGen) this.attributes[i];
            }
        }
        this.instructions = new InstructionList(attribute.getByteCode(), localVariableTable, constantPool).getInstructions();
        this.decompiledInstructions = new DecompiledInstructions(instructions).getInstructions();
    }

}
