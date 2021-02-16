package com.wade.decompiler.enums;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClassAccessFlagsListTest {
    private void check2Flags(ClassAccessFlags flag1, ClassAccessFlags flag2) {
        int flag = flag1.getFlag() | flag2.getFlag();
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(flag);
        assertTrue(flagsList.getFlagsList().get(0).getFlag() == flag1.getFlag());
        assertTrue(flagsList.getFlagsList().get(1).getFlag() == flag2.getFlag());
    }

    private void checkFlag(ClassAccessFlags flag) {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(flag.getFlag());
        assertTrue(flagsList.getFlagsList().get(0).getFlag() == flag.getFlag());
    }

    @Test
    void testIsSet1() {
        ClassAccessFlagsList flagsList = new ClassAccessFlagsList(ClassAccessFlags.ACC_PUBLIC.getFlag());
        assertTrue(flagsList.isPublic());
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
}
