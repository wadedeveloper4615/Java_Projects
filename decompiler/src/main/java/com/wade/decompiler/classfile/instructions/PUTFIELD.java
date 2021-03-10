package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class PUTFIELD extends Instruction {
    private int index;
    @ToString.Exclude
    private String superName;
    @ToString.Exclude
    protected String methodName;
    @ToString.Exclude
    protected String signature;
    @ToString.Exclude
    private Object constantValue;
    @ToString.Exclude
    private String constantString;

    public PUTFIELD(ConstantPool cp) {
        super(InstructionOpCodes.PUTFIELD, 3, cp);
    }

    @Override
    public int consumeStack() {
        return getFieldSize() + 1;
    }

    public int getFieldSize() {
        return Type.size(Type.getTypeSize(getSignature()));
    }

    public Type getType() {
        String name = constantPool.constantToString(index, ClassFileConstants.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setLength(3);
    }
}
