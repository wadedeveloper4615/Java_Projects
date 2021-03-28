package com.wade.decompiler.classfile.constant;

import com.wade.decompiler.enums.ClassFileConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
public class ConstantMethodref extends ConstantCP {
    public ConstantMethodref(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, input);
    }

//    public ConstantMethodref(int class_index, int name_and_type_index) {
//        super(ClassFileConstants.CONSTANT_Methodref, class_index, name_and_type_index);
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
