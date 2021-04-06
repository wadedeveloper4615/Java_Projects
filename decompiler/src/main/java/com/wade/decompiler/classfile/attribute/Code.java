package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class Code extends Attribute {
    private int maxStack;
    private int maxLocals;
    private byte[] byteCode;
    private List<CodeException> exceptionTable;
    private List<Attribute> attributes;
    private LocalVariableTable localVariableTable;
    private LineNumberTable lineNumberTable;

    public Code(int nameIndex, int length, DataInput file, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, file.readUnsignedShort(), file.readUnsignedShort(), null, null, null, constantPool);

        int codeLength = file.readInt();
        byteCode = new byte[codeLength];
        file.readFully(byteCode);

        int exception_table_length = file.readUnsignedShort();
        exceptionTable = new ArrayList<>();
        for (int i = 0; i < exception_table_length; i++) {
            exceptionTable.add(new CodeException(file));
        }

        int attributesCount = file.readUnsignedShort();
        attributes = new ArrayList<>();
        for (int i = 0; i < attributesCount; i++) {
            Attribute attribute = Attribute.readAttribute(file, constantPool);
            attributes.add(attribute);
            if (attribute instanceof LocalVariableTable) {
                this.localVariableTable = (LocalVariableTable) attribute;
            } else if (attribute instanceof LineNumberTable) {
                this.lineNumberTable = (LineNumberTable) attributes;
            }
        }
        super.setLength(length);
    }

    public Code(int nameIndex, int length, int maxStack, int maxLocals, byte[] code, List<CodeException> exceptionTable, List<Attribute> attributes, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_CODE, nameIndex, length, constantPool);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.byteCode = code != null ? code : new byte[0];
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
        super.setLength(calculateLength());
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

    private int getInternalLength() {
        return 2 + 2 + 4 + byteCode.length + 2 + 8 * (exceptionTable == null ? 0 : exceptionTable.size()) + 2;
    }
}
