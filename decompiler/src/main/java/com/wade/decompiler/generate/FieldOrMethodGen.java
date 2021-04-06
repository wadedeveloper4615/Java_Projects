package com.wade.decompiler.generate;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.FieldOrMethod;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generate.attribute.AttributeGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class FieldOrMethodGen {
    protected String name;
    protected String signature;
    protected ClassAccessFlagsList accessFlags;
    protected List<AttributeGen> attributes;

    public FieldOrMethodGen(FieldOrMethod value, ConstantPool constantPool) {
        this.name = ((ConstantUtf8) constantPool.getConstant(value.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(value.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.accessFlags = new ClassAccessFlagsList(value.getAccessFlags());

        attributes = new ArrayList<>();
        value.getAttributes().stream().forEach(attribute -> attributes.add(new AttributeGen(attribute, constantPool)));
    }
}
