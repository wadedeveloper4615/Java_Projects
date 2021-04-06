package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<ElementValueGen> value;

    public ArrayElementValueGen(byte type, ArrayElementValue element, ConstantPool constantPool) {
        super(type);
        value = new ArrayList<>();
        for (ElementValue entry : element.getElementValues()) {
            value.add(ElementValueGen.readElementValue(entry, constantPool));
        }
    }
}
