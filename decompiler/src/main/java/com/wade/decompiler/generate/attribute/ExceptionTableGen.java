package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ExceptionTable;
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
public class ExceptionTableGen extends AttributeGen {
    private String[] names;

    public ExceptionTableGen(ExceptionTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int[] exceptionIndexTable = attribute.getExceptionIndexTable();
        this.names = new String[exceptionIndexTable.length];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            this.names[i] = constantPool.constantToString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class);
        }
    }
}
