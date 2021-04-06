package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.CodeException;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.InstructionList;
import com.wade.decompiler.decompiler.InstructionInfoExtract;
import com.wade.decompiler.generate.instructions.InstructionGen;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class CodeGen extends AttributeGen {
    private int maxStack;
    private int maxLocals;
    private int codeSize;
    @ToString.Exclude
    private StackMapGen stackMap;
    private List<CodeException> codeException;
    @ToString.Exclude
    private LineNumberTableGen lineNumberTable;
    @ToString.Exclude
    private LocalVariableTableGen localVariableTable;
    @ToString.Exclude
    private List<InstructionGen> instructionExtracted = new ArrayList<>();
    @ToString.Exclude
    private Instruction[] instructions;
    @ToString.Exclude
    private List<String> instructionDecompiled;
    private List<AttributeGen> attributes;

    public CodeGen(Code code, ConstantPool constantPool) throws IOException {
        super(code, constantPool);
        this.maxStack = code.getMaxStack();
        this.maxLocals = code.getMaxLocals();
        this.codeException = code.getExceptionTable();
        this.codeSize = code.getByteCode().length;
        List<Attribute> attributes = code.getAttributes();
        this.attributes = new ArrayList<>();
        for (Attribute entry : attributes) {
            AttributeGen attribute = AttributeGen.readAttribute(entry, constantPool);
            if (attribute instanceof LineNumberTableGen) {
                lineNumberTable = (LineNumberTableGen) attributes;
            }
            if (attribute instanceof LocalVariableTableGen) {
                localVariableTable = (LocalVariableTableGen) attribute;
            }
            if (attributes instanceof StackMapGen) {
                stackMap = (StackMapGen) attributes;
            }
        }
        this.instructions = new InstructionList(code.getByteCode(), localVariableTable, constantPool).getInstructions();
        this.instructionExtracted = new InstructionInfoExtract(instructions).getInstructionsExtracted();
        this.instructionDecompiled = null;//new DecompileInstructions(instructionExtracted).getInstructionDecompiled();
    }

}
