package org.apache.bcel.classfile.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionList;

public class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] byteCode;
    private CodeException[] exceptionTable;
    private Attribute[] attributes;
    private LineNumberTable lineNumberTable;
    private LocalVariableTable localVariableTable;
    private InstructionHandle[] instructions;

    public Code(Code c) {
        this(c.getNameIndex(), c.getLength(), c.getMaxStack(), c.getMaxLocals(), c.getCode(), c.getExceptionTable(), c.getAttributes(), c.getConstantPool());
    }

    @SuppressWarnings("unused")
    public Code(int nameIndex, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(nameIndex, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);
        int code_length = file.readInt();
        byteCode = new byte[code_length];
        file.readFully(byteCode);
        int exceptionTableLength = file.readUnsignedShort();
        exceptionTable = new CodeException[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new CodeException(file);
        }
        InstructionList instructionList = new InstructionList(byteCode);
//        instructions = instructionList.getInstructionHandles();
        int attributesCount = file.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        lineNumberTable = null;
        localVariableTable = null;
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
            if (attributes[i] instanceof LineNumberTable) {
                lineNumberTable = (LineNumberTable) attributes[i];
            }
            if (attributes[i] instanceof LocalVariableTable) {
                localVariableTable = (LocalVariableTable) attributes[i];
            }
        }
        super.setLength(length);
    }

    public Code(int nameIndex, int length, int maxStack, int maxLocals, byte[] code, CodeException[] exceptionTable, Attribute[] attributes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CODE, nameIndex, length, constant_pool);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.byteCode = code != null ? code : new byte[0];
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength());
    }

    @Override
    public void accept(Visitor v) {
        v.visitCode(this);
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

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        Code c = (Code) clone();
        if (byteCode != null) {
            c.byteCode = new byte[byteCode.length];
            System.arraycopy(byteCode, 0, c.byteCode, 0, byteCode.length);
        }
        c.setConstantPool(_constant_pool);
        c.exceptionTable = new CodeException[exceptionTable.length];
        for (int i = 0; i < exceptionTable.length; i++) {
            c.exceptionTable[i] = exceptionTable[i].copy();
        }
        c.attributes = new Attribute[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            c.attributes[i] = attributes[i].copy(_constant_pool);
        }
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(maxStack);
        file.writeShort(maxLocals);
        file.writeInt(byteCode.length);
        file.write(byteCode, 0, byteCode.length);
        file.writeShort(exceptionTable.length);
        for (CodeException exception : exceptionTable) {
            exception.dump(file);
        }
        file.writeShort(attributes.length);
        for (Attribute attribute : attributes) {
            attribute.dump(file);
        }
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

    public InstructionHandle[] getInstructions() {
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

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength());
    }

    public void setByteCode(byte[] byteCode) {
        this.byteCode = byteCode;
    }

    public void setCode(byte[] code) {
        this.byteCode = code != null ? code : new byte[0];
        super.setLength(calculateLength());
    }

    public void setExceptionTable(CodeException[] exceptionTable) {
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        super.setLength(calculateLength());
    }

    public void setInstructions(InstructionHandle[] instructions) {
        this.instructions = instructions;
    }

    public void setLineNumberTable(LineNumberTable lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    public void setLocalVariableTable(LocalVariableTable localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
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
