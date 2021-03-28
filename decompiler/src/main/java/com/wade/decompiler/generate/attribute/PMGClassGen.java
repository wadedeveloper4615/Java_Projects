package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.PMGClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class PMGClassGen extends AttributeGen {
    private String pmg;
    private String pmgClass;

    public PMGClassGen(PMGClass attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.pmg = ((ConstantUtf8) constantPool.getConstant(attribute.getPmgIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.pmgClass = ((ConstantUtf8) constantPool.getConstant(attribute.getPmgClassIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PMGClassGen other = (PMGClassGen) obj;
        if (pmg == null) {
            if (other.pmg != null) return false;
        } else if (!pmg.equals(other.pmg)) return false;
        if (pmgClass == null) {
            if (other.pmgClass != null) return false;
        } else if (!pmgClass.equals(other.pmgClass)) return false;
        return true;
    }

    public String getPmg() {
        return pmg;
    }

    public String getPmgClass() {
        return pmgClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pmg == null) ? 0 : pmg.hashCode());
        result = prime * result + ((pmgClass == null) ? 0 : pmgClass.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "PMGClassGen [pmg=" + pmg + ", pmgClass=" + pmgClass + "]";
    }
}
