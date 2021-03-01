package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.ConstantPool;

class FieldOrMethodTest {
    @Test
    void testEqualsObject1() {
        FieldOrMethod field1 = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertTrue(field1.equals(field1));
        assertNotNull(field1.getAccessFlags());
        assertNotNull(field1.getAttributes());
        assertNotNull(field1.getConstantPool());
        assertNotNull(field1.getNameIndex());
        assertNotNull(field1.getSignatureIndex());
    }

}
