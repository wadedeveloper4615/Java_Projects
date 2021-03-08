package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class MULTIANEWARRAY extends Instruction {
    private short dimensions;
    private int index;

    public MULTIANEWARRAY(ConstantPool constantPool) {
        super(InstructionOpCodes.MULTIANEWARRAY, 3, constantPool);
        if (dimensions < 1) {
            throw new ClassGenException("Invalid dimensions value: " + dimensions);
        }
        setLength(4);
    }

    @Override
    public int consumeStack() {
        return dimensions;
    }

    public short getDimensions() {
        return dimensions;
    }

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }

    public ObjectType getLoadClassType() {
        Type t = getType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    public Type getType() {
        String name = constantPool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        dimensions = bytes.readByte();
        setLength(4);
    }
}
