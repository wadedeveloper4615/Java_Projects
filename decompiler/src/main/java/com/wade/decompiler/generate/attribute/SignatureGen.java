package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Signature;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SignatureGen extends AttributeGen {
    private String signature;

    public SignatureGen(Signature attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.signature = ((ConstantUtf8) constantPool.getConstant(attribute.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
