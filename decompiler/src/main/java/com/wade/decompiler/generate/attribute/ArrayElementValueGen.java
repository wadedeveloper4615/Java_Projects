package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ArrayElementValue;
import com.wade.decompiler.classfile.element.ElementValue;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ArrayElementValueGen extends ElementValueGen {
    private ElementValueGen[] value;

    public ArrayElementValueGen(byte type, ArrayElementValue element, ConstantPool constantPool) {
        super(type);
        ElementValue[] value = element.getElementValues();
        this.value = new ElementValueGen[value.length];
        for (int i = 0; i < value.length; i++) {
            this.value[i] = ElementValueGen.readElementValue(value[i], constantPool);
        }
    }
}
