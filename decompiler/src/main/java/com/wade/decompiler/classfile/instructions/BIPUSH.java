package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BIPUSH extends Instruction {
    private byte value;
    private Type type;

    public BIPUSH(ConstantPool cp) {
        super(InstructionOpCodes.BIPUSH, 2, cp);
        type = Type.BYTE;
    }

    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(2);
        this.value = bytes.readByte();
    }
}
