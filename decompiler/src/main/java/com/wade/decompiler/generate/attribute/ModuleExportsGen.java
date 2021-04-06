package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.ModuleExports;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ModuleExportsGen {
    private List<String> exports;
    private String package_name;
    private ClassAccessFlagsList exportFlags;

    public ModuleExportsGen(ModuleExports attribute, ConstantPool constantPool) {
        this.package_name = constantPool.constantToString(attribute.getExportsIndex(), ClassFileConstants.CONSTANT_Package);
        this.exportFlags = new ClassAccessFlagsList(attribute.getExportsFlags());
        this.exports = new ArrayList<>();
        for (Integer entry : attribute.getExportsToIndex()) {
            this.exports.add(constantPool.constantToString(entry, ClassFileConstants.CONSTANT_Module));
        }
    }
}
