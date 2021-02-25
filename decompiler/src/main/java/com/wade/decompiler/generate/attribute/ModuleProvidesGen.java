package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.ModuleProvides;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

public class ModuleProvidesGen {
    private String[] providesIndex;
    private String interface_name;

    public ModuleProvidesGen(ModuleProvides attribute, ConstantPool constantPool) {
        this.interface_name = constantPool.constantToString(attribute.getProvidesIndex(), ClassFileConstants.CONSTANT_Class);
        int[] providesIndex = attribute.getProvidesWithIndex();
        int providesWithCount = providesIndex.length;
        this.providesIndex = new String[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            this.providesIndex[i] = constantPool.getConstantString(providesIndex[i], ClassFileConstants.CONSTANT_Module);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleProvidesGen other = (ModuleProvidesGen) obj;
        if (interface_name == null) {
            if (other.interface_name != null)
                return false;
        } else if (!interface_name.equals(other.interface_name))
            return false;
        if (!Arrays.equals(providesIndex, other.providesIndex))
            return false;
        return true;
    }

    public String getInterface_name() {
        return interface_name;
    }

    public String[] getProvidesIndex() {
        return providesIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((interface_name == null) ? 0 : interface_name.hashCode());
        result = prime * result + Arrays.hashCode(providesIndex);
        return result;
    }

    @Override
    public String toString() {
        return "ModuleProvidesGen [providesIndex=" + Arrays.toString(providesIndex) + ", interface_name=" + interface_name + "]";
    }
}
