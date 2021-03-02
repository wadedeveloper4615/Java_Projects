package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.AbstractTest;

class JavaClassTest extends AbstractTest {
    @SuppressWarnings("unlikely-arg-type")
    @Test
    void test() throws Exception {
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
}
