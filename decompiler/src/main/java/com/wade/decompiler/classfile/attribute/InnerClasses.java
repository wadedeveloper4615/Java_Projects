package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class InnerClasses extends Attribute {
    private InnerClass[] innerClasses;

    public InnerClasses(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (InnerClass[]) null, constant_pool);
        int number_of_classes = input.readUnsignedShort();
        innerClasses = new InnerClass[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            innerClasses[i] = new InnerClass(input, constant_pool);
        }
    }

    public InnerClasses(int name_index, int length, InnerClass[] innerClasses, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_INNER_CLASSES, name_index, length, constant_pool);
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        InnerClasses other = (InnerClasses) obj;
        if (!Arrays.equals(innerClasses, other.innerClasses))
            return false;
        return true;
    }

    public InnerClass[] getInnerClasses() {
        return innerClasses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(innerClasses);
        return result;
    }

    @Override
    public String toString() {
        return "InnerClasses [innerClasses=" + Arrays.toString(innerClasses) + "]";
    }
}
