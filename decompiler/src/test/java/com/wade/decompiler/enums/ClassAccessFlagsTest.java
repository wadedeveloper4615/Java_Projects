package com.wade.decompiler.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClassAccessFlagsTest {
    @Test
    void testGetFlag1() {
        assertEquals(0x0400, ClassAccessFlags.ACC_ABSTRACT.getFlag());
    }

    @Test
    void testGetName() {
        assertEquals("abstract", ClassAccessFlags.ACC_ABSTRACT.getName());
    }

    @Test
    void testIsAbstract() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_ABSTRACT;
        assertTrue(flag.isAbstract());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isAbstract());
    }

    @Test
    void testIsAnnotation() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_ANNOTATION;
        assertTrue(flag.isAnnotation());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isAnnotation());
    }

    @Test
    void testIsEnum() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_ENUM;
        assertTrue(flag.isEnum());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isEnum());
    }

    @Test
    void testIsFinal() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_FINAL;
        assertTrue(flag.isFinal());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isFinal());
    }

    @Test
    void testIsInterface() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_INTERFACE;
        assertTrue(flag.isInterface());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isInterface());
    }

    @Test
    void testIsMandated() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_MANDATED;
        assertTrue(flag.isMandated());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isMandated());
    }

    @Test
    void testIsNative() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_NATIVE;
        assertTrue(flag.isNative());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isNative());
    }

    @Test
    void testIsPrivate() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_PRIVATE;
        assertTrue(flag.isPrivate());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isPrivate());
    }

    @Test
    void testIsProtected() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_PROTECTED;
        assertTrue(flag.isProtected());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isProtected());
    }

    @Test
    void testIsPublic() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_PUBLIC;
        assertTrue(flag.isPublic());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isPublic());
    }

    @Test
    void testIsSetInt() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_PROTECTED;
        assertTrue(flag.isSet(ClassAccessFlags.ACC_PROTECTED.getFlag()));
        assertFalse(flag.isSet(ClassAccessFlags.ACC_PUBLIC.getFlag()));
    }

    @Test
    void testIsStatic() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_STATIC;
        assertTrue(flag.isStatic());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isStatic());
    }

    @Test
    void testIsStrictfp() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_STRICT;
        assertTrue(flag.isStrictfp());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isStrictfp());
    }

    @Test
    void testIsSuper() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_SUPER;
        assertTrue(flag.isSuper());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isSuper());
    }

    @Test
    void testIsSynchronized() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_SYNCHRONIZED;
        assertTrue(flag.isSynchronized());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isSynchronized());
    }

    @Test
    void testIsSynthetic() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_SYNTHETIC;
        assertTrue(flag.isSynthetic());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isSynthetic());
    }

    @Test
    void testIsTransient() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_TRANSIENT;
        assertTrue(flag.isTransient());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isTransient());
    }

    @Test
    void testIsVarArgs() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_VARARGS;
        assertTrue(flag.isVarArgs());
        flag = ClassAccessFlags.ACC_BRIDGE;
        assertFalse(flag.isVarArgs());
    }

    @Test
    void testIsVolatile() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_VOLATILE;
        assertTrue(flag.isVolatile());
        flag = ClassAccessFlags.ACC_PUBLIC;
        assertFalse(flag.isVolatile());
    }

    @Test
    void testRead() {
        ClassAccessFlags flag = ClassAccessFlags.read(ClassAccessFlags.ACC_PROTECTED.getFlag());
        assertTrue(flag.isProtected());
    }

    @Test
    void testSetFlag() {
        ClassAccessFlags flag = ClassAccessFlags.ACC_VOLATILE;
        flag.setFlag(6);
        assertTrue(flag.isVolatile());
    }

}
