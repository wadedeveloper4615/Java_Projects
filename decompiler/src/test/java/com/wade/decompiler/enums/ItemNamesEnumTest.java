package com.wade.decompiler.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemNamesEnumTest {
    @Test
    void testClassFileAttributes() {
        assertEquals(1, ItemNamesEnum.ITEM_Integer.getTag());
    }

    @Test
    void testGetName() {
        assertEquals("Integer", ItemNamesEnum.ITEM_Integer.getName());
    }

    @Test
    void testGetTag() {
        assertEquals(1, ItemNamesEnum.ITEM_Integer.getTag());
    }

    @Test
    void testRead1() {
        assertEquals(ItemNamesEnum.ITEM_Integer, ItemNamesEnum.read((byte) 1));
    }

    @Test
    void testRead2() {
        assertEquals(null, ItemNamesEnum.read((byte) 100));
    }
}
