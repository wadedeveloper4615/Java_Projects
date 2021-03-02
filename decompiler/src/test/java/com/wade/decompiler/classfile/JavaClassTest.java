package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.AbstractTest;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.Version;

class JavaClassTest extends AbstractTest {
    @SuppressWarnings("unlikely-arg-type")
    @Test
    void test1() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        assertNotNull(clazz.hashCode());
        assertNotNull(clazz.toString());
        assertTrue(clazz.equals(clazz));
        assertFalse(clazz.equals(""));
        assertFalse(clazz.equals(null));

        assertNotNull(clazz.getClassNameIndex());
        assertNotNull(clazz.getSuperclassNameIndex());
        assertNotNull(clazz.getFileName());
        assertNotNull(clazz.getVersion());
        assertNotNull(clazz.getAccessFlags());
        assertNotNull(clazz.getConstantPool());
        assertNotNull(clazz.getInterfaces());
        assertNotNull(clazz.getFields());
        assertNotNull(clazz.getMethods());
        assertNotNull(clazz.getAttributes());
        assertNotNull(clazz.getRepository());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void test2() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        assertNotNull(clazz.hashCode());
        assertNotNull(clazz.toString());
        assertTrue(clazz.equals(clazz));
        assertFalse(clazz.equals(""));
        assertFalse(clazz.equals(null));

        assertNotNull(clazz.getClassNameIndex());
        assertNotNull(clazz.getSuperclassNameIndex());
        assertNotNull(clazz.getFileName());
        assertNotNull(clazz.getVersion());
        assertNotNull(clazz.getAccessFlags());
        assertNotNull(clazz.getConstantPool());
        assertNotNull(clazz.getInterfaces());
        assertNotNull(clazz.getFields());
        assertNotNull(clazz.getMethods());
        assertNotNull(clazz.getAttributes());
        assertNotNull(clazz.getRepository());

        JavaClass clazz2 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        assertNotNull(clazz2.hashCode());
        assertNotNull(clazz2.toString());
        assertTrue(clazz2.equals(clazz2));
        assertTrue(clazz.equals(clazz2));
        assertFalse(clazz2.equals(""));
        assertFalse(clazz2.equals(null));

        assertNotNull(clazz2.getClassNameIndex());
        assertNotNull(clazz2.getSuperclassNameIndex());
        assertNotNull(clazz2.getFileName());
        assertNotNull(clazz2.getVersion());
        assertNotNull(clazz2.getAccessFlags());
        assertNotNull(clazz2.getConstantPool());
        assertNotNull(clazz2.getInterfaces());
        assertNotNull(clazz2.getFields());
        assertNotNull(clazz2.getMethods());
        assertNotNull(clazz2.getAttributes());
        assertNotNull(clazz2.getRepository());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void test3() throws Exception {
        JavaClass clazz = new JavaClass();

        clazz.setClassNameIndex(1);
        clazz.setSuperclassNameIndex(2);
        clazz.setFileName("");
        clazz.setVersion(Version.Version_1_1);
        clazz.setAccessFlags(new ClassAccessFlagsList(5));
        clazz.setConstantPool(null);
        clazz.setInterfaces(null);
        clazz.setFields(null);
        clazz.setMethods(null);
        clazz.setAttributes(null);
        clazz.setRepository(null);
    }
}
