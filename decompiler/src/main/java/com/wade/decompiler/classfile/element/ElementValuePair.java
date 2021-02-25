package com.wade.decompiler.classfile.element;

public class ElementValuePair {
    private ElementValue elementValue;
    private int elementNameIndex;

    public ElementValuePair(int elementNameIndex, ElementValue elementValue) {
        this.elementValue = elementValue;
        this.elementNameIndex = elementNameIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ElementValuePair other = (ElementValuePair) obj;
        if (elementNameIndex != other.elementNameIndex)
            return false;
        if (elementValue == null) {
            if (other.elementValue != null)
                return false;
        } else if (!elementValue.equals(other.elementValue))
            return false;
        return true;
    }

    public int getElementNameIndex() {
        return elementNameIndex;
    }

    public ElementValue getElementValue() {
        return elementValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + elementNameIndex;
        result = prime * result + ((elementValue == null) ? 0 : elementValue.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ElementValuePair [elementValue=" + elementValue + ", elementNameIndex=" + elementNameIndex + "]";
    }
}
