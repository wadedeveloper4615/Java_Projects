package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BootstrapMethods extends Attribute {
    private List<BootstrapMethod> bootstrapMethods;

    public BootstrapMethods(int nameIndex, int length, List<BootstrapMethod> bootstrapMethods, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_BOOTSTRAP_METHODS, nameIndex, length, constantPool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (List<BootstrapMethod>) null, constantPool);
        int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new ArrayList<>();
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods.add(new BootstrapMethod(input, constantPool));
        }
    }
}
