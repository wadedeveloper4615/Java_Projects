package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArrayInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.StackProducer;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class AALOAD extends ArrayInstruction implements StackProducer {
    public AALOAD(ConstantPool cp) {
        super(InstructionOpCodes.AALOAD, cp);
    }
}
