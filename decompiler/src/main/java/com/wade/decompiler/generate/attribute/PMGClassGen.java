package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.PMGClass;
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
public class PMGClassGen extends AttributeGen {
    private String pmg;
    private String pmgClass;

    public PMGClassGen(PMGClass attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.pmg = ((ConstantUtf8) constantPool.getConstant(attribute.getPmgIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.pmgClass = ((ConstantUtf8) constantPool.getConstant(attribute.getPmgClassIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
