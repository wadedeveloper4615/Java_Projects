package com.wade.decompiler.classfile.element;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ElementValuePair {
    private ElementValue elementValue;
    private int elementNameIndex;

    public ElementValuePair(int elementNameIndex, ElementValue elementValue) {
        this.elementValue = elementValue;
        this.elementNameIndex = elementNameIndex;
    }
}
