package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.DataInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

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
        EqualsVerifier.simple().forClass(annotation.getClass()).verify();
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
        EqualsVerifier.simple().forClasses(annotation1.getClass(), annotation2.getClass()).verify();
    }

    @Test
    void testEqualsObject2() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        EqualsVerifier.simple().forClass(annotation1.getClass()).verify();
    }

    @Test
    void testEqualsObject3() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        EqualsVerifier.simple().forClass(annotation1.getClass()).verify();
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
        EqualsVerifier.simple().forClasses(annotation1.getClass(), annotation2.getClass()).verify();
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
        EqualsVerifier.simple().forClasses(annotation1.getClass(), annotation2.getClass()).verify();
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
        EqualsVerifier.simple().forClasses(annotation1.getClass(), annotation2.getClass()).verify();
    }

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
        EqualsVerifier.simple().forClasses(annotation1.getClass(), annotation2.getClass()).verify();
    }

    @Test
    void testEqualsObject8() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        EqualsVerifier.simple().forClass(annotation1.getClass()).verify();
    }

    @Test
    void testGetDefaultValue() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        EqualsVerifier.simple().forClass(annotation1.getClass()).verify();
    }

    @Test
    void testHashCode() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        assertNotNull(annotation1.hashCode());
        EqualsVerifier.simple().forClass(annotation1.getClass()).verify();
    }

    @Test
    void testSetDefaultValue() throws Exception {
        when(mockInputStream.read()).thenReturn((int) ElementValue.PRIMITIVE_LONG).thenReturn(0).thenReturn(1);
        dataInput = new DataInputStream(mockInputStream);
        AnnotationDefault annotation1 = new AnnotationDefault(1, 1, dataInput, new ConstantPool());
        assertNotNull(annotation1.getDefaultValue());
        annotation1.setDefaultValue(null);
        assertNull(annotation1.getDefaultValue());
        assertNotNull(annotation1.hashCode());
        EqualsVerifier.simple().forClass(annotation1.getClass()).verify();
    }

}
