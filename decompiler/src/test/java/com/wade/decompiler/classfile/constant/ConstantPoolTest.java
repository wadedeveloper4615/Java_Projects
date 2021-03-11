package com.wade.decompiler.classfile.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.AbstractTest;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.enums.ClassFileConstants;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

class ConstantPoolTest extends AbstractTest {
    private Constant searchConstantPool(Constant[] constantPool, ClassFileConstants constant) {
        int i = 0;
        for (Constant index : constantPool) {
            if (index != null && index.getTag() == constant) {
                System.out.println("i=" + i);
                return index;
            }
            i++;
        }
        return null;
    }

    @Test
    void testDouble() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_Double);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantDouble);
        ConstantDouble c = (ConstantDouble) constant;
        assertEquals("6.0", constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testFloat() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_Float);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantFloat);
        ConstantFloat c = (ConstantFloat) constant;
        assertEquals("8.0", constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testInteger() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_Integer);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantInteger);
        ConstantInteger c = (ConstantInteger) constant;
        assertEquals("250000", constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testInterfaceMethod() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_InterfaceMethodref);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantInterfaceMethodRef);
        ConstantInterfaceMethodRef c = (ConstantInterfaceMethodRef) constant;
        assertNotNull(constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testInvokeDynamic() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_InvokeDynamic);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantInvokeDynamic);
        ConstantInvokeDynamic c = (ConstantInvokeDynamic) constant;
        assertNotNull(constantPool.constantToString(c));
        assertNotNull(c.getNameAndTypeIndex());
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testInvokeMethodType() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_MethodType);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantMethodType);
        ConstantMethodType c = (ConstantMethodType) constant;
        assertNotNull(constantPool.constantToString(c));
        assertNotNull(c.getDescriptorIndex());
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testLong() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_Long);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantLong);
        ConstantLong c = (ConstantLong) constant;
        assertEquals("7", constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testMethod() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_Methodref);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantMethodref);
        ConstantMethodref c = (ConstantMethodref) constant;
        assertNotNull(constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testNameAndType() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_NameAndType);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantNameAndType);
        ConstantNameAndType c = (ConstantNameAndType) constant;
        assertNotNull(constantPool.constantToString(c));
        assertNotNull(c.getNameIndex());
        assertNotNull(c.getSignatureIndex());
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testString() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_String);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantString);
        ConstantString c = (ConstantString) constant;
        assertNotNull(constantPool.constantToString(c));
        assertNotNull(c.getStringIndex());
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    void testUtf8() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClassWithConstantPool");
        assertNotNull(clazz);
        ConstantPool constantPool = clazz.getConstantPool();
        System.out.println(constantPool.toString());
        assertNotNull(constantPool);
        assertNotNull(constantPool.getConstantPool());
        assertNotNull(constantPool.hashCode());
        assertFalse(constantPool.canEqual(null));
        assertTrue(constantPool.canEqual(constantPool));
        Constant constant = searchConstantPool(constantPool.getConstantPool(), ClassFileConstants.CONSTANT_Utf8);
        assertNotNull(constant);
        assertTrue(constant instanceof ConstantUtf8);
        ConstantUtf8 c = (ConstantUtf8) constant;
        assertNotNull(constantPool.constantToString(constant));
        EqualsVerifier.forClass(c.getClass()).suppress(Warning.NONFINAL_FIELDS).suppress(Warning.STRICT_INHERITANCE).verify();
    }
}
