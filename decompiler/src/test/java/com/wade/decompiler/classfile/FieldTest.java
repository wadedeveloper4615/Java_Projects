package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;

import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Decompiler JUnit 5 Field Test")
@ExtendWith(MockitoExtension.class)
class FieldTest {
    @Mock
    private DataInput inputStream;

    @Test
    void testField1() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(1).thenReturn(1).thenReturn(0);
        //@formatter:on
        Field field = new Field(inputStream, null);
        EqualsVerifier.simple().forClass(field.getClass()).verify();
    }

    @Test
    void testField2() {
        Field field = new Field();
        field.setAccessFlags(1);
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new ConstantValue(0, 0, 0, null));
        field.setAttributes(attributes);
        field.setNameIndex(2);
        field.setSignatureIndex(3);
        assertNotNull(field.hashCode());
        assertNotNull(field.toString());
        EqualsVerifier.simple().forClass(field.getClass()).verify();
    }

    @Test
    void testMethodIntIntIntAttributeArrayConstantPoolEquals() {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new ConstantValue(0, 0, 0, null));
        Field field1 = new Field(1, 2, 3, attributes);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClass(field1.getClass()).verify();
    }

    @Test
    void testMethodIntIntIntAttributeArrayConstantPoolNotEquals() {
        Field field1 = new Field(1, 2, 3, null);
        assertNotNull(field1.hashCode());
        assertNotNull(field1.toString());
        EqualsVerifier.simple().forClass(field1.getClass()).verify();
    }
}
