package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.AllocationInstruction;
import com.wade.decompiler.classfile.instructions.base.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.BasicType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
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
public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {
    private TypeEnum type;

    public NEWARRAY(BasicType type, ConstantPool cp) {
        this(type.getType(), cp);
    }

    public NEWARRAY(int type, ConstantPool cp) {
        super(InstructionOpCodes.NEWARRAY, 2, cp);
        this.type = TypeEnum.read(type);
    }

    public NEWARRAY(TypeEnum type, ConstantPool cp) {
        super(InstructionOpCodes.NEWARRAY, 2, cp);
        this.type = type;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        Expression exp1 = stack.pop();
        stack.push(new Expression(ExpressionType.EXPRESSION, "new " + type.getTypeName() + "[" + exp1.toString() + "]"));
        return null;
    }

    @Override
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
