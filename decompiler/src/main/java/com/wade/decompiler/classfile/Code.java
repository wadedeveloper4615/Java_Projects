package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public  class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private CodeException[] exceptionTable;
    private Attribute[] attributes;

    public Code( Code c) {
        this(c.getNameIndex(), c.getLength(), c.getMaxStack(), c.getMaxLocals(), c.getCode(), c.getExceptionTable(), c.getAttributes(), c.getConstantPool());
    }

    public Code( int name_index,  int length,  DataInput file,  ConstantPool constant_pool) throws IOException {
        this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);
        int code_length = file.readInt();
        code = new byte[code_length]; // Read byte code
        file.readFully(code);
        int exception_table_length = file.readUnsignedShort();
        exceptionTable = new CodeException[exception_table_length];
        for (int i = 0; i < exception_table_length; i++) {
            exceptionTable[i] = new CodeException(file);
        }
        int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
        }
        super.setLength(length);
    }

    public Code( int name_index,  int length,  int maxStack,  int maxLocals,  byte[] code,  CodeException[] exceptionTable,  Attribute[] attributes,  ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CODE, name_index, length, constant_pool);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code != null ? code : new byte[0];
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength()); // Adjust length
    }

    @Override
    public void accept( Visitor v) {
        v.visitCode(this);
    }

    private int calculateLength() {
        int len = 0;
        if (attributes != null) {
            for ( Attribute attribute : attributes) {
                len += attribute.getLength() + 6;
            }
        }
        return len + getInternalLength();
    }

    @Override
    public Attribute copy( ConstantPool _constant_pool) {
        Code c = (Code) clone();
        if (code != null) {
            c.code = new byte[code.length];
            System.arraycopy(code, 0, c.code, 0, code.length);
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
    public void dump( DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(maxStack);
        file.writeShort(maxLocals);
        file.writeInt(code.length);
        file.write(code, 0, code.length);
        file.writeShort(exceptionTable.length);
        for ( CodeException exception : exceptionTable) {
            exception.dump(file);
        }
        file.writeShort(attributes.length);
        for ( Attribute attribute : attributes) {
            attribute.dump(file);
        }
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public byte[] getCode() {
        return code;
    }

    public CodeException[] getExceptionTable() {
        return exceptionTable;
    }

    private int getInternalLength() {
        return 2 + 2 + 4 + code.length + 2 + 8 * (exceptionTable == null ? 0 : exceptionTable.length) + 2;
    }

    public LineNumberTable getLineNumberTable() {
        for ( Attribute attribute : attributes) {
            if (attribute instanceof LineNumberTable) {
                return (LineNumberTable) attribute;
            }
        }
        return null;
    }

    public LocalVariableTable getLocalVariableTable() {
        for ( Attribute attribute : attributes) {
            if (attribute instanceof LocalVariableTable) {
                return (LocalVariableTable) attribute;
            }
        }
        return null;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setAttributes( Attribute[] attributes) {
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength()); // Adjust length
    }

    public void setCode( byte[] code) {
        this.code = code != null ? code : new byte[0];
        super.setLength(calculateLength()); // Adjust length
    }

    public void setExceptionTable( CodeException[] exceptionTable) {
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        super.setLength(calculateLength()); // Adjust length
    }

    public void setMaxLocals( int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public void setMaxStack( int maxStack) {
        this.maxStack = maxStack;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString( boolean verbose) {
        StringBuilder buf = new StringBuilder(100); // CHECKSTYLE IGNORE MagicNumber
        buf.append("Code(maxStack = ").append(maxStack).append(", maxLocals = ").append(maxLocals).append(", code_length = ").append(code.length).append(")\n").append(Utility.codeToString(code, super.getConstantPool(), 0, -1, verbose));
        if (exceptionTable.length > 0) {
            buf.append("\nException handler(s) = \n").append("From\tTo\tHandler\tType\n");
            for ( CodeException exception : exceptionTable) {
                buf.append(exception.toString(super.getConstantPool(), verbose)).append("\n");
            }
        }
        if (attributes.length > 0) {
            buf.append("\nAttribute(s) = ");
            for ( Attribute attribute : attributes) {
                buf.append("\n").append(attribute.getName()).append(":");
                buf.append("\n").append(attribute);
            }
        }
        return buf.toString();
    }
}
