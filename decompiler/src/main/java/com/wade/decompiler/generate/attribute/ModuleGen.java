package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Module;
import com.wade.decompiler.classfile.attribute.ModuleExports;
import com.wade.decompiler.classfile.attribute.ModuleOpens;
import com.wade.decompiler.classfile.attribute.ModuleProvides;
import com.wade.decompiler.classfile.attribute.ModuleRequires;
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
public class ModuleGen extends AttributeGen {
    private String moduleName;
    private String moduleVersion;
    private ClassAccessFlagsList moduleFlags;
    private ModuleRequiresGen[] requiresTable;
    private ModuleExportsGen[] exportsTable;
    private ModuleOpensGen[] opensTable;
    private String[] usesIndex;
    private ModuleProvidesGen[] providesTable;

    public ModuleGen(Module attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.moduleName = constantPool.constantToString(attribute.getModuleNameIndex(), ClassFileConstants.CONSTANT_Module);
        this.moduleVersion = constantPool.constantToString(attribute.getModuleVersionIndex(), ClassFileConstants.CONSTANT_Utf8);
        this.moduleFlags = new ClassAccessFlagsList(attribute.getModuleFlags());

        ModuleRequires[] requiresTable = attribute.getRequiresTable();
        int requires_count = requiresTable.length;
        this.requiresTable = new ModuleRequiresGen[requires_count];
        for (int i = 0; i < requires_count; i++) {
            this.requiresTable[i] = new ModuleRequiresGen(requiresTable[i], constantPool);
        }

        ModuleExports[] exportsTable = attribute.getExportsTable();
        int exports_count = exportsTable.length;
        this.exportsTable = new ModuleExportsGen[exports_count];
        for (int i = 0; i < exports_count; i++) {
            this.exportsTable[i] = new ModuleExportsGen(exportsTable[i], constantPool);
        }

        ModuleOpens[] opensTable = attribute.getOpensTable();
        int opens_count = opensTable.length;
        this.opensTable = new ModuleOpensGen[opens_count];
        for (int i = 0; i < opens_count; i++) {
            this.opensTable[i] = new ModuleOpensGen(opensTable[i], constantPool);
        }

        int[] usesIndex = attribute.getUsesIndex();
        int usesCount = usesIndex.length;
        this.usesIndex = new String[usesCount];
        for (int i = 0; i < usesCount; i++) {
            this.usesIndex[i] = constantPool.constantToString(usesIndex[i], ClassFileConstants.CONSTANT_Module);
        }

        ModuleProvides[] providesTable = attribute.getProvidesTable();
        int provides_count = opensTable.length;
        this.providesTable = new ModuleProvidesGen[provides_count];
        for (int i = 0; i < provides_count; i++) {
            this.providesTable[i] = new ModuleProvidesGen(providesTable[i], constantPool);
        }
    }
}
