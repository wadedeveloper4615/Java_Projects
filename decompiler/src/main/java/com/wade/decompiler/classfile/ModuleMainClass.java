package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleMainClass extends Attribute {
    private int mainClassIndex;

    public ModuleMainClass(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
    }

    public ModuleMainClass(int name_index, int length, int mainClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, name_index, length, constantPool);
        this.mainClassIndex = mainClassIndex;
    }

    public ModuleMainClass(ModuleMainClass c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    @Override
    public void accept(Visitor v) {
        v.visitModuleMainClass(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        ModuleMainClass c = (ModuleMainClass) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(mainClassIndex);
    }

    public int getHostClassIndex() {
        return mainClassIndex;
    }

    public void setHostClassIndex(int mainClassIndex) {
        this.mainClassIndex = mainClassIndex;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("ModuleMainClass: ");
        String class_name = super.getConstantPool().getConstantString(mainClassIndex, ClassFileConstants.CONSTANT_Class);
        buf.append(Utility.compactClassName(class_name, false));
        return buf.toString();
    }
}
