package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleMainClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleMainClassGen extends AttributeGen {
    private String className;

    public ModuleMainClassGen(ModuleMainClass attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.className = constantPool.getConstantString(attribute.getMainClassIndex(), ClassFileConstants.CONSTANT_Class);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleMainClassGen other = (ModuleMainClassGen) obj;
        if (className == null) {
            if (other.className != null)
                return false;
        } else if (!className.equals(other.className))
            return false;
        return true;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ModuleMainClassGen [className=" + className + "]";
    }
}
