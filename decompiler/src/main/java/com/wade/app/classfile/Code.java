package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.classfile.attribute.CodeException;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private CodeException[] exceptionTable;
    private Attribute[] attributes;

    public Code(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
        this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);
        int code_length = file.readInt();
        code = new byte[code_length];
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

    public Code(int name_index, int length, int maxStack, int maxLocals, byte[] code, CodeException[] exceptionTable, Attribute[] attributes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CODE, name_index, length, constant_pool);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code != null ? code : new byte[0];
        this.exceptionTable = exceptionTable != null ? exceptionTable : new CodeException[0];
        this.attributes = attributes != null ? attributes : new Attribute[0];
        super.setLength(calculateLength());
    }

    private int calculateLength() {
        int len = 0;
        if (attributes != null) {
            for (Attribute attribute : attributes) {
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

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getMaxStack() {
        return maxStack;
    }

    @Override
    public String toString() {
        return "Code [maxStack=" + maxStack + ", maxLocals=" + maxLocals + ", code=" + code.length + "bytes , exceptionTable=" + Arrays.toString(exceptionTable) + ", attributes=" + Arrays.toString(attributes) + "]";
    }
}
