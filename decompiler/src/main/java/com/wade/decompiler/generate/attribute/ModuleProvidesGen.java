package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.ModuleProvides;
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
public class ModuleProvidesGen {
    private List<String> providesIndex;
    private String interface_name;

    public ModuleProvidesGen(ModuleProvides attribute, ConstantPool constantPool) {
        this.interface_name = constantPool.constantToString(attribute.getProvidesIndex(), ClassFileConstants.CONSTANT_Class);
        this.providesIndex = new ArrayList<>();
        for (Integer entry : attribute.getProvidesWithIndex()) {
            this.providesIndex.add(constantPool.constantToString(entry, ClassFileConstants.CONSTANT_Module));
        }
    }
}
