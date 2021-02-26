package com.wade.decompiler.generate.attribute;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.CodeException;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.InstructionList;
import com.wade.decompiler.decompiler.DecompiledInstructions;

public class CodeGen extends AttributeGen {
    private int maxStack;
    private int maxLocals;
    private Instruction[] instructions;
    private AttributeGen[] attributes;
    private CodeException[] codeException;
    private LineNumberTableGen lineNumberTable;
    private LocalVariableTableGen localVariableTable;
    private List<String> decompiledInstructions;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CodeGen other = (CodeGen) obj;
        if (!Arrays.equals(attributes, other.attributes))
            return false;
        if (!Arrays.equals(codeException, other.codeException))
            return false;
        if (!Arrays.equals(instructions, other.instructions))
            return false;
        if (maxLocals != other.maxLocals)
            return false;
        if (maxStack != other.maxStack)
            return false;
        return true;
    }

    public AttributeGen[] getAttributes() {
        return attributes;
    }

    public CodeException[] getCodeException() {
        return codeException;
    }

    public List<String> getDecompiledInstructions() {
        return decompiledInstructions;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public LineNumberTableGen getLineNumberTable() {
        return lineNumberTable;
    }

    public LocalVariableTableGen getLocalVariableTable() {
        return localVariableTable;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getMaxStack() {
        return maxStack;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + Arrays.hashCode(codeException);
        result = prime * result + Arrays.hashCode(instructions);
        result = prime * result + maxLocals;
        result = prime * result + maxStack;
        return result;
    }

    @Override
    public String toString() {
        return "CodeGen [maxStack=" + maxStack + ", maxLocals=" + maxLocals + ", instructions=" + instructions.length + " istrs., attributes=" + Arrays.toString(attributes) + ", codeException=" + Arrays.toString(codeException) + "]";
    }
}
