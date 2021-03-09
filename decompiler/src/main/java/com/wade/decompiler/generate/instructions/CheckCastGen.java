package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.CHECKCAST;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class CheckCastGen extends InstructionGen {
    private int index;
    private Type type;
    private Class<?>[] exceptions;
    @ToString.Exclude
    private ConstantPool constantPool;

    public CheckCastGen(CHECKCAST instr) {
        this.index = instr.getIndex();
        this.constantPool = instr.getConstantPool();
        this.type = getLoadClassType();
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.CLASS_CAST_EXCEPTION);
    }

    public Type getBaseType() {
        String name = constantPool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    public ObjectType getLoadClassType() {
        Type t = getBaseType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }
}
