package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
    public StoreInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag, ConstantPool cp) {
        super(canon_tag, c_tag, cp);
    }

    public StoreInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n, LocalVariableTableGen localVariableTable, ConstantPool constantPool) {
        super(opcode, c_tag, n, localVariableTable, constantPool);
    }
}
