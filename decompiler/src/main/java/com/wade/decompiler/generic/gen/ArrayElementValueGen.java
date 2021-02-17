package com.wade.decompiler.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.element.ArrayElementValue;
import com.wade.decompiler.classfile.element.ElementValue;

public class ArrayElementValueGen extends ElementValueGen {
    // J5TODO: Should we make this an array or a list? A list would be easier to
    // modify ...
    private List<ElementValueGen> evalues;

    public ArrayElementValueGen(ArrayElementValue value, ConstantPoolGen cpool, boolean copyPoolEntries) {
        super(ARRAY, cpool);
        evalues = new ArrayList<>();
        ElementValue[] in = value.getElementValuesArray();
        for (ElementValue element : in) {
            evalues.add(ElementValueGen.copy(element, cpool, copyPoolEntries));
        }
    }

    public ArrayElementValueGen(ConstantPoolGen cp) {
        super(ARRAY, cp);
        evalues = new ArrayList<>();
    }

    public ArrayElementValueGen(int type, ElementValue[] datums, ConstantPoolGen cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.evalues = new ArrayList<>();
        for (ElementValue datum : datums) {
            evalues.add(ElementValueGen.copy(datum, cpool, true));
        }
    }

    public void addElement(ElementValueGen gen) {
        evalues.add(gen);
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType()); // u1 type of value (ARRAY == '[')
        dos.writeShort(evalues.size());
        for (ElementValueGen element : evalues) {
            element.dump(dos);
        }
    }

    @Override
    public ElementValue getElementValue() {
        ElementValue[] immutableData = new ElementValue[evalues.size()];
        int i = 0;
        for (ElementValueGen element : evalues) {
            immutableData[i++] = element.getElementValue();
        }
        return new ArrayElementValue(super.getElementValueType(), immutableData, getConstantPool().getConstantPool());
    }

    public List<ElementValueGen> getElementValues() {
        return evalues;
    }

    public int getElementValuesSize() {
        return evalues.size();
    }

    @Override
    public String stringifyValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String comma = "";
        for (ElementValueGen element : evalues) {
            sb.append(comma);
            comma = ",";
            sb.append(element.stringifyValue());
        }
        sb.append("]");
        return sb.toString();
    }
}
