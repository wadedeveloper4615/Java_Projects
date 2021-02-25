package com.wade.decompiler.classfile;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.ConstantPool;

class MethodTest {

    @Test
    void testMethodIntIntIntAttributeArrayConstantPool() {
        Method field = new Method(1, 2, 3, new Attribute[] { new ConstantValue(0, 0, 0, null) }, new ConstantPool());
    }

}
