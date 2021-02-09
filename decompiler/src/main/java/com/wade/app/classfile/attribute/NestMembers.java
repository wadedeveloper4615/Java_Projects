package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class NestMembers extends Attribute {
    private int[] classes;

    public NestMembers(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
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

    public int[] getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        return "NestMembers [classes=" + Arrays.toString(classes) + "]";
    }
}
