package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;
import com.wade.app.attribute.Attribute;

public final class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(final int name_index, final int length, final int sourceFileIndex, final ConstantPool constantPool) {
        super(Const.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }

    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public SourceFile(final SourceFile c) {
        this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    public String getSourceFileName() throws ClassFormatException {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(sourceFileIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }
}
