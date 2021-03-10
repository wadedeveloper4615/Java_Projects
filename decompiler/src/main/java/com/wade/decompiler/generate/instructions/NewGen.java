package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.ANEWARRAY;
import com.wade.decompiler.classfile.instructions.MULTIANEWARRAY;
import com.wade.decompiler.classfile.instructions.NEW;
import com.wade.decompiler.classfile.instructions.NEWARRAY;
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
@SuppressWarnings("unused")
public class NewGen extends InstructionGen {
    private int index;
    @ToString.Exclude
    private ConstantPool constantPool;
    private Type type;
    private Short dimension;

    private Class<?>[] exceptions;

    public NewGen(int offset, ANEWARRAY instr) {
        super(offset, instr.getLength());
        this.index = instr.getIndex();
        this.dimension = null;
        constantPool = instr.getConstantPool();
        type = this.getType();
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INSTANTIATION_ERROR);
    }

    public NewGen(int offset, MULTIANEWARRAY instr) {
        super(offset, instr.getLength());
        this.index = instr.getIndex();
        this.dimension = instr.getDimensions();
        constantPool = instr.getConstantPool();
        type = this.getType();
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INSTANTIATION_ERROR);
    }

    public NewGen(int offset, NEW instr) {
        super(offset, instr.getLength());
        this.index = instr.getIndex();
        this.dimension = null;
        constantPool = instr.getConstantPool();
        type = this.getType();
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INSTANTIATION_ERROR);
    }

    public NewGen(int offset, NEWARRAY instr) {
        super(offset, instr.getLength());
        type = this.getType();
        exceptions = new Class[] { ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION };
    }

    public ObjectType getLoadClassType() {
        Type t = getType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    public Type getType() {
        String name = constantPool.getConstantString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }
}
