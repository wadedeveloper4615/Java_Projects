package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.AllocationInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.type.ArrayType;
import com.wade.decompiler.generic.type.BasicType;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {
    private byte type;

    public NEWARRAY() {
    }

    public NEWARRAY(BasicType type, ConstantPool cp) {
        this(type.getType(), cp);
    }

    public NEWARRAY(byte type, ConstantPool cp) {
        super(InstructionOpCodes.NEWARRAY, 2, cp);
        this.type = type;
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION };
    }

    public Type getType() {
        return new ArrayType(BasicType.getType(type), 1);
    }

    public byte getTypecode() {
        return type;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        type = bytes.readByte();
        super.setLength(2);
    }

    @Override
    public String toString() {
        return super.toString() + " " + Const.getTypeName(type);
    }
}
