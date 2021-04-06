package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.enums.TypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BasicType extends Type {
    public BasicType(TypeEnum type) {
        super(type, type.getShortTypeName());
        if ((type.getTag() < TypeEnum.T_BOOLEAN.getTag()) || (type.getTag() > TypeEnum.T_VOID.getTag())) {
            throw new ClassGenException("Invalid type: " + type);
        }
    }

    public static BasicType getType(int type) {
        switch (TypeEnum.read(type)) {
            case T_VOID:
                return VOID;
            case T_BOOLEAN:
                return BOOLEAN;
            case T_BYTE:
                return BYTE;
            case T_SHORT:
                return SHORT;
            case T_CHAR:
                return CHAR;
            case T_INTEGER:
                return INTEGER;
            case T_LONG:
                return LONG;
            case T_DOUBLE:
                return DOUBLE;
            case T_FLOAT:
                return FLOAT;
            default:
                throw new ClassGenException("Invalid type: " + type);
        }
    }

    public static BasicType getType(TypeEnum type) {
        switch (type) {
            case T_VOID:
                return VOID;
            case T_BOOLEAN:
                return BOOLEAN;
            case T_BYTE:
                return BYTE;
            case T_SHORT:
                return SHORT;
            case T_CHAR:
                return CHAR;
            case T_INTEGER:
                return INTEGER;
            case T_LONG:
                return LONG;
            case T_DOUBLE:
                return DOUBLE;
            case T_FLOAT:
                return FLOAT;
            default:
                throw new ClassGenException("Invalid type: " + type);
        }
    }
}
