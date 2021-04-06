package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.EnclosingMethod;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
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
public class EnclosingMethodGen extends AttributeGen {
    private String superName;
    private String methodName;
    private String signature;

    public EnclosingMethodGen(EnclosingMethod attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        int classIndex = attribute.getClassIndex();
        int mehtodIndex = attribute.getMethodIndex();

        ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
        ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(mehtodIndex, ClassFileConstants.CONSTANT_NameAndType);

        superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
