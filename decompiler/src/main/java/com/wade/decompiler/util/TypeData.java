package com.wade.decompiler.util;

import com.wade.decompiler.enums.TypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class TypeData {
    TypeEnum baseType;
    TypeEnum indexType;
    private String reference;

    public TypeData(TypeEnum baseType, TypeEnum indexType) {
        this.baseType = baseType;
        this.indexType = indexType;
    }

    public TypeData(TypeEnum baseType, TypeEnum indexType, String reference) {
        this.baseType = baseType;
        this.indexType = indexType;
        this.reference = reference;
    }
}
