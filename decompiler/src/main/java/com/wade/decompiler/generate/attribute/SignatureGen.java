package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Signature;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class SignatureGen extends AttributeGen {
    private String signature;

    public SignatureGen(Signature attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.signature = ((ConstantUtf8) constantPool.getConstant(attribute.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SignatureGen other = (SignatureGen) obj;
        if (signature == null) {
            if (other.signature != null)
                return false;
        } else if (!signature.equals(other.signature))
            return false;
        return true;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((signature == null) ? 0 : signature.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SignatureGen [signature=" + signature + "]";
    }
}
