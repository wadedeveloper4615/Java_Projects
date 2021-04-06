package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.BootstrapMethod;
import com.wade.decompiler.classfile.attribute.BootstrapMethods;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BootstrapMethodsGen extends AttributeGen {
    private List<BootstrapMethodGen> bootstrapMethods;

    public BootstrapMethodsGen(BootstrapMethods attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        List<BootstrapMethod> bootstrapMethodsAttr = attribute.getBootstrapMethods();
        int num_bootstrap_methods = bootstrapMethodsAttr.size();
        for (BootstrapMethod entry:bootstrapMethodsAttr) {
            this.bootstrapMethods.add(new BootstrapMethodGen(entry, constantPool));
        }
    }
}
