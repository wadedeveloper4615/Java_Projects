package com.wade.decompiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generate.FieldGen;
import com.wade.decompiler.generate.JavaClassGen;
import com.wade.decompiler.generate.MethodGen;

import lombok.extern.slf4j.Slf4j;
import nl.jqno.equalsverifier.EqualsVerifier;

@ExtendWith(MockitoExtension.class)
@DisplayName("Decompiler JUnit 5 test")
@Slf4j
class DecompilerTest extends AbstractTest {
    @Test
    void testEqual1() throws Exception {
        JavaClass clazz1 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        JavaClassGen gen1 = new JavaClassGen(clazz1);
        EqualsVerifier.simple().forClass(gen1.getClass()).verify();
    }

    @Test
    void testEqual2() throws Exception {
        JavaClass clazz1 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        JavaClassGen gen1 = new JavaClassGen(clazz1);
        EqualsVerifier.simple().forClass(gen1.getClass()).verify();
    }

    @Test
    void testEqual3() throws Exception {
        JavaClass clazz1 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        JavaClassGen gen1 = new JavaClassGen(clazz1);
        EqualsVerifier.simple().forClass(gen1.getClass()).verify();
    }

    @Test
    void testEqual4() throws Exception {
        JavaClass clazz1 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        JavaClassGen gen1 = new JavaClassGen(clazz1);
        JavaClass clazz2 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        JavaClassGen gen2 = new JavaClassGen(clazz2);
        EqualsVerifier.simple().forClasses(gen1.getClass(), gen2.getClass()).verify();
    }

    @Test
    void testObjectClass() throws Exception {
        JavaClass clazz = getTestClass("java.lang.Object");
        Method[] methods = clazz.getMethods();
        ConstantPool constantPool = clazz.getConstantPool();
        Constant[] constantPoolArray = constantPool.getConstantPool();
        assertEquals(12, methods.length);
        assertEquals(92, constantPoolArray.length);
        assertNotNull(clazz);
    }

    @Test
    void testSimpleClass() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        JavaClassGen gen = new JavaClassGen(clazz);
        MethodGen[] methods = gen.getMethods();
        ConstantPool constantPool = gen.getConstantPool();
        // ClassAccessFlagsList flags = gen.getAccessFlags();
        Constant[] constantPoolArray = constantPool.getConstantPool();

        assertNotNull(clazz.toString());
        assertNotNull(clazz.hashCode());
        assertNotNull(gen.toString());
        assertNotNull(gen.hashCode());
        assertNotNull(methods);
        assertEquals(2, methods.length);
        assertNotNull(methods[0]);
        assertNotNull(methods[0].toString());
        assertNotNull(methods[0].hashCode());
        assertNotNull(methods[1]);
        assertNotNull(methods[1].toString());
        assertNotNull(methods[1].hashCode());
        assertTrue(methods[1].getAccessFlags().isStatic());
        assertEquals(20, constantPoolArray.length);
    }

    @Test
    void testSimpleClassWithDefaultConstructor() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithDefaultConstructor");
        JavaClassGen gen = new JavaClassGen(clazz);
        MethodGen[] methods = gen.getMethods();
        ConstantPool constantPool = gen.getConstantPool();
        Constant[] constantPoolArray = constantPool.getConstantPool();

        assertNotNull(clazz.toString());
        assertNotNull(clazz.hashCode());
        assertNotNull(gen.toString());
        assertNotNull(gen.hashCode());
        assertNotNull(methods);
        assertEquals(1, methods.length);
        assertNotNull(methods[0]);
        assertNotNull(methods[0].toString());
        assertNotNull(methods[0].hashCode());
        assertEquals(16, constantPoolArray.length);
    }

    @Test
    void testSimpleClassWithFields() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithFields");
        JavaClassGen gen = new JavaClassGen(clazz);
        MethodGen[] methods = gen.getMethods();
        FieldGen[] fields = gen.getFields();
        ConstantPool constantPool = gen.getConstantPool();
        Constant[] constantPoolArray = constantPool.getConstantPool();

        assertNotNull(clazz.toString());
        assertNotNull(clazz.hashCode());
        assertNotNull(gen.toString());
        assertNotNull(gen.hashCode());
        assertNotNull(methods);
        assertEquals(2, methods.length);
        assertEquals(15, fields.length);
        assertNotNull(methods[0]);
        assertNotNull(methods[0].toString());
        assertNotNull(methods[0].hashCode());
        assertNotNull(methods[1]);
        assertNotNull(methods[1].toString());
        assertNotNull(methods[1].hashCode());
        assertTrue(methods[1].getAccessFlags().isStatic());
        assertEquals(52, constantPoolArray.length);
    }

    @Test
    void testSimpleClassWithInterfaces() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithInterfaces");
        JavaClassGen gen = new JavaClassGen(clazz);
        MethodGen[] methods = gen.getMethods();
        FieldGen[] fields = gen.getFields();
        String[] interfaces = gen.getInterfaceNames();
        ConstantPool constantPool = gen.getConstantPool();
        Constant[] constantPoolArray = constantPool.getConstantPool();

        assertNotNull(clazz.toString());
        assertNotNull(clazz.hashCode());
        assertNotNull(gen.toString());
        assertNotNull(gen.hashCode());
        assertNotNull(methods);
        assertEquals(2, methods.length);
        assertEquals(15, fields.length);
        assertEquals(1, interfaces.length);
        assertNotNull(methods[0]);
        assertNotNull(methods[0].toString());
        assertNotNull(methods[0].hashCode());
        assertNotNull(methods[1]);
        assertNotNull(methods[1].toString());
        assertNotNull(methods[1].hashCode());
        assertTrue(methods[1].getAccessFlags().isStatic());
        assertEquals(54, constantPoolArray.length);
    }
}
