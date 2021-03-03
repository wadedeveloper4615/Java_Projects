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
public class ASTORE extends StoreInstruction {
    public ASTORE(ConstantPool cp) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, cp);
    }

    public ASTORE(int n, LocalVariableTableGen localVariableTable, ConstantPool cp) {
        super(InstructionOpCodes.ASTORE, InstructionOpCodes.ASTORE_0, n, localVariableTable, cp);
    }
}
