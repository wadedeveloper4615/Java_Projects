package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.AnnotationDefault;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class AnnotationDefaultGen extends AttributeGen {
    private ElementValueGen defaultValue;

    public AnnotationDefaultGen(AnnotationDefault attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        defaultValue = ElementValueGen.readElementValue(attribute.getDefaultValue(), constantPool);
    }
}
