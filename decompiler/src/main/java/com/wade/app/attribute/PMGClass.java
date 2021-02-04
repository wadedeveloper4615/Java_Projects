package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.exception.ClassFormatException;

public final class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;

    PMGClass(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }

    public PMGClass(final int name_index, final int length, final int pmgIndex, final int pmgClassIndex, final ConstantPool constantPool) {
        super(Const.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
    }

    public PMGClass(final PMGClass pgmClass) {
        this(pgmClass.getNameIndex(), pgmClass.getLength(), pgmClass.getPMGIndex(), pgmClass.getPMGClassIndex(), pgmClass.getConstantPool());
    }

    public int getPMGClassIndex() {
        return pmgClassIndex;
    }

    public String getPMGClassName() throws ClassFormatException {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgClassIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getPMGIndex() {
        return pmgIndex;
    }

    public String getPMGName() throws ClassFormatException {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }
}
