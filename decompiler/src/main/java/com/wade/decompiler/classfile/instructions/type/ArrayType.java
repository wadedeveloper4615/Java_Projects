package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.TypeEnum;

public class ArrayType extends ReferenceType {
    private int dimensions;
    private Type basicType;

    public ArrayType(byte type, int dimensions) {
        this(BasicType.getType(type), dimensions);
    }

    public ArrayType(String class_name, int dimensions) {
        this(new ObjectType(class_name), dimensions);
    }

    public ArrayType(Type type, int dimensions) {
        super(TypeEnum.T_ARRAY, "<dummy>");
        if ((dimensions < 1) || (dimensions > Const.MAX_BYTE)) {
            throw new ClassGenException("Invalid number of dimensions: " + dimensions);
        }
        basicType = switch (type.getType()) {
            case T_ARRAY -> {
                ArrayType array = (ArrayType) type;
                this.dimensions = dimensions + array.dimensions;
                yield array.basicType;
            }
            case T_VOID -> throw new ClassGenException("Invalid type: void[]");
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArrayType other = (ArrayType) obj;
        if (basicType == null) {
            if (other.basicType != null)
                return false;
        } else if (!basicType.equals(other.basicType))
            return false;
        if (dimensions != other.dimensions)
            return false;
        return true;
    }

    public Type getBasicType() {
        return this.basicType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((basicType == null) ? 0 : basicType.hashCode());
        result = prime * result + dimensions;
        return result;
    }
}
