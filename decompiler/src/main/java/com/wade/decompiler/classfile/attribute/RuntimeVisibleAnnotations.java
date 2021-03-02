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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class RuntimeVisibleAnnotations extends Annotations {
    public RuntimeVisibleAnnotations(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_ANNOTATIONS, nameIndex, length, input, constantPool, true);
    }
}
