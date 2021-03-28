package com.wade.decompiler.classfile;

import com.wade.decompiler.AbstractTest;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.Unknown;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.Version;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JavaClassTest extends AbstractTest {
    @Test
    void test1() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        assertNotNull(clazz.hashCode());
        assertNotNull(clazz.toString());
        EqualsVerifier.simple().forClass(clazz.getClass()).verify();

        assertNotNull(clazz.getClassNameIndex());
        assertNotNull(clazz.getSuperclassNameIndex());
        assertNotNull(clazz.getFileName());
        assertNotNull(clazz.getVersion());
        assertNotNull(clazz.getAccessFlags());
        assertNotNull(clazz.getConstantPool());
        assertNotNull(clazz.getInterfaces());
        assertNotNull(clazz.getFields());
        assertNotNull(clazz.getMethods());
        assertNotNull(clazz.getAttributes());
        assertNotNull(clazz.getRepository());
    }

    @Test
    void test10() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), new ConstantPool(), new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test11() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        int[] interfaces = new int[1];
        interfaces[0] = 1;
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, interfaces, new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test12() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        Field[] field = new Field[1];
        field[0] = new Field();
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], field, new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test13() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        Method[] method = new Method[1];
        method[0] = new Method();
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], method, new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test14() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        Attribute[] attribute = new Attribute[1];
        attribute[0] = new Unknown();
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], attribute);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test2() throws Exception {
        JavaClass clazz = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        assertNotNull(clazz.hashCode());
        assertNotNull(clazz.toString());
        EqualsVerifier.simple().forClass(clazz.getClass()).verify();

        assertNotNull(clazz.getClassNameIndex());
        assertNotNull(clazz.getSuperclassNameIndex());
        assertNotNull(clazz.getFileName());
        assertNotNull(clazz.getVersion());
        assertNotNull(clazz.getAccessFlags());
        assertNotNull(clazz.getConstantPool());
        assertNotNull(clazz.getInterfaces());
        assertNotNull(clazz.getFields());
        assertNotNull(clazz.getMethods());
        assertNotNull(clazz.getAttributes());
        assertNotNull(clazz.getRepository());

        JavaClass clazz2 = getTestClass(PACKAGE_BASE_NAME + ".data.test.SimpleClass");
        assertNotNull(clazz2.hashCode());
        assertNotNull(clazz2.toString());
        EqualsVerifier.simple().forClass(clazz2.getClass()).verify();

        assertNotNull(clazz2.getClassNameIndex());
        assertNotNull(clazz2.getSuperclassNameIndex());
        assertNotNull(clazz2.getFileName());
        assertNotNull(clazz2.getVersion());
        assertNotNull(clazz2.getAccessFlags());
        assertNotNull(clazz2.getConstantPool());
        assertNotNull(clazz2.getInterfaces());
        assertNotNull(clazz2.getFields());
        assertNotNull(clazz2.getMethods());
        assertNotNull(clazz2.getAttributes());
        assertNotNull(clazz2.getRepository());
    }

    @Test
    void test3() throws Exception {
        JavaClass clazz = new JavaClass();

        clazz.setClassNameIndex(1);
        clazz.setSuperclassNameIndex(2);
        clazz.setFileName("");
        clazz.setVersion(Version.Version_1_1);
        clazz.setAccessFlags(new ClassAccessFlagsList(5));
        clazz.setConstantPool(null);
        clazz.setInterfaces(null);
        clazz.setFields(null);
        clazz.setMethods(null);
        clazz.setAttributes(null);
        clazz.setRepository(null);
    }

    @Test
    void test4() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test5() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(2, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test6() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(1, 3, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test7() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(1, 2, "ff", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test8() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_2, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }

    @Test
    void test9() throws Exception {
        JavaClass clazz1 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0001), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        JavaClass clazz2 = new JavaClass(1, 2, "", Version.Version_1_1, new ClassAccessFlagsList(0x0002), null, new int[0], new Field[0], new Method[0], new Attribute[0]);
        EqualsVerifier.simple().forClasses(clazz1.getClass(), clazz2.getClass()).verify();
    }
}
