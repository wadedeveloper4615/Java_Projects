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

    public String getName(ConstantPool cp) {
        return cp.constantToString(getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    public String getSignature(ConstantPool cp) {
        return cp.constantToString(getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
    }
}
