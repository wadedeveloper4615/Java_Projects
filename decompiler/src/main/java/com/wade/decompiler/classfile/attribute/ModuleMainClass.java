package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ModuleMainClass extends Attribute {
    private int mainClassIndex;
    private String className;

    public ModuleMainClass(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
        this.className = constantPool.getConstantString(mainClassIndex, ClassFileConstants.CONSTANT_Class);
    }

    public ModuleMainClass(int name_index, int length, int mainClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, name_index, length, constantPool);
        this.mainClassIndex = mainClassIndex;
        this.className = constantPool.getConstantString(mainClassIndex, ClassFileConstants.CONSTANT_Class);
    }

    public String getClassName() {
        return className;
    }

    public int getMainClassIndex() {
        return mainClassIndex;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("ModuleMainClass: ");
        buf.append(Utility.compactClassName(className, false));
        return buf.toString();
    }
}
