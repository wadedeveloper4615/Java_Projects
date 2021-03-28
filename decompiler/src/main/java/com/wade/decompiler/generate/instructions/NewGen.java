package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.ANEWARRAY;
import com.wade.decompiler.classfile.instructions.MULTIANEWARRAY;
import com.wade.decompiler.classfile.instructions.NEW;
import com.wade.decompiler.classfile.instructions.NEWARRAY;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
import com.wade.decompiler.enums.ClassFileConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class NewGen extends InstructionGen {
    private int index;
    @ToString.Exclude
    private ConstantPool constantPool;
    private Type type;
    private Short dimension;

    public NewGen(int offset, ANEWARRAY instr) {
        super(offset, instr.getLength());
        this.index = instr.getIndex();
        this.dimension = null;
        constantPool = instr.getConstantPool();
        type = this.getType();
    }

    public NewGen(int offset, MULTIANEWARRAY instr) {
        super(offset, instr.getLength());
        this.index = instr.getIndex();
        this.dimension = instr.getDimensions();
        constantPool = instr.getConstantPool();
        type = this.getType();
    }

    public NewGen(int offset, NEW instr) {
        super(offset, instr.getLength());
        this.index = instr.getIndex();
        this.dimension = null;
        constantPool = instr.getConstantPool();
        type = this.getType();
    }

    public NewGen(int offset, NEWARRAY instr) {
        super(offset, instr.getLength());
        type = this.getType();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        ObjectType objectType = (ObjectType) this.getType();
        String className = objectType.getClassName();
        String string = "new " + className + "()";
        Expression item = new Expression(ExpressionType.EXPRESSION, string);
        stack.push(item);
        return null;//"pushed " + string;
    }

    public ObjectType getLoadClassType() {
        Type t = getType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    public Type getType() {
        String name = constantPool.constantToString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }
}
