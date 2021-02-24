package com.wade.decompiler.generate;

import java.io.IOException;

import com.wade.decompiler.classfile.FieldOrMethod;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generate.attribute.ConstantValueGen;

public class FieldGen extends FieldOrMethodGen {
    private ConstantValueGen constant;

    public FieldGen(FieldOrMethod value, ConstantPool constantPool) throws IOException {
        super(value, constantPool);
        for (Attribute attr : value.getAttributes()) {
            if (attr instanceof ConstantValue) {
                constant = new ConstantValueGen((ConstantValue) attr, constantPool);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldGen other = (FieldGen) obj;
        if (constant == null) {
            if (other.constant != null)
                return false;
        } else if (!constant.equals(other.constant))
            return false;
        return true;
    }

    public ConstantValueGen getConstant() {
        return constant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((constant == null) ? 0 : constant.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FieldGen [constant=" + constant + "]";
    }
}
