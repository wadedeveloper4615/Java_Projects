package com.wade.decompiler.classfile;

import com.wade.decompiler.classfile.constant.*;
import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.DataInput;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClassParserTest {
    @Mock
    private DataInput inputStream;

    @Test
    void testReadConstantPool_ConstantClass() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
                .thenReturn(3) //nameIndex for constant class
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_Class.getTag()) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_Class, constantPool[1].getTag());
        ConstantClass constantClass = (ConstantClass) constantPool[1];
        assertEquals(3, constantClass.getNameIndex());
    }

    @Test
    void testReadConstantPool_ConstantFieldRef() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
                .thenReturn(3) //classIndex for constant field ref
                .thenReturn(4) //nameAndTypeIndex for constant field ref
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_Fieldref.getTag()) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_Fieldref, constantPool[1].getTag());
        ConstantFieldRef constantFieldRef = (ConstantFieldRef) constantPool[1];
        assertEquals(3, constantFieldRef.getClassIndex());
        assertEquals(4, constantFieldRef.getNameAndTypeIndex());
    }

    @Test
    void testReadConstantPool_ConstantFloat() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_Float.getTag()) //read constantclass tag
        ;
        when(inputStream.readFloat()).thenReturn(5.0f) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_Float, constantPool[1].getTag());
        ConstantFloat constantFieldRef = (ConstantFloat) constantPool[1];
        assertEquals(5.0f, constantFieldRef.getBytes());
    }

    @Test
    void testReadConstantPool_ConstantInteger() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_Integer.getTag()) //read constantclass tag
        ;
        when(inputStream.readInt()).thenReturn(5) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_Integer, constantPool[1].getTag());
        ConstantInteger constantFieldRef = (ConstantInteger) constantPool[1];
        assertEquals(5, constantFieldRef.getBytes());
    }

    @Test
    void testReadConstantPool_ConstantInterfaceMethodRef() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
                .thenReturn(3) //classIndex for constant field ref
                .thenReturn(4) //nameAndTypeIndex for constant field ref
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_InterfaceMethodref.getTag()) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_InterfaceMethodref, constantPool[1].getTag());
        ConstantInterfaceMethodRef constantFieldRef = (ConstantInterfaceMethodRef) constantPool[1];
        assertEquals(3, constantFieldRef.getClassIndex());
        assertEquals(4, constantFieldRef.getNameAndTypeIndex());
    }

    @Test
    void testReadConstantPool_ConstantMethodRef() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
                .thenReturn(3) //classIndex for constant field ref
                .thenReturn(4) //nameAndTypeIndex for constant field ref
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_Methodref.getTag()) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_Methodref, constantPool[1].getTag());
        ConstantMethodref constantFieldRef = (ConstantMethodref) constantPool[1];
        assertEquals(3, constantFieldRef.getClassIndex());
        assertEquals(4, constantFieldRef.getNameAndTypeIndex());
    }

    @Test
    void testReadConstantPool_ConstantString() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(2) //the number of constants+1
                .thenReturn(3) //stringIndex for constant field ref
        ;
        when(inputStream.readByte()).thenReturn(ClassFileConstants.CONSTANT_String.getTag()) //read constantclass tag
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readConstantPool(inputStream);
        ConstantPool cp = parser.getConstantPool();
        assertNotNull(cp);
        Constant[] constantPool = cp.getConstantPool();
        assertNotNull(constantPool);
        assertEquals(2, constantPool.length);
        assertEquals(null, constantPool[0]);
        assertEquals(ClassFileConstants.CONSTANT_String, constantPool[1].getTag());
        ConstantString constantFieldRef = (ConstantString) constantPool[1];
        assertEquals(3, constantFieldRef.getStringIndex());
    }

    @Test
    void testReadField() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(1) //fields counts
                .thenReturn(2) //field access flags
                .thenReturn(3) //field name index
                .thenReturn(4) //field name signature index
                .thenReturn(0) //field name attribute count
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readFields(inputStream);
        assertNotNull(parser.getFields());
        assertEquals(1, parser.getFields().length);
    }

    @SuppressWarnings("unused")
    @Test
    void testReadId1() throws IOException {
        //@formatter:off
        when(inputStream.readInt()).thenReturn(0xCAFEBABF);
        //@formatter:on
        ClassParser parser = new ClassParser();
        try {
            parser.readID(inputStream);
        } catch (ClassFormatException e) {
            return;
        }
        fail("classs id read error");
    }

    @Test
    void testReadId2() throws Exception {
        //@formatter:off
        when(inputStream.readInt()).thenReturn(0xCAFEBABE);
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readID(inputStream);
    }

    @Test
    void testReadInterfaces() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(1) //interfaces counts
                .thenReturn(2) //interfaces name index
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readInterfaces(inputStream);
        assertNotNull(parser.getInterfaces());
        assertEquals(1, parser.getInterfaces().length);
    }

    @Test
    void testReadMethod() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(1) //methods counts
                .thenReturn(2) //methods access flags
                .thenReturn(3) //methods name index
                .thenReturn(4) //methods name signature index
                .thenReturn(0) //methods name attribute count
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readMethods(inputStream);
        assertNotNull(parser.getMethods());
        assertEquals(1, parser.getMethods().length);
    }

    @Test
    void testReadVersion() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort()).thenReturn(Version.Version_14.getMinor()).thenReturn(Version.Version_14.getMajor());
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readVersion(inputStream);
        assertEquals(Version.Version_14, parser.getVersion());
    }
}
