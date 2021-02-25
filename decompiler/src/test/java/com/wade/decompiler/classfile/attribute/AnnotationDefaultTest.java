package com.wade.decompiler.classfile.attribute;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.DataInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;

@ExtendWith(MockitoExtension.class)
@DisplayName("Decompiler JUnit 5 AnnotationDefault test")
public class AnnotationDefaultTest {
    DataInputStream dataInput;
    @Mock
    InputStream mockInputStream;

    @Test
    void testAnnotationDefaultIntIntDataInputConstantPool() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation.getDefaultValue());
    }

    @Test
    void testEqualsObject1() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation2 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation2.getDefaultValue());
        assertTrue(annotation1.equals(annotation2));
    }

    @Test
    void testEqualsObject2() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertTrue(annotation1.equals(annotation1));
    }

    @Test
    void testEqualsObject3() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertFalse(annotation1.equals(null));
    }

    @Test
    void testEqualsObject4() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation2 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation2.getDefaultValue());
        annotation2.setDefaultValue(null);
        assertFalse(annotation1.equals(annotation2));
    }

    @Test
    void testEqualsObject5() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation2 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation2.getDefaultValue());
        annotation1.setDefaultValue(null);
        assertFalse(annotation1.equals(annotation2));
    }

    @Test
    void testEqualsObject6() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation2 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation2.getDefaultValue());
        annotation1.setDefaultValue(null);
        annotation2.setDefaultValue(null);
        assertTrue(annotation1.equals(annotation2));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEqualsObject7() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation2 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation2.getDefaultValue());
        assertFalse(annotation1.equals(""));
    }

    @Test
    void testGetDefaultValue() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
    }

    @Test
    void testHashCode() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation1.hashCode());
    }

    @Test
    void testSetDefaultValue() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        annotation1.setDefaultValue(null);
        assertNull(annotation1.getDefaultValue());
    }

}
