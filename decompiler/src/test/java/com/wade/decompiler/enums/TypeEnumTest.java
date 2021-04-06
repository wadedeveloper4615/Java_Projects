package com.wade.decompiler.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TypeEnumTest {
    @Test
    void testClassFileAttributes() {
        assertEquals(4, TypeEnum.T_BOOLEAN.getTag());
    }

    @Test
    void testGetName() {
        assertEquals("boolean", TypeEnum.T_BOOLEAN.getTypeName());
    }

    @Test
    void testGetShortTypeName() {
        assertEquals("Z", TypeEnum.T_BOOLEAN.getShortTypeName());
    }

    @Test
    void testGetTag() {
        assertEquals(4, TypeEnum.T_BOOLEAN.getTag());
    }

    @Test
    void testGetTypeName() {
        assertEquals("java.lang.Boolean", TypeEnum.T_BOOLEAN.getClassTypeName());
    }

    @Test
    void testRead1() {
        assertEquals(TypeEnum.T_BOOLEAN, TypeEnum.read((byte) 4));
    }

    @Test
    void testRead2() {
        assertEquals(TypeEnum.T_UNKNOWN, TypeEnum.read((byte) 100));
    }
}
