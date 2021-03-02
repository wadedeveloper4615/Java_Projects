package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;

@DisplayName("Decompiler JUnit 5 Method Test")
class MethodTest {
    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testField() {
        Method field = new Method();
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
    void testMethodIntIntIntAttributeArrayConstantPoolEquals() {
        Method field1 = new Method(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) });
        Method field2 = new Method(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) });
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
    void testMethodIntIntIntAttributeArrayConstantPoolNotEquals() {
        Method field1 = new Method(1, 2, 3, null);
        Method field2 = new Method(2, 3, 4, null);
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
