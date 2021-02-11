
package org.apache.bcel.generic.base;

import org.apache.bcel.Const;
import org.apache.bcel.generic.BasicType;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;

public final class ArrayType extends ReferenceType {

    private int dimensions;
    private Type basicType;

    public ArrayType(final byte type, final int dimensions) {
        this(BasicType.getType(type), dimensions);
    }

    public ArrayType(final String class_name, final int dimensions) {
        this(ObjectType.getInstance(class_name), dimensions);
    }

    public ArrayType(final Type type, final int dimensions) {
        super(Const.T_ARRAY, "<dummy>");
        if ((dimensions < 1) || (dimensions > Const.MAX_BYTE)) {
            throw new ClassGenException("Invalid number of dimensions: " + dimensions);
        }
        switch (type.getType()) {
            case Const.T_ARRAY:
                final ArrayType array = (ArrayType) type;
                this.dimensions = dimensions + array.dimensions;
                basicType = array.basicType;
                break;
            case Const.T_VOID:
                throw new ClassGenException("Invalid type: void[]");
            default: // Basic type or reference
                this.dimensions = dimensions;
                basicType = type;
                break;
        }
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < this.dimensions; i++) {
            buf.append('[');
        }
        buf.append(basicType.getSignature());
        super.setSignature(buf.toString());
    }

    public Type getBasicType() {
        return basicType;
    }

    public Type getElementType() {
        if (dimensions == 1) {
            return basicType;
        }
        return new ArrayType(basicType, dimensions - 1);
    }

    public int getDimensions() {
        return dimensions;
    }

    @Override
    public int hashCode() {
        return basicType.hashCode() ^ dimensions;
    }

    @Override
    public boolean equals(final Object _type) {
        if (_type instanceof ArrayType) {
            final ArrayType array = (ArrayType) _type;
            return (array.dimensions == dimensions) && array.basicType.equals(basicType);
        }
        return false;
    }
}
