package com.wade.decompiler.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionsOpCodesTest {
    @Test
    void testClassFileAttributes() {
        assertEquals(3, InstructionOpCodes.ICONST_0.getOpcode());
    }

    @Test
    void testGetConsumeStack() {
        assertEquals(0, InstructionOpCodes.ICONST_0.getConsumeStack());
    }

    @Test
    void testGetName() {
        assertEquals("iconst_0", InstructionOpCodes.ICONST_0.getName());
    }

    @Test
    void testGetNumberOfOperands() {
        assertEquals((short) 0, InstructionOpCodes.ICONST_0.getNumberOfOperands());
    }

    @Test
    void testGetProduceStack() {
        assertEquals((short) 1, InstructionOpCodes.ICONST_0.getProduceStack());
    }

    @Test
    void testGetTag() {
        assertEquals(3, InstructionOpCodes.ICONST_0.getOpcode());
    }

    @Test
    void testGetTypeOfOperands() {
        assertEquals((short) 0, InstructionOpCodes.ICONST_0.getTypeOfOperands().length);
    }

    @Test
    void testRead1() {
        assertEquals(InstructionOpCodes.ICONST_0, InstructionOpCodes.read((byte) 3));
    }

    @Test
    void testRead2() {
        assertEquals(null, InstructionOpCodes.read((byte) 500));
    }
}
