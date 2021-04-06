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
public class ConstantNameAndType extends Constant {
    private final int nameIndex;
    private final int signatureIndex;

    public ConstantNameAndType(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType(int nameIndex, int signatureIndex) {
        super(ClassFileConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ConstantNameAndType other = (ConstantNameAndType) obj;
        return nameIndex == other.nameIndex && signatureIndex == other.signatureIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(nameIndex, signatureIndex);
        return result;
    }
}
