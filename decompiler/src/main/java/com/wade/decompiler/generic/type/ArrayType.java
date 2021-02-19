package com.wade.decompiler.generic.type;

import com.wade.decompiler.constants.Const;
import com.wade.decompiler.generic.base.ClassGenException;

public class ArrayType extends ReferenceType {
    private int dimensions;
    private Type basicType;

    public ArrayType(byte type, int dimensions) {
        this(BasicType.getType(type), dimensions);
    }

    public ArrayType(String class_name, int dimensions) {
        this(ObjectType.getInstance(class_name), dimensions);
    }

    public ArrayType(Type type, int dimensions) {
        super(Const.T_ARRAY, "<dummy>");
        if ((dimensions < 1) || (dimensions > Const.MAX_BYTE)) {
            throw new ClassGenException("Invalid number of dimensions: " + dimensions);
        }
        basicType = switch (type.getType()) {
            case Const.T_ARRAY -> {
                ArrayType array = (ArrayType) type;
                this.dimensions = dimensions + array.dimensions;
                yield array.basicType;
            }
            case Const.T_VOID -> throw new ClassGenException("Invalid type: void[]");
            default -> {
                this.dimensions = dimensions;
                yield type;
            }
        };
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < this.dimensions; i++) {
            buf.append('[');
        }
        buf.append(basicType.getSignature());
        super.setSignature(buf.toString());
    }

    @Override
    public boolean equals(Object _type) {
        if (_type instanceof ArrayType) {
            ArrayType array = (ArrayType) _type;
            return (array.dimensions == dimensions) && array.basicType.equals(basicType);
        }
        return false;
    }

    public Type getBasicType() {
        return basicType;
    }

    public int getDimensions() {
        return dimensions;
    }

    public Type getElementType() {
        if (dimensions == 1) {
            return basicType;
        }
        return new ArrayType(basicType, dimensions - 1);
    }

    @Override
    public int hashCode() {
        return basicType.hashCode() ^ dimensions;
    }
}
