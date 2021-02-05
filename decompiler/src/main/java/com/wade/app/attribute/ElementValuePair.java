package com.wade.app.attribute;

import com.wade.app.ClassFileConstants;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.exception.ClassFormatException;

public class ElementValuePair {
    private final ElementValue elementValue;

    private final ConstantPool constantPool;

    private final int elementNameIndex;

    public ElementValuePair(final int elementNameIndex, final ElementValue elementValue, final ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementNameIndex = elementNameIndex;
        this.constantPool = constantPool;
    }

    public int getNameIndex() {
        return elementNameIndex;
    }

    public String getNameString() throws ClassFormatException {
        final ConstantUtf8 c = (ConstantUtf8) constantPool.getConstant(elementNameIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public final ElementValue getValue() {
        return elementValue;
    }
}
