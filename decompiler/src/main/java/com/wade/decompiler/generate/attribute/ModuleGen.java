package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Module;
import com.wade.decompiler.classfile.attribute.*;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ModuleGen extends AttributeGen {
    private String moduleName;
    private String moduleVersion;
    private ClassAccessFlagsList moduleFlags;
    private List<ModuleRequiresGen> requiresTable;
    private List<ModuleExportsGen> exportsTable;
    private List<ModuleOpensGen> opensTable;
    private List<String> usesIndex;
    private List<ModuleProvidesGen> providesTable;

    public ModuleGen(Module attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.moduleName = constantPool.constantToString(attribute.getModuleNameIndex(), ClassFileConstants.CONSTANT_Module);
        this.moduleVersion = constantPool.constantToString(attribute.getModuleVersionIndex(), ClassFileConstants.CONSTANT_Utf8);
        this.moduleFlags = new ClassAccessFlagsList(attribute.getModuleFlags());

        this.requiresTable = new ArrayList<>();
        for (ModuleRequires entry : attribute.getRequiresTable()) {
            this.requiresTable.add(new ModuleRequiresGen(entry, constantPool));
        }

        this.exportsTable = new ArrayList<>();
        for (ModuleExports entry : attribute.getExportsTable()) {
            this.exportsTable.add(new ModuleExportsGen(entry, constantPool));
        }

        this.opensTable = new ArrayList<>();
        for (ModuleOpens entry : attribute.getOpensTable()) {
            this.opensTable.add(new ModuleOpensGen(entry, constantPool));
        }

        this.usesIndex = new ArrayList<>();
        for (Integer  entry : attribute.getUsesIndex()) {
            this.usesIndex.add(constantPool.constantToString(entry, ClassFileConstants.CONSTANT_Module));
        }

        this.providesTable = new ArrayList<>();
        for (ModuleProvides entry : attribute.getProvidesTable()) {
            this.providesTable.add(new ModuleProvidesGen(entry, constantPool));
        }
    }
}
