package com.wade.decompiler.classfile.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    }

    @Test
    void testAnnotationEntry() {
        AnnotationEntry ae = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae.getElementValuePairs());
        assertEquals(0, ae.getElementValuePairs().size());
    }

    @Test
    void testEqualsObject1() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        assertTrue(ae1.equals(ae2));
    }

    @Test
    void testEqualsObject2() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertTrue(ae1.equals(ae1));
    }

    @Test
    void testEqualsObject3() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertFalse(ae1.equals(null));
    }

    @Test
    void testEqualsObject4() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        ae1.setConstantPool(null);
        assertFalse(ae1.equals(ae2));
    }

    @Test
    void testEqualsObject5() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        ae2.setConstantPool(null);
        assertFalse(ae1.equals(ae2));
    }

    @Test
    void testEqualsObject6() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        AnnotationEntry ae2 = new AnnotationEntry(1, new ConstantPool(), false);
        ae1.setConstantPool(null);
        ae2.setConstantPool(null);
        assertTrue(ae1.equals(ae2));
    }

    @Test
    void testGetAnnotationTypeIndex() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getTypeIndex());
    }

    @Test
    void testGetConstantPool() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getConstantPool());
    }

    @Test
    void testGetElementValuePairs() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getElementValuePairs());
    }

    @Test
    void testGetNumElementValuePairs() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getElementValuePairs());
    }

    @Test
    void testGetTypeIndex() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.getTypeIndex());
    }

    @Test
    void testHashCode() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.hashCode());
    }

    @Test
    void testIsRuntimeVisible() {
        AnnotationEntry ae1 = new AnnotationEntry(1, new ConstantPool(), false);
        assertNotNull(ae1.isRuntimeVisible());
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
