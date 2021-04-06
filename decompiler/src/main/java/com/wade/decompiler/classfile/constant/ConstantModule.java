package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantModule extends Constant {
    private final int nameIndex;

    public ConstantModule(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantModule(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Module);
        this.nameIndex = nameIndex;
    }

    public Object getConstantValue(ConstantPool cp) {
        Constant c = cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }
}
