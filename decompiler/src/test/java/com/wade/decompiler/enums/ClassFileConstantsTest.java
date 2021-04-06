package com.wade.decompiler.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassFileConstantsTest {
    @Test
    void testClassFileAttributes() {
        assertEquals(3, ClassFileConstants.CONSTANT_Integer.getTag());
    }

    @Test
    void testGetName() {
        assertEquals("CONSTANT_Integer", ClassFileConstants.CONSTANT_Integer.getName());
    }

    @Test
    void testGetTag() {
        assertEquals(3, ClassFileConstants.CONSTANT_Integer.getTag());
    }

    @Test
    void testRead1() {
        assertEquals(ClassFileConstants.CONSTANT_Integer, ClassFileConstants.read((byte) 3));
    }

    @Test
    void testRead2() {
        assertEquals(ClassFileConstants.CONSTANT_UNKNOWN, ClassFileConstants.read((byte) 100));
    }
}
