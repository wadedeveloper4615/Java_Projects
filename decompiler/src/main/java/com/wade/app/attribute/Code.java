package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private CodeException[] exceptionTable;
    private Attribute[] attributes;

    public Code(final Code c) {
        this(c.getNameIndex(), c.getLength(), c.getMaxStack(), c.getMaxLocals(), c.getCode(), c.getExceptionTable(), c.getAttributes(), c.getConstantPool());
    }

    public Code(final int name_index, final int length, final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);
        final int code_length = file.readInt();
        code = new byte[code_length]; // Read byte code
        file.readFully(code);
        final int exception_table_length = file.readUnsignedShort();
        exceptionTable = new CodeException[exception_table_length];
        for (int i = 0; i < exception_table_length; i++) {
            exceptionTable[i] = new CodeException(file);
        }
        final int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
        }
        super.setLength(length);
    }

    public Code(final int name_index, final int length, final int maxStack, final int maxLocals, final byte[] code, final CodeException[] exceptionTable, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(Const.ATTR_CODE, name_index, length, constant_pool);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code != null ? code : new byte[0];
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength()); // Adjust length
    }

    private int calculateLength() {
        int len = 0;
        if (attributes != null) {
            for (final Attribute attribute : attributes) {
                len += attribute.getLength() + 6 /* attribute header size */;
            }
        }
        return len + getInternalLength();
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
        return 2 /* maxStack */ + 2 /* maxLocals */ + 4 /* code length */
                + code.length /* byte-code */
                + 2 /* exception-table length */
                + 8 * (exceptionTable == null ? 0 : exceptionTable.length) /* exception table */
                + 2 /* attributes count */;
    }

    public LineNumberTable getLineNumberTable() {
        for (final Attribute attribute : attributes) {
            if (attribute instanceof LineNumberTable) {
                return (LineNumberTable) attribute;
            }
        }
        return null;
    }

    public LocalVariableTable getLocalVariableTable() {
        for (final Attribute attribute : attributes) {
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

}
