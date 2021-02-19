package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.CodeException;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.InstructionList;
import com.wade.decompiler.util.Utility;

public class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] byteCode;
    private CodeException[] exceptionTable;
    private Attribute[] attributes;
    private LocalVariableTable localVariableTable;
    private LineNumberTable lineNumberTable;
    private Instruction[] instructions;

    public Code(int nameIndex, int length, DataInput file, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constantPool);

        int codeLength = file.readInt();
        byteCode = new byte[codeLength];
        file.readFully(byteCode);
        instructions = new InstructionList(byteCode, constantPool).getInstructions();

        int exception_table_length = file.readUnsignedShort();
        exceptionTable = new CodeException[exception_table_length];
        for (int i = 0; i < exception_table_length; i++) {
            exceptionTable[i] = new CodeException(file);
        }

        int attributesCount = file.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.readAttribute(file, constantPool);
            if (attributes[i] instanceof LocalVariableTable) {
                this.localVariableTable = (LocalVariableTable) attributes[i];
            } else if (attributes[i] instanceof LineNumberTable) {
                this.lineNumberTable = (LineNumberTable) attributes[i];
            }
        }
        super.setLength(length);
    }

    public Code(int nameIndex, int length, int maxStack, int maxLocals, byte[] code, CodeException[] exceptionTable, Attribute[] attributes, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_CODE, nameIndex, length, constantPool);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.byteCode = code != null ? code : new byte[0];
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength()); // Adjust length
    }

    private int calculateLength() {
        int len = 0;
        if (attributes != null) {
            for (Attribute attribute : attributes) {
                len += attribute.getLength() + 6;
            }
        }
        return len + getInternalLength();
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public byte[] getByteCode() {
        return byteCode;
    }

    public byte[] getCode() {
        return byteCode;
    }

    public CodeException[] getExceptionTable() {
        return exceptionTable;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    private int getInternalLength() {
        return 2 + 2 + 4 + byteCode.length + 2 + 8 * (exceptionTable == null ? 0 : exceptionTable.length) + 2;
    }

    public LineNumberTable getLineNumberTable() {
        return lineNumberTable;
    }

    public LocalVariableTable getLocalVariableTable() {
        return localVariableTable;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getMaxStack() {
        return maxStack;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean verbose) {
        StringBuilder buf = new StringBuilder(100);
        buf.append("Code(maxStack = ").append(maxStack).append(", maxLocals = ").append(maxLocals).append(", code_length = ").append(byteCode.length).append(")\n").append(Utility.codeToString(byteCode, super.getConstantPool(), 0, -1, verbose));
        if (exceptionTable.length > 0) {
            buf.append("\nException handler(s) = \n").append("From\tTo\tHandler\tType\n");
            for (CodeException exception : exceptionTable) {
                buf.append(exception.toString(super.getConstantPool(), verbose)).append("\n");
            }
        }
        if (attributes.length > 0) {
            buf.append("\nAttribute(s) = ");
            for (Attribute attribute : attributes) {
                buf.append("\n").append(attribute.getName()).append(":");
                buf.append("\n").append(attribute);
            }
        }
        return buf.toString();
    }
}
