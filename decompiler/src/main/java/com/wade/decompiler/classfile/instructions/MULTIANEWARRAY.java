package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.Instruction;
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
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        dimensions = bytes.readByte();
        setLength(4);
    }
}
