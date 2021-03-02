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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantClass extends Constant implements ConstantObject {
    private final int nameIndex;

    public ConstantClass(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    public ConstantClass(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        String name = ((ConstantUtf8) cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        return name;
    }
}
