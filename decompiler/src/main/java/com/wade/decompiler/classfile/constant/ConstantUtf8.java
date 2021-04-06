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
public class ConstantUtf8 extends Constant {
    private final String bytes;

    public ConstantUtf8(DataInput dataInput) throws IOException {
        super(ClassFileConstants.CONSTANT_Utf8);
        this.bytes = dataInput.readUTF();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ConstantUtf8 other = (ConstantUtf8) obj;
        return Objects.equals(bytes, other.bytes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(bytes);
        return result;
    }
}
