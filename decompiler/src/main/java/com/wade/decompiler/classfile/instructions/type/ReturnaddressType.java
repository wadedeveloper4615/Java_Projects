package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.enums.TypeEnum;

public class ReturnaddressType extends Type {
    public static ReturnaddressType NO_TARGET = new ReturnaddressType();

    private ReturnaddressType() {
        super(TypeEnum.T_ADDRESS, "<return address>");
    }
}
