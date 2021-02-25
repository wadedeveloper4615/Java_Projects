package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.ConstantPool;

class FieldOrMethodTest {
    @Test
    void testEqualsObject1() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertTrue(field1.equals(field1));
        assertNotNull(field1.getAccessFlags());
        assertNotNull(field1.getAttributes());
        assertNotNull(field1.getConstantPool());
        assertNotNull(field1.getNameIndex());
        assertNotNull(field1.getSignatureIndex());
    }

    @Test
    void testEqualsObject10() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field1.setNameIndex(-1);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEqualsObject11() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field1.setSignatureIndex(-1);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEqualsObject2() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertTrue(field1.equals(field2));
    }

    @Test
    void testEqualsObject3() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertFalse(field1.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEqualsObject4() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertFalse(field1.equals(""));
    }

    @Test
    void testEqualsObject5() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field1.setAttributes(null);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEqualsObject6() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field1.setAccessFlags(-1);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEqualsObject7() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field1.setConstantPool(null);
        assertNotNull(field1.hashCode());
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEqualsObject8() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field2.setConstantPool(null);
        assertFalse(field1.equals(field2));
    }

    @Test
    void testEqualsObject9() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        FieldOrMethod field2 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        field1.setConstantPool(null);
        field2.setConstantPool(null);
        assertTrue(field1.equals(field2));
    }
}
