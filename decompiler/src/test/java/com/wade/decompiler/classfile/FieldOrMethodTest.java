package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;

import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Decompiler JUnit 5 Field or Method Test")
class FieldOrMethodTest {
    @Test
    void tesNotEqualsByAttribute() {
        Attribute[] attribute1 = new Attribute[]{new ConstantValue(0, 0, 0, null)};
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, attribute1);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClass(field1.getClass()).verify();
    }

    @Test
    void testEqualsByAttribute() {
        Attribute[] attribute1 = new Attribute[]{new ConstantValue(0, 0, 0, null)};
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, attribute1);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClass(field1.getClass()).verify();
    }

    @Test
    void testEqualsByConstant() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(1, 2, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClasses(field1.getClass(), field2.getClass()).verify();
    }

    @Test
    void testField() {
        FieldOrMethod field = new AbstractTest();
        field.setAccessFlags(1);
        field.setAttributes(new Attribute[]{new ConstantValue(0, 0, 0, null)});
        field.setNameIndex(2);
        field.setSignatureIndex(3);
        assertNotNull(field.hashCode());
        assertNotNull(field.toString());
        EqualsVerifier.simple().forClass(field.getClass()).verify();
    }

    @Test
    void testNotEqualsByConstant1() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(2, 2, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClasses(field1.getClass(), field2.getClass()).verify();
    }

    @Test
    void testNotEqualsByConstant2() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(1, 3, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClasses(field1.getClass(), field2.getClass()).verify();
    }

    @Test
    void testNotEqualsByConstant3() {
        FieldOrMethod field1 = new AbstractTest(1, 2, 3, null);
        FieldOrMethod field2 = new AbstractTest(1, 2, 4, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClasses(field1.getClass(), field2.getClass()).verify();
    }

    public class AbstractTest extends FieldOrMethod {

        public AbstractTest() {
        }

        public AbstractTest(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes) {
            super(accessFlags, nameIndex, signatureIndex, attributes);
        }
    }
}
