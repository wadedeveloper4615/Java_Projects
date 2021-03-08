package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.ANEWARRAY;
import com.wade.decompiler.classfile.instructions.NEW;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class NewGen extends InstructionGen {
    private int index;
    @ToString.Exclude
    private ConstantPool constantPool;
    private Type type;

    public NewGen(ANEWARRAY instr) {
        this.index = instr.getIndex();
        constantPool = instr.getConstantPool();
        type = this.getType();
    }

    public NewGen(NEW instr) {
        this.index = instr.getIndex();
        constantPool = instr.getConstantPool();
        type = this.getType();
    }

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INSTANTIATION_ERROR);
    }

    public ObjectType getLoadClassType() {
        return (ObjectType) getType();
    }

    public Type getType() {
        String name = constantPool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }
}
