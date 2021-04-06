package com.wade.decompiler.classfile.constant;

import com.wade.decompiler.enums.ClassFileConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
public class ConstantString extends Constant {
    private final int stringIndex;

    public ConstantString(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantString(int stringIndex) {
        super(ClassFileConstants.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ConstantString other = (ConstantString) obj;
        return stringIndex == other.stringIndex;
    }

//    public Object getConstantValue(ConstantPool cp) {
//        Constant c = cp.getConstant(stringIndex, ClassFileConstants.CONSTANT_Utf8);
//        return ((ConstantUtf8) c).getBytes();
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(stringIndex);
        return result;
    }
}
