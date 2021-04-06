package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.SourceFile;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SourceFileGen extends AttributeGen {
    private String sourceFile;

    public SourceFileGen(SourceFile attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.sourceFile = ((ConstantUtf8) constantPool.getConstant(attribute.getSourceFileIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
