package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.ConstantPool;

@DisplayName("Decompiler JUnit 5 Field Test")
class FieldTest {
    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testFieldIntIntIntAttributeArrayConstantPool() {
        Field field = new Field(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
        assertNotNull(field.hashCode());
        assertNotNull(field.toString());
        assertTrue(field.equals(field));
        assertFalse(field.equals(""));
        assertFalse(field.equals(null));
    }
}
