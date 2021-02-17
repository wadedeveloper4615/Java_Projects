package com.wade.decompiler.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClassAccessFlagsListTest {
    private void check2Flags(ClassAccessFlags flag1, ClassAccessFlags flag2) {
        int flag = flag1.getFlag() | flag2.getFlag();
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(flag);
        assertEquals(flagsList.getFlagsList().get(0).getFlag(), flag1.getFlag());
        assertEquals(flagsList.getFlagsList().get(1).getFlag(), flag2.getFlag());
    }

    private void checkFlag(ClassAccessFlags flag) {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(flag.getFlag());
        assertEquals(flagsList.getFlagsList().get(0).getFlag(), flag.getFlag());
    }

    @Test
    void testFlags1FlagAtATime() {
        checkFlag(ClassAccessFlags.ACC_PUBLIC);
        checkFlag(ClassAccessFlags.ACC_PRIVATE);
        checkFlag(ClassAccessFlags.ACC_PROTECTED);
        checkFlag(ClassAccessFlags.ACC_STATIC);
        checkFlag(ClassAccessFlags.ACC_FINAL);
        checkFlag(ClassAccessFlags.ACC_OPEN);
        checkFlag(ClassAccessFlags.ACC_SUPER);
        checkFlag(ClassAccessFlags.ACC_SYNCHRONIZED);
        checkFlag(ClassAccessFlags.ACC_TRANSITIVE);
        checkFlag(ClassAccessFlags.ACC_BRIDGE);
        checkFlag(ClassAccessFlags.ACC_STATIC_PHASE);
        checkFlag(ClassAccessFlags.ACC_VOLATILE);
        checkFlag(ClassAccessFlags.ACC_TRANSIENT);
        checkFlag(ClassAccessFlags.ACC_VARARGS);
        checkFlag(ClassAccessFlags.ACC_NATIVE);
        checkFlag(ClassAccessFlags.ACC_INTERFACE);
        checkFlag(ClassAccessFlags.ACC_ABSTRACT);
        checkFlag(ClassAccessFlags.ACC_STRICT);
        checkFlag(ClassAccessFlags.ACC_SYNTHETIC);
        checkFlag(ClassAccessFlags.ACC_ANNOTATION);
        checkFlag(ClassAccessFlags.ACC_ENUM);
        checkFlag(ClassAccessFlags.ACC_MANDATED);
        checkFlag(ClassAccessFlags.ACC_MODULE);
    }

    @Test
    void testFlags2FlagAtATime() {
        check2Flags(ClassAccessFlags.ACC_PUBLIC, ClassAccessFlags.ACC_PRIVATE);
    }

    @Test
    void testIsSet0() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_PUBLIC.getFlag());
        assertFalse(flagsList.isPrivate());
    }

    @Test
    void testIsSet1() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_PUBLIC.getFlag());
        assertTrue(flagsList.isPublic());
    }

    @Test
    void testIsSet10() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_INTERFACE.getFlag());
        assertTrue(flagsList.isInterface());
    }

    @Test
    void testIsSet11() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_MANDATED.getFlag());
        assertTrue(flagsList.isMandated());
    }

    @Test
    void testIsSet12() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_NATIVE.getFlag());
        assertTrue(flagsList.isNative());
    }

    @Test
    void testIsSet13() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_STATIC.getFlag());
        assertTrue(flagsList.isStatic());
    }

    @Test
    void testIsSet14() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_STRICT.getFlag());
        assertTrue(flagsList.isStrictfp());
    }

    @Test
    void testIsSet15() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_SUPER.getFlag());
        assertTrue(flagsList.isSuper());
    }

    @Test
    void testIsSet2() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_PROTECTED.getFlag());
        assertTrue(flagsList.isProtected());
    }

    @Test
    void testIsSet3() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_PRIVATE.getFlag());
        assertTrue(flagsList.isPrivate());
    }

    @Test
    void testIsSet4() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_ABSTRACT.getFlag());
        assertTrue(flagsList.isAbstract());
    }

    @Test
    void testIsSet5() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_ANNOTATION.getFlag());
        assertTrue(flagsList.isAnnotation());
    }

    @Test
    void testIsSet6() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_ENUM.getFlag());
        assertTrue(flagsList.isEnum());
    }

    @Test
    void testIsSet7() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_FINAL.getFlag());
        assertTrue(flagsList.isFinal());
    }

    @Test
    void testIsSet8() {
        int flag = ClassAccessFlags.ACC_FINAL.getFlag() | ClassAccessFlags.ACC_ABSTRACT.getFlag();
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(flag);
        assertTrue(flagsList.isFinalAndAbstract());
    }

    @Test
    void testIsSet9() {
        int flag = ClassAccessFlags.ACC_FINAL.getFlag() | ClassAccessFlags.ACC_PUBLIC.getFlag();
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(flag);
        assertFalse(flagsList.isFinalAndAbstract());
    }
}
