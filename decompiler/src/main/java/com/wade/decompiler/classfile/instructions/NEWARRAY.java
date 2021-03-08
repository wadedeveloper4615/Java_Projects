package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.BasicType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class NEWARRAY extends Instruction {
    private TypeEnum type;

    public NEWARRAY(BasicType type, ConstantPool cp) {
        this(type.getType(), cp);
    }

    public NEWARRAY(ConstantPool cp) {
        super(InstructionOpCodes.NEWARRAY, 2, cp);
    }

    public NEWARRAY(TypeEnum type, ConstantPool cp) {
        super(InstructionOpCodes.NEWARRAY, 2, cp);
        this.type = type;
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION };
    }

    public Type getType() {
        return new ArrayType(BasicType.getType(type), 1);
    }

    public TypeEnum getTypecode() {
        return type;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        type = TypeEnum.read(bytes.readByte());
        super.setLength(2);
    }
}
