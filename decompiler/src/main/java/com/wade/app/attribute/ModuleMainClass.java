package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class ModuleMainClass extends Attribute {
    private int mainClassIndex;

    public ModuleMainClass(final int nameIndex, final int length, final DataInputStream input, final ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
    }

    public ModuleMainClass(final int name_index, final int length, final int mainClassIndex, final ConstantPool constantPool) {
        super(Const.ATTR_NEST_MEMBERS, name_index, length, constantPool);
        this.mainClassIndex = mainClassIndex;
    }

    public ModuleMainClass(final ModuleMainClass c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    public int getHostClassIndex() {
        return mainClassIndex;
    }

    public void setHostClassIndex(final int mainClassIndex) {
        this.mainClassIndex = mainClassIndex;
    }
}
