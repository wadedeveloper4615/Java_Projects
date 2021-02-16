package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class NestMembers extends Attribute {
    private int[] classes;

    public NestMembers(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (int[]) null, constant_pool);
        int number_of_classes = input.readUnsignedShort();
        classes = new int[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            classes[i] = input.readUnsignedShort();
        }
    }

    public NestMembers(int name_index, int length, int[] classes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, name_index, length, constant_pool);
        this.classes = classes != null ? classes : new int[0];
    }

    public NestMembers(NestMembers c) {
        this(c.getNameIndex(), c.getLength(), c.getClasses(), c.getConstantPool());
    }

    @Override
    public void accept(Visitor v) {
        v.visitNestMembers(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        NestMembers c = (NestMembers) clone();
        if (classes != null) {
            c.classes = new int[classes.length];
            System.arraycopy(classes, 0, c.classes, 0, classes.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(classes.length);
        for (int index : classes) {
            file.writeShort(index);
        }
    }

    public int[] getClasses() {
        return classes;
    }

    public String[] getClassNames() {
        String[] names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            names[i] = super.getConstantPool().getConstantString(classes[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
        return names;
    }

    public int getNumberClasses() {
        return classes == null ? 0 : classes.length;
    }

    public void setClasses(int[] classes) {
        this.classes = classes != null ? classes : new int[0];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("NestMembers(");
        buf.append(classes.length);
        buf.append("):\n");
        for (int index : classes) {
            String class_name = super.getConstantPool().getConstantString(index, ClassFileConstants.CONSTANT_Class);
            buf.append("  ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
