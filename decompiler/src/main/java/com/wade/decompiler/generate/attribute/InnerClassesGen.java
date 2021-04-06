package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.attribute.InnerClasses;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InnerClassesGen extends AttributeGen {
    private InnerClassGen[] innerClasses;

    public InnerClassesGen(InnerClasses attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        InnerClass[] innerClasses = attribute.getInnerClasses();
        this.innerClasses = new InnerClassGen[innerClasses.length];
        for (int i = 0; i < innerClasses.length; i++) {
            this.innerClasses[i] = new InnerClassGen(innerClasses[i], constantPool);
        }
    }
}
