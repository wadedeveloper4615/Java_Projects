package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.INSTANCEOF;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class InstanceOfGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private int index;
    private Type type;
    private String name;
    @ToString.Exclude
    private ConstantPool constantPool;

    public InstanceOfGen(int offset, INSTANCEOF instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        index = instr.getIndex();
        constantPool = instr.getConstantPool();
        type = getLoadClassType();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }

    public ObjectType getLoadClassType() {
        Type t = getType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    public Type getType() {
        name = constantPool.constantToString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }
}
