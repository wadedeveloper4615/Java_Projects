package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.ModulePackages;
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
public class ModulePackagesGen extends AttributeGen {
    private List<String> packageIndexNames;

    public ModulePackagesGen(ModulePackages attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        packageIndexNames = new ArrayList<>();
        for (Integer entry : attribute.getPackageIndexTable()) {
            packageIndexNames.add(constantPool.constantToString(entry, ClassFileConstants.CONSTANT_Package));
        }
    }
}
