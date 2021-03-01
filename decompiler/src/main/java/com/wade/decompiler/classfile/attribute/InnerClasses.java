package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class InnerClasses extends Attribute {
    private InnerClass[] innerClasses;

    public InnerClasses(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (InnerClass[]) null, constantPool);
        int number_of_classes = input.readUnsignedShort();
        innerClasses = new InnerClass[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            innerClasses[i] = new InnerClass(input);
        }
    }

    public InnerClasses(int nameIndex, int length, InnerClass[] innerClasses, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_INNER_CLASSES, nameIndex, length, constantPool);
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }
}
