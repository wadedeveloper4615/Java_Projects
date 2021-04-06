package com.wade.decompiler.generate.attribute;

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
    private String[] exports;
    private String package_name;
    private ClassAccessFlagsList exportFlags;

    public ModuleExportsGen(ModuleExports attribute, ConstantPool constantPool) {
        this.package_name = constantPool.constantToString(attribute.getExportsIndex(), ClassFileConstants.CONSTANT_Package);
        this.exportFlags = new ClassAccessFlagsList(attribute.getExportsFlags());
        int[] exports = attribute.getExportsToIndex();
        int requires_count = exports.length;
        this.exports = new String[requires_count];
        for (int i = 0; i < requires_count; i++) {
            this.exports[i] = constantPool.constantToString(exports[i], ClassFileConstants.CONSTANT_Module);
        }
    }
}
