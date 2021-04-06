package com.wade.decompiler.generate;

import com.wade.decompiler.classfile.FieldOrMethod;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generate.attribute.AttributeGen;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class FieldOrMethodGen {
    protected String name;
    protected String signature;
    protected ClassAccessFlagsList accessFlags;
    protected AttributeGen[] attributes;

    public FieldOrMethodGen(FieldOrMethod value, ConstantPool constantPool) throws IOException {
        this.name = ((ConstantUtf8) constantPool.getConstant(value.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(value.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.accessFlags = new ClassAccessFlagsList(value.getAccessFlags());
        Attribute[] attributes = value.getAttributes();
        this.attributes = new AttributeGen[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            this.attributes[i] = AttributeGen.readAttribute(attributes[i], constantPool);
        }
    }
}
