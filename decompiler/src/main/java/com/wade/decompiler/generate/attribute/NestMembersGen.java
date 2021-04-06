package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.NestMembers;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class NestMembersGen extends AttributeGen {
    private String[] names;

    public NestMembersGen(NestMembers attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int[] classes = attribute.getClasses();
        this.names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            this.names[i] = constantPool.constantToString(classes[i], ClassFileConstants.CONSTANT_Class);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        NestMembersGen other = (NestMembersGen) obj;
        if (!Arrays.equals(names, other.names)) return false;
        return true;
    }

    public String[] getNames() {
        return names;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(names);
        return result;
    }

    @Override
    public String toString() {
        return "NestMembersGen [names=" + Arrays.toString(names) + "]";
    }
}
