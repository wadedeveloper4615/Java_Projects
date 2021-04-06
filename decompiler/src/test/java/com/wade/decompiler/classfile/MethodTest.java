package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;

import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Decompiler JUnit 5 Method Test")
class MethodTest {
    @Test
    void testField() {
        Method field = new Method();
        field.setAccessFlags(1);
        field.setAttributes(new Attribute[]{new ConstantValue(0, 0, 0, null)});
        field.setNameIndex(2);
        field.setSignatureIndex(3);
        assertNotNull(field.hashCode());
        assertNotNull(field.toString());
        EqualsVerifier.simple().forClass(field.getClass()).verify();
    }

    @Test
    void testMethodIntIntIntAttributeArrayConstantPoolEquals() {
        Method field1 = new Method(1, 2, 3, new Attribute[]{new ConstantValue(0, 0, 0, null)});
        Method field2 = new Method(1, 2, 3, new Attribute[]{new ConstantValue(0, 0, 0, null)});
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClasses(field1.getClass(), field2.getClass()).verify();
    }

    @Test
    void testMethodIntIntIntAttributeArrayConstantPoolNotEquals() {
        Method field1 = new Method(1, 2, 3, null);
        Method field2 = new Method(2, 3, 4, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClasses(field1.getClass(), field2.getClass()).verify();
    }
}
