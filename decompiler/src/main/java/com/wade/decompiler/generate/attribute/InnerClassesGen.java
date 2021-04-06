package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.attribute.InnerClasses;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InnerClassesGen extends AttributeGen {
    private List<InnerClassGen> innerClasses;

    public InnerClassesGen(InnerClasses attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        innerClasses = new ArrayList<>();
        for (InnerClass entry : attribute.getInnerClasses()) {
            innerClasses.add(new InnerClassGen(entry, constantPool));
        }
    }
}
