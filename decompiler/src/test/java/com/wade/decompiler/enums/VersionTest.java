package com.wade.decompiler.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionTest {
    @Test
    void testGetMajor() {
        assertEquals(58, Version.Version_14.getMajor());
    }

    @Test
    void testGetMinor() {
        assertEquals(0, Version.Version_14.getMinor());
    }

    @Test
    void testGetVersionString() {
        assertEquals("14", Version.Version_14.getVersionString());
    }

    @Test
    void testRead1() {
        assertEquals(Version.Version_14, Version.read(0, 58));
    }

    @Test
    void testRead2() {
        assertEquals(Version.Unknown, Version.read(100, 0));
    }

    @Test
    void testRead3() {
        assertEquals(Version.Unknown, Version.read(58, 20));
    }
}
