package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.ExceptionTable;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class ExceptionTableGen extends AttributeGen {
    private String[] names;

    public ExceptionTableGen(ExceptionTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int[] exceptionIndexTable = attribute.getExceptionIndexTable();
        this.names = new String[exceptionIndexTable.length];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            this.names[i] = constantPool.getConstantString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExceptionTableGen other = (ExceptionTableGen) obj;
        if (!Arrays.equals(names, other.names))
            return false;
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
        return "ExceptionTableGen [names=" + Arrays.toString(names) + "]";
    }
}
