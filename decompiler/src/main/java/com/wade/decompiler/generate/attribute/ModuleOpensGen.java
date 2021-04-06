package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ModuleOpens;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
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
public class ModuleOpensGen {
    private String package_name;
    private List<String> opensToIndex;
    private ClassAccessFlagsList opensFlags;

    public ModuleOpensGen(ModuleOpens attribute, ConstantPool constantPool) {
        this.package_name = constantPool.constantToString(attribute.getOpensIndex(), ClassFileConstants.CONSTANT_Package);
        this.opensFlags = new ClassAccessFlagsList(attribute.getOpensFlags());
        this.opensToIndex = new ArrayList<>();
        for (Integer entry : attribute.getOpensToIndex()) {
            this.opensToIndex.add(constantPool.constantToString(entry, ClassFileConstants.CONSTANT_Module));
        }
    }
}
