package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ElementValuePair;
import com.wade.decompiler.enums.ClassFileConstants;

public class ElementValuePairGen {
    private ElementValueGen elementValue;
    private String elementName;

    public ElementValuePairGen(ElementValuePair elementValuePairs, ElementValueGen elementValue, ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementName = ((ConstantUtf8) constantPool.getConstant(elementValuePairs.getElementNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ElementValuePairGen other = (ElementValuePairGen) obj;
        if (elementName == null) {
            if (other.elementName != null) return false;
        } else if (!elementName.equals(other.elementName)) return false;
        if (elementValue == null) {
            if (other.elementValue != null) return false;
        } else if (!elementValue.equals(other.elementValue)) return false;
        return true;
    }

    public String getElementName() {
        return elementName;
    }

    public ElementValueGen getElementValue() {
        return elementValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elementName == null) ? 0 : elementName.hashCode());
        result = prime * result + ((elementValue == null) ? 0 : elementValue.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ElementValuePairGen [elementValue=" + elementValue + ", elementName=" + elementName + "]";
    }
}
