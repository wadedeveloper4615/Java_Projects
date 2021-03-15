package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ACONST_NULL;
import com.wade.decompiler.classfile.instructions.BIPUSH;
import com.wade.decompiler.classfile.instructions.DCONST;
import com.wade.decompiler.classfile.instructions.FCONST;
import com.wade.decompiler.classfile.instructions.ICONST;
import com.wade.decompiler.classfile.instructions.LCONST;
import com.wade.decompiler.classfile.instructions.LDC;
import com.wade.decompiler.classfile.instructions.LDC2_W;
import com.wade.decompiler.classfile.instructions.SIPUSH;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConstGen extends InstructionGen {
    private Object value;
    private Type type;
    private Class<?>[] exceptions;

    public ConstGen(int offset, ACONST_NULL instr) {
        super(offset, instr.getLength());
        value = Type.NULL;
        type = Type.NULL;
    }

    public ConstGen(int offset, BIPUSH instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(int offset, DCONST instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = Type.DOUBLE;
    }

    public ConstGen(int offset, FCONST instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = Type.FLOAT;
    }

    public ConstGen(int offset, ICONST instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = Type.INTEGER;
    }

    public ConstGen(int offset, LCONST instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = Type.INTEGER;
    }

    public ConstGen(int offset, LDC instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = instr.getType();
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_STRING_RESOLUTION);
    }

    public ConstGen(int offset, LDC2_W instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(int offset, SIPUSH instr) {
        super(offset, instr.getLength());
        value = instr.getValue();
        type = instr.getType();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        String name = this.value.toString();
        stack.push(new Expression(ExpressionType.CONSTANT_NUMBER, name));
        return null;// "push " + name;
    }
}
