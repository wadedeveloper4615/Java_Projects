package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

import com.wade.decompiler.enums.ClassFileConstants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
public abstract class ConstantCP extends Constant {
    protected final int classIndex;
    protected final int nameAndTypeIndex;

    public ConstantCP(ClassFileConstants tag, DataInput file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantCP(ClassFileConstants tag, int classIndex, int nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ConstantCP other = (ConstantCP) obj;
        return classIndex == other.classIndex && nameAndTypeIndex == other.nameAndTypeIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(classIndex, nameAndTypeIndex);
        return result;
    }
}
