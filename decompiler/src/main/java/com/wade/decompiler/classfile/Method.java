package com.wade.decompiler.classfile;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
public class Method extends FieldOrMethod {
    public Method() {
        super();
    }

    public Method(DataInput file, ConstantPool constantPool) throws IOException, ClassFormatException {
        super(file, constantPool);
    }

    public Method(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes) {
        super(accessFlags, nameIndex, signatureIndex, attributes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
