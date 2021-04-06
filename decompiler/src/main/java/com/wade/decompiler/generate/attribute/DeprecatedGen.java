package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.Deprecated;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class DeprecatedGen extends AttributeGen {
    private byte[] bytes;

    public DeprecatedGen(Deprecated attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.bytes = attribute.getBytes();
    }
}
