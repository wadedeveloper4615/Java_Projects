package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.TypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
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

}
