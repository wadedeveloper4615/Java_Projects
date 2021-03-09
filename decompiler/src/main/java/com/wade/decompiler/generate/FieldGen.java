package com.wade.decompiler.generate;

import java.io.IOException;

import com.wade.decompiler.classfile.FieldOrMethod;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.attribute.Signature;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generate.attribute.ConstantValueGen;
import com.wade.decompiler.generate.attribute.SignatureGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class FieldGen extends FieldOrMethodGen {
    private ConstantValueGen constant;
    private SignatureGen typeSignature;

    public FieldGen(FieldOrMethod value, ConstantPool constantPool) throws IOException {
        super(value, constantPool);
        for (Attribute attr : value.getAttributes()) {
            if (attr instanceof ConstantValue) {
                constant = new ConstantValueGen((ConstantValue) attr, constantPool);
            }
            if (attr instanceof Signature) {
                typeSignature = new SignatureGen((Signature) attr, constantPool);
            }
        }
    }

}
