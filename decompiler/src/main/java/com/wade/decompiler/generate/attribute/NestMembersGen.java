package com.wade.decompiler.generate.attribute;

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
    private String[] names;

    public NestMembersGen(NestMembers attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int[] classes = attribute.getClasses();
        this.names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            this.names[i] = constantPool.constantToString(classes[i], ClassFileConstants.CONSTANT_Class);
        }
    }
}
