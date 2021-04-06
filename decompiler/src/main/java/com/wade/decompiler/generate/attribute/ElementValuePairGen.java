package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ElementValuePair;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ElementValuePairGen {
    private ElementValueGen elementValue;
    private String elementName;

    public ElementValuePairGen(ElementValuePair elementValuePairs, ElementValueGen elementValue, ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementName = ((ConstantUtf8) constantPool.getConstant(elementValuePairs.getElementNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
