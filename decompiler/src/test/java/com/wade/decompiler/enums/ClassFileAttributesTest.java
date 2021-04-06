package com.wade.decompiler.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassFileAttributesTest {
    @Test
    void testClassFileAttributes() {
        assertEquals(2, ClassFileAttributes.ATTR_CODE.getTag());
    }

    @Test
    void testGetName() {
        assertEquals("Code", ClassFileAttributes.ATTR_CODE.getName());
    }

    @Test
    void testGetTag() {
        assertEquals(2, ClassFileAttributes.ATTR_CODE.getTag());
    }

    @Test
    void testRead1() {
        assertEquals(ClassFileAttributes.ATTR_CODE, ClassFileAttributes.read(2));
    }

    @Test
    void testRead2() {
        assertEquals(ClassFileAttributes.ATTR_UNKNOWN, ClassFileAttributes.read(100));
    }
}
