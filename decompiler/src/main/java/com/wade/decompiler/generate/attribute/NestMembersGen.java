package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.NestMembers;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class NestMembersGen extends AttributeGen {
    private List<String> names;

    public NestMembersGen(NestMembers attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        List<Integer> classes = attribute.getClasses();
        names = new ArrayList<>();
        for (Integer entry : attribute.getClasses()) {
            names.add(constantPool.constantToString(entry, ClassFileConstants.CONSTANT_Class));
        }
    }
}
