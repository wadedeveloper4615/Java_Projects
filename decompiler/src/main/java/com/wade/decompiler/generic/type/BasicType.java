package com.wade.decompiler.generic.type;

import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.generic.base.ClassGenException;

public class BasicType extends Type {
    public BasicType(TypeEnum type) {
        super(type, type.getShortTypeName());
        if ((type.getTag() < TypeEnum.T_BOOLEAN.getTag()) || (type.getTag() > TypeEnum.T_VOID.getTag())) {
            throw new ClassGenException("Invalid type: " + type);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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
            case T_INT:
                return INT;
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
            case T_INT:
                return INT;
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
