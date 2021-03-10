package com.wade.decompiler.classfile.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.DataInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;

import nl.jqno.equalsverifier.EqualsVerifier;

@ExtendWith(MockitoExtension.class)
@DisplayName("Decompiler JUnit 5 AnnotationEntry test")
class AnnotationEntryTest {
    DataInputStream dataInput;
    @Mock
    InputStream mockInputStream;

    @Test
    void testAddElementNameValuePair() {
        AnnotationEntry ae = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae.getElementValuePairs());
        ae.addElementNameValuePair(null);
        EqualsVerifier.simple().forClass(ae.getClass()).verify();
    }

    @Test
    void testAnnotationEntry() {
        AnnotationEntry ae = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae.getElementValuePairs());
        assertEquals(0, ae.getElementValuePairs().size());
        EqualsVerifier.simple().forClass(ae.getClass()).verify();
    }

    @Test
    void testEqualsObject1() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        EqualsVerifier.simple().forClasses(ae1.getClass(), ae2.getClass()).verify();
    }

    @Test
    void testEqualsObject2() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertTrue(ae1.equals(ae1));
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testEqualsObject3() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testEqualsObject4() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        ae1.setConstantPool(null);
        EqualsVerifier.simple().forClasses(ae1.getClass(), ae2.getClass()).verify();
    }

    @Test
    void testEqualsObject5() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        ae2.setConstantPool(null);
        EqualsVerifier.simple().forClasses(ae1.getClass(), ae2.getClass()).verify();
    }

    @Test
    void testEqualsObject6() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        ae1.setConstantPool(null);
        ae2.setConstantPool(null);
        EqualsVerifier.simple().forClasses(ae1.getClass(), ae2.getClass()).verify();
    }

    @Test
    void testGetAnnotationTypeIndex() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getTypeIndex());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testGetConstantPool() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getConstantPool());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testGetElementValuePairs() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getElementValuePairs());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testGetNumElementValuePairs() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getElementValuePairs());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testGetTypeIndex() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getTypeIndex());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testHashCode() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.hashCode());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testIsRuntimeVisible() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.isRuntimeVisible());
        EqualsVerifier.simple().forClass(ae1.getClass()).verify();
    }

    @Test
    void testRead() throws Exception {
        //@formatter:off
        when(mockInputStream.read())
        .thenReturn(0).thenReturn(3)
        .thenReturn(0).thenReturn(1)
        .thenReturn(0).thenReturn(1)
        .thenReturn((int) ElementValue.PRIMITIVE_LONG)
        .thenReturn(0).thenReturn(1)
        ;
        //@formatter:on
        dataInput = new DataInputStream(mockInputStream);
        AnnotationEntry ae1 = AnnotationEntry.read(dataInput, new ConstantPool(), true);
        assertNotNull(ae1);
    }

    @Test
    void testToString() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(new Constant[] { new ConstantFloat(20.0f) }), false);
        assertNotNull(ae1.toString());
    }

}
