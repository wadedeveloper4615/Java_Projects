package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class NestMembers extends Attribute {
    private int[] classes;

    public NestMembers(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (int[]) null, constantPool);
        int number_of_classes = input.readUnsignedShort();
        classes = new int[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            classes[i] = input.readUnsignedShort();
        }
    }

    public NestMembers(int nameIndex, int length, int[] classes, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.classes = classes != null ? classes : new int[0];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        NestMembers other = (NestMembers) obj;
        if (!Arrays.equals(classes, other.classes))
            return false;
        return true;
    }

    public int[] getClasses() {
        return classes;
    }

    public int getNumberClasses() {
        return classes == null ? 0 : classes.length;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(classes);
        return result;
    }

    @Override
    public String toString() {
        return "NestMembers [classes=" + Arrays.toString(classes) + "]";
    }
}
