package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.EnclosingMethod;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class EnclosingMethodGen extends AttributeGen {
    private String superName;
    private String methodName;
    private String signature;

    public EnclosingMethodGen(EnclosingMethod attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int classIndex = attribute.getClassIndex();
        int mehtodIndex = attribute.getMethodIndex();

        ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
        ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(mehtodIndex, ClassFileConstants.CONSTANT_NameAndType);

        superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        EnclosingMethodGen other = (EnclosingMethodGen) obj;
        if (methodName == null) {
            if (other.methodName != null) return false;
        } else if (!methodName.equals(other.methodName)) return false;
        if (signature == null) {
            if (other.signature != null) return false;
        } else if (!signature.equals(other.signature)) return false;
        if (superName == null) {
            if (other.superName != null) return false;
        } else if (!superName.equals(other.superName)) return false;
        return true;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getSignature() {
        return signature;
    }

    public String getSuperName() {
        return superName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + ((signature == null) ? 0 : signature.hashCode());
        result = prime * result + ((superName == null) ? 0 : superName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "EnclosingMethodGen [superName=" + superName + ", methodName=" + methodName + ", signature=" + signature + "]";
    }
}
