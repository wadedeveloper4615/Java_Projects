package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class NestMembers extends Attribute {
    private int[] classes;
    private String[] names;

    public NestMembers(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (int[]) null, constant_pool);
        int number_of_classes = input.readUnsignedShort();
        classes = new int[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            classes[i] = input.readUnsignedShort();
        }
        names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            names[i] = super.getConstantPool().getConstantString(classes[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
    }

    public NestMembers(int name_index, int length, int[] classes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, name_index, length, constant_pool);
        this.classes = classes != null ? classes : new int[0];
        names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            names[i] = super.getConstantPool().getConstantString(classes[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
    }

    public int[] getClasses() {
        return classes;
    }

    public String[] getClassNames() {
        return names;
    }

    public int getNumberClasses() {
        return classes == null ? 0 : classes.length;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("NestMembers(");
        buf.append(classes.length);
        buf.append("):\n");
        for (String class_name : names) {
            buf.append("  ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
