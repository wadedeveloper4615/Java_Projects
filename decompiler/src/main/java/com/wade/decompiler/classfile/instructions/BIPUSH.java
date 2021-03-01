package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class BIPUSH extends Instruction implements ConstantPushInstruction {
    private byte b;

    public BIPUSH(byte b, ConstantPool cp) {
        super(InstructionOpCodes.BIPUSH, 2, cp);
        this.b = b;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        stack.push(new Expression(ExpressionType.CONSTANT_NUMBER, b));
        return null;
    }

    @Override
    public Type getType() {
        return Type.BYTE;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(2);
        b = bytes.readByte();
    }
}
