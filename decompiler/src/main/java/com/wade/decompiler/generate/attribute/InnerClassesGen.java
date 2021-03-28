package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.attribute.InnerClasses;
import com.wade.decompiler.classfile.constant.ConstantPool;

import java.util.Arrays;

public class InnerClassesGen extends AttributeGen {
    private InnerClassGen[] innerClasses;

    public InnerClassesGen(InnerClasses attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        InnerClass[] innerClasses = attribute.getInnerClasses();
        this.innerClasses = new InnerClassGen[innerClasses.length];
        for (int i = 0; i < innerClasses.length; i++) {
            this.innerClasses[i] = new InnerClassGen(innerClasses[i], constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        InnerClassesGen other = (InnerClassesGen) obj;
        if (!Arrays.equals(innerClasses, other.innerClasses)) return false;
        return true;
    }

    public InnerClassGen[] getInnerClasses() {
        return innerClasses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(innerClasses);
        return result;
    }

    @Override
    public String toString() {
        return "InnerClassesGen [innerClasses=" + Arrays.toString(innerClasses) + "]";
    }
}
