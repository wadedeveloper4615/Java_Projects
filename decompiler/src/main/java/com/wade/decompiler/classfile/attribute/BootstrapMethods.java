package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BootstrapMethods extends Attribute {
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethods(int nameIndex, int length, BootstrapMethod[] bootstrapMethods, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_BOOTSTRAP_METHODS, nameIndex, length, constantPool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (BootstrapMethod[]) null, constantPool);
        int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input, constantPool);
        }
    }
}
