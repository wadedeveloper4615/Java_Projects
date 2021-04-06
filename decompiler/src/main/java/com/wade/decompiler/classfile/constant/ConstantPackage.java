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
public class ConstantPackage extends Constant {
    private final int nameIndex;

    public ConstantPackage(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantPackage(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Package);
        this.nameIndex = nameIndex;
    }

    public Object getConstantValue(ConstantPool cp) {
        Constant c = cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }
}
