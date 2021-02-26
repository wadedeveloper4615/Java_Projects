package com.wade.decompiler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassAccessFlagsList;

public class UtilityTest {
    @Test
    void testAccessToStringClassAccessFlagsListBoolean1() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_STATIC);
        assertEquals("public static", Utility.accessToString(access_flags, false));
    }

    @Test
    void testAccessToStringClassAccessFlagsListBoolean2() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_STATIC);
        access_flags.addFlag(ClassAccessFlags.ACC_SUPER);
        access_flags.addFlag(ClassAccessFlags.ACC_INTERFACE);
        assertEquals("public static synchronized interface", Utility.accessToString(access_flags, false));
    }

    @Test
    void testAccessToStringClassAccessFlagsListBoolean3() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_STATIC);
        assertEquals("public static", Utility.accessToString(access_flags));
    }

    @Test
    void testAccessToStringClassAccessFlagsListBoolean4() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_STATIC);
        access_flags.addFlag(ClassAccessFlags.ACC_SUPER);
        access_flags.addFlag(ClassAccessFlags.ACC_INTERFACE);
        assertEquals("public static synchronized interface", Utility.accessToString(access_flags));
    }

    @Test
    void testAccessToStringClassAccessFlagsListBoolean5() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_STATIC);
        access_flags.addFlag(ClassAccessFlags.ACC_SUPER);
        access_flags.addFlag(ClassAccessFlags.ACC_INTERFACE);
        assertEquals("public static", Utility.accessToString(access_flags, true));
    }

    @Test
    void testClassTypeAnnotation() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_INTERFACE);
        access_flags.addFlag(ClassAccessFlags.ACC_ANNOTATION);
        access_flags.addFlag(ClassAccessFlags.ACC_ABSTRACT);
        assertEquals("@interface", Utility.classType(access_flags));
    }

    @Test
    void testClassTypeClass() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        assertEquals("class", Utility.classType(access_flags));
    }

    @Test
    void testClassTypeInterface() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        access_flags.addFlag(ClassAccessFlags.ACC_INTERFACE);
        assertEquals("interface", Utility.classType(access_flags));
    }

    @Test
    void testClassTypePublic() {
        ClassAccessFlagsList access_flags = new ClassAccessFlagsList();
        access_flags.addFlag(ClassAccessFlags.ACC_PUBLIC);
        assertEquals("class", Utility.classType(access_flags));
    }
}
