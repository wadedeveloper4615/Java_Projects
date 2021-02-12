
package org.apache.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.enums.ClassFileConstants;

public class ElementValuePair {
    private final ElementValue elementValue;

    private final ConstantPool constantPool;

    private final int elementNameIndex;

    public ElementValuePair(final int elementNameIndex, final ElementValue elementValue, final ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementNameIndex = elementNameIndex;
        this.constantPool = constantPool;
    }

    protected void dump(final DataOutputStream dos) throws IOException {
        dos.writeShort(elementNameIndex); // u2 name of the element
        elementValue.dump(dos);
    }

    public int getNameIndex() {
        return elementNameIndex;
    }

    public String getNameString() {
        final ConstantUtf8 c = (ConstantUtf8) constantPool.getConstant(elementNameIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public final ElementValue getValue() {
        return elementValue;
    }

    public String toShortString() {
        final StringBuilder result = new StringBuilder();
        result.append(getNameString()).append("=").append(getValue().toShortString());
        return result.toString();
    }
}
