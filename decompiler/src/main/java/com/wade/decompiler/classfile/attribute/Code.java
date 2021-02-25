package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] byteCode;
    private CodeException[] exceptionTable;
    private Attribute[] attributes;
    private LocalVariableTable localVariableTable;
    private LineNumberTable lineNumberTable;

    public Code(int nameIndex, int length, DataInput file, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constantPool);

        int codeLength = file.readInt();
        byteCode = new byte[codeLength];
        file.readFully(byteCode);

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Code other = (Code) obj;
        if (!Arrays.equals(attributes, other.attributes))
            return false;
        if (!Arrays.equals(byteCode, other.byteCode))
            return false;
        if (!Arrays.equals(exceptionTable, other.exceptionTable))
            return false;
        if (lineNumberTable == null) {
            if (other.lineNumberTable != null)
                return false;
        } else if (!lineNumberTable.equals(other.lineNumberTable))
            return false;
        if (localVariableTable == null) {
            if (other.localVariableTable != null)
                return false;
        } else if (!localVariableTable.equals(other.localVariableTable))
            return false;
        if (maxLocals != other.maxLocals)
            return false;
        if (maxStack != other.maxStack)
            return false;
        return true;
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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + Arrays.hashCode(byteCode);
        result = prime * result + Arrays.hashCode(exceptionTable);
        result = prime * result + ((lineNumberTable == null) ? 0 : lineNumberTable.hashCode());
        result = prime * result + ((localVariableTable == null) ? 0 : localVariableTable.hashCode());
        result = prime * result + maxLocals;
        result = prime * result + maxStack;
        return result;
    }

    @Override
    public String toString() {
        return "Code [maxStack=" + maxStack + ", maxLocals=" + maxLocals + ", exceptionTable=" + Arrays.toString(exceptionTable) + ", attributes=" + Arrays.toString(attributes) + "]";
    }
}
