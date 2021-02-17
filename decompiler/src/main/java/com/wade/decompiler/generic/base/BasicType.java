package com.wade.decompiler.generic.base;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.gen.ClassGenException;

public final class BasicType extends Type {
    public BasicType(final byte type) {
        super(type, Const.getShortTypeName(type));
        if ((type < Const.T_BOOLEAN) || (type > Const.T_VOID)) {
            throw new ClassGenException("Invalid type: " + type);
        }
    }

    @Override
    public boolean equals(final Object _type) {
        return (_type instanceof BasicType) ? ((BasicType) _type).getType() == this.getType() : false;
    }

    @Override
    public int hashCode() {
        return super.getType();
    }

    // @since 6.0 no longer final
    public static BasicType getType(final byte type) {
        switch (type) {
            case Const.T_VOID:
                return VOID;
            case Const.T_BOOLEAN:
                return BOOLEAN;
            case Const.T_BYTE:
                return BYTE;
            case Const.T_SHORT:
                return SHORT;
            case Const.T_CHAR:
                return CHAR;
            case Const.T_INT:
                return INT;
            case Const.T_LONG:
                return LONG;
            case Const.T_DOUBLE:
                return DOUBLE;
            case Const.T_FLOAT:
                return FLOAT;
            default:
                throw new ClassGenException("Invalid type: " + type);
        }
    }
}
