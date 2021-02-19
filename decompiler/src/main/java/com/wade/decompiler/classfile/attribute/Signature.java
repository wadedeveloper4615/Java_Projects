package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class Signature extends Attribute {
    private int signatureIndex;
    private String signature;

    public Signature(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public Signature(int name_index, int length, int signatureIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_SIGNATURE, name_index, length, constant_pool);
        this.signatureIndex = signatureIndex;
        this.signature = ((ConstantUtf8) constant_pool.getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public String getSignature() {
        return signature;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    @Override
    public String toString() {
        return "Signature [signatureIndex=" + signatureIndex + ", signature=" + signature + "]";
    }
}
