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
        assertEquals(1, javaClass.getClassNameIndex());
        assertEquals(3, javaClass.getSuperclassNameIndex());
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
    void test10() throws Exception {
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
    void test11() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setClassNameIndex(99);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test12() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setConstantPool(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test13() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setConstantPool(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test14() throws Exception {
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
    void test15() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setFields(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test2() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        assertFalse(javaClass1.equals(null));
    }

    @Test
    void test3() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        assertTrue(javaClass1.equals(javaClass1));
    }

    @Test
    void test4() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void test5() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAccessFlags(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test6() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setAccessFlags(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test7() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAccessFlags(null);
        javaClass2.setAccessFlags(null);
        assertTrue(javaClass1.equals(javaClass2));
    }

    @Test
    void test8() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass1.setAttributes(null);
        assertFalse(javaClass1.equals(javaClass2));
    }

    @Test
    void test9() throws Exception {
        Class<Decompiler> c = Decompiler.class;
        Decompiler decompiler = new Decompiler();
        String resource = "/com/wade/decompiler/test/Test1.class";
        JavaClass javaClass1 = decompiler.process(c, resource);
        JavaClass javaClass2 = decompiler.process(c, resource);
        javaClass2.setAttributes(null);
        assertFalse(javaClass1.equals(javaClass2));
    }
}
