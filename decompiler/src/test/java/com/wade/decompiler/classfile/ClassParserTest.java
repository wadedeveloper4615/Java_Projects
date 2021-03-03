package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.io.DataInput;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFieldRef;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantInteger;
import com.wade.decompiler.classfile.constant.ConstantInterfaceMethodRef;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantString;
import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;

@ExtendWith(MockitoExtension.class)
class ClassParserTest {
    @Mock
    private DataInput inputStream;

    @Test
    void testReadConstantPool_ConstantClass() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        .thenReturn(3) //nameIndex for constant class
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_Class.getTag()) //read constantclass tag
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
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        .thenReturn(3) //classIndex for constant field ref
        .thenReturn(4) //nameAndTypeIndex for constant field ref
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_Fieldref.getTag()) //read constantclass tag
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
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_Float.getTag()) //read constantclass tag
        ;
        when(inputStream.readFloat())
        .thenReturn(5.0f) //read constantclass tag
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
        assertEquals(5.0f, constantFieldRef.getConstantValue(null));
    }

    @Test
    void testReadConstantPool_ConstantInteger() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_Integer.getTag()) //read constantclass tag
        ;
        when(inputStream.readInt())
        .thenReturn(5) //read constantclass tag
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
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        .thenReturn(3) //classIndex for constant field ref
        .thenReturn(4) //nameAndTypeIndex for constant field ref
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_InterfaceMethodref.getTag()) //read constantclass tag
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
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        .thenReturn(3) //classIndex for constant field ref
        .thenReturn(4) //nameAndTypeIndex for constant field ref
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_Methodref.getTag()) //read constantclass tag
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
        when(inputStream.readUnsignedShort())
        .thenReturn(2) //the number of constants+1
        .thenReturn(3) //stringIndex for constant field ref
        ;
        when(inputStream.readByte())
        .thenReturn(ClassFileConstants.CONSTANT_String.getTag()) //read constantclass tag
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
    void testReadVersion() throws Exception {
        //@formatter:off
        when(inputStream.readUnsignedShort())
        .thenReturn(Version.Version_14.getMinor())
        .thenReturn(Version.Version_14.getMajor())
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.readVersion(inputStream);
        assertEquals(Version.Version_14, parser.getVersion());
    }
}
