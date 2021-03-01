package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.enums.TypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ReturnaddressType extends Type {
    public static ReturnaddressType NO_TARGET = new ReturnaddressType();

    private ReturnaddressType() {
        super(TypeEnum.T_ADDRESS, "<return address>");
    }
}
