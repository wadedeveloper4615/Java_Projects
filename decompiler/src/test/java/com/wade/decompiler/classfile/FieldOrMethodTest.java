package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.ConstantPool;

@DisplayName("Decompiler JUnit 5 Field or method Test")
class FieldOrMethodTest {
    @Test
    void testEqualsObject1() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertTrue(field1.equals(field1));
    }

    @Test
    void testEqualsObject2() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertFalse(field1.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEqualsObject3() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertFalse(field1.equals(""));
    }

    @Test
    void testSettersGetters() {
        FieldOrMethod field1 = new Field();
        field1.setAccessFlags(1);
        field1.setAttributes(new Attribute[] { new ConstantValue(0, 0, 0, null) });
        field1.setConstantPool(new ConstantPool());
        field1.setNameIndex(2);
        field1.setSignatureIndex(3);
        assertNotNull(field1.getAccessFlags());
        assertNotNull(field1.getAttributes());
        assertNotNull(field1.getConstantPool());
        assertNotNull(field1.getNameIndex());
        assertNotNull(field1.getSignatureIndex());
    }

    @Test
    void testToString() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertNotNull(field1.toString());
    }

}
