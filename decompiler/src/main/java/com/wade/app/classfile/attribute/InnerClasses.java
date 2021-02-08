package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class InnerClasses extends Attribute {
    private InnerClass[] innerClasses;

    public InnerClasses(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (InnerClass[]) null, constant_pool);
         int number_of_classes = input.readUnsignedShort();
        innerClasses = new InnerClass[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            innerClasses[i] = new InnerClass(input);
        }
    }

    public InnerClasses(int name_index, int length, InnerClass[] innerClasses, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_INNER_CLASSES, name_index, length, constant_pool);
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }
}
