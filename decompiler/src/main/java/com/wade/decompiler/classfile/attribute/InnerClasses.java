package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class InnerClasses extends Attribute {
    private InnerClass[] innerClasses;

    public InnerClasses(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (InnerClass[]) null, constantPool);
        int number_of_classes = input.readUnsignedShort();
        innerClasses = new InnerClass[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            innerClasses[i] = new InnerClass(input);
        }
    }

    public InnerClasses(int nameIndex, int length, InnerClass[] innerClasses, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_INNER_CLASSES, nameIndex, length, constantPool);
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
