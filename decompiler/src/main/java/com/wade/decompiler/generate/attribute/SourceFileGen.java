package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.SourceFile;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class SourceFileGen extends AttributeGen {
    private String sourceFile;

    public SourceFileGen(SourceFile attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.sourceFile = ((ConstantUtf8) constantPool.getConstant(attribute.getSourceFileIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SourceFileGen other = (SourceFileGen) obj;
        if (sourceFile == null) {
            if (other.sourceFile != null)
                return false;
        } else if (!sourceFile.equals(other.sourceFile))
            return false;
        return true;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sourceFile == null) ? 0 : sourceFile.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SourceFileGen [sourceFile=" + sourceFile + "]";
    }
}
