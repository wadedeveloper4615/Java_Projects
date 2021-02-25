package com.wade.decompiler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.enums.Version;

@ExtendWith(MockitoExtension.class)
@DisplayName("Decompiler JUnit 5 test")
class DecompilerTest {
    @Test
    void test1() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass = decompiler.process(c, resource);
        assertNotNull(javaClass);
        assertNotNull(javaClass.getClassNameIndex());
        assertNotNull(javaClass.getSuperclassNameIndex());
        assertNotNull(javaClass.getFileName());
        assertEquals(Version.Version_14, javaClass.getVersion());
        assertEquals(33, javaClass.getAccessFlags().getFlags());
        assertEquals(50, javaClass.getConstantPool().getConstantPool().length);
        assertEquals(1, javaClass.getInterfaces().length);
        assertEquals(15, javaClass.getFields().length);
        assertEquals(1, javaClass.getMethods().length);
        assertEquals(1, javaClass.getAttributes().length);
        assertTrue(javaClass.equals(javaClass));
        assertNotNull(javaClass.hashCode());
        assertNotNull(javaClass.toString());
    }

    @Test
    void testEquals1() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAttributes(null);
        javaClass2.setAttributes(null);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals10() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAttributes(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals11() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setFields(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals12() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setFileName(null);
        assertFalse(javaClass1.equals(javaClass2));
        assertNotNull(javaClass1.hashCode());
    }

    @Test
    void testEquals13() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setFileName(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals14() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setFileName(null);
        javaClass2.setFileName(null);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals15() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setInterfaces(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals16() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setInterfaces(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals17() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setInterfaces(null);
        javaClass2.setInterfaces(null);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals18() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setMethods(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals19() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setMethods(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals2() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setClassNameIndex(99);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals20() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setMethods(null);
        javaClass2.setMethods(null);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals21() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setSuperclassNameIndex(-1);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals22() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setVersion(null);
        assertFalse(javaClass1.equals(javaClass2));
        assertNotNull(javaClass1.hashCode());
    }

    @Test
    void testEquals23() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        javaClass1.setVersion(null);
        assertFalse(javaClass1.equals(""));
    }

    @Test
    void testEquals3() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setConstantPool(null);
        assertFalse(javaClass1.equals(javaClass2));
        assertNotNull(javaClass1.hashCode());
    }

    @Test
    void testEquals4() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setConstantPool(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals5() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setConstantPool(null);
        javaClass2.setConstantPool(null);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals6() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        assertFalse(javaClass1.equals(null));
    }

    @Test
    void testEquals7() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAccessFlags(null);
        assertFalse(javaClass1.equals(javaClass2));
        assertNotNull(javaClass1.hashCode());
    }

    @Test
    void testEquals8() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setAccessFlags(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void testEquals9() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAccessFlags(null);
        javaClass2.setAccessFlags(null);
        assertTrue(javaClass1.equals(javaClass2));
    }
}
