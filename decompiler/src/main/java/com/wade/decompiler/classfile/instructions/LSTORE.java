package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LSTORE extends StoreInstruction {
    public LSTORE(ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, cp);
    }

    public LSTORE(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n, localVariableTable, cp);
    }
}
