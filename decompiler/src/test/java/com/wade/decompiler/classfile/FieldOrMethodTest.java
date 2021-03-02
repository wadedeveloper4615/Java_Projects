package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;

@DisplayName("Decompiler JUnit 5 Field or Method Test")
class FieldOrMethodTest {
    public class AbstractTest extends FieldOrMethod {

        public AbstractTest() {
        }

        public AbstractTest(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes) {
            super(accessFlags, nameIndex, signatureIndex, attributes);
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void tesNotEqualsByAttribute() {
        Attribute[] attribute1 = new Attribute[] { new ConstantValue(0, 0, 0, null) };
        Attribute[] attribute2 = new Attribute[] { new ConstantValue(1, 1, 1, null) };
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, attribute1);
        FieldOrMethod field2 = new AbstractTest(1, 2, 3, attribute2);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(""));
        assertFalse(field1.equals(null));
        assertNotNull(field2.hashCode());
        assertNotNull(field2.toString());
        assertTrue(field2.equals(field2));
        assertFalse(field2.equals(""));
        assertFalse(field2.equals(null));
        assertFalse(field1.equals(field2));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEqualsByAttribute() {
        Attribute[] attribute1 = new Attribute[] { new ConstantValue(0, 0, 0, null) };
        Attribute[] attribute2 = new Attribute[] { new ConstantValue(0, 0, 0, null) };
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, attribute1);
        FieldOrMethod field2 = new AbstractTest(1, 2, 3, attribute2);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(""));
        assertFalse(field1.equals(null));
        assertNotNull(field2.hashCode());
        assertNotNull(field2.toString());
        assertTrue(field2.equals(field2));
        assertFalse(field2.equals(""));
        assertFalse(field2.equals(null));
        assertTrue(field1.equals(field2));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEqualsByConstant() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(1, 2, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(""));
        assertFalse(field1.equals(null));
        assertNotNull(field2.hashCode());
        assertNotNull(field2.toString());
        assertTrue(field2.equals(field2));
        assertFalse(field2.equals(""));
        assertFalse(field2.equals(null));
        assertTrue(field1.equals(field2));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testField() {
        FieldOrMethod field = new AbstractTest();
        field.setAccessFlags(1);
        field.setAttributes(new Attribute[] { new ConstantValue(0, 0, 0, null) });
        field.setNameIndex(2);
        field.setSignatureIndex(3);
        assertNotNull(field.hashCode());
        assertNotNull(field.toString());
        assertTrue(field.equals(field));
        assertFalse(field.equals(""));
        assertFalse(field.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testNotEqualsByConstant1() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(2, 2, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(""));
        assertFalse(field1.equals(null));
        assertNotNull(field2.hashCode());
        assertNotNull(field2.toString());
        assertTrue(field2.equals(field2));
        assertFalse(field2.equals(""));
        assertFalse(field2.equals(null));
        assertFalse(field1.equals(field2));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testNotEqualsByConstant2() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(1, 3, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(""));
        assertFalse(field1.equals(null));
        assertNotNull(field2.hashCode());
        assertNotNull(field2.toString());
        assertTrue(field2.equals(field2));
        assertFalse(field2.equals(""));
        assertFalse(field2.equals(null));
        assertFalse(field1.equals(field2));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testNotEqualsByConstant3() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(1, 2, 4, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(""));
        assertFalse(field1.equals(null));
        assertNotNull(field2.hashCode());
        assertNotNull(field2.toString());
        assertTrue(field2.equals(field2));
        assertFalse(field2.equals(""));
        assertFalse(field2.equals(null));
        assertFalse(field1.equals(field2));
    }
}
