package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ClassGenException;
import com.wade.decompiler.generic.base.InvokeInstruction;
import com.wade.decompiler.util.ByteSequence;

public class INVOKEINTERFACE extends InvokeInstruction {
    private int nargs; // Number of arguments on stack (number of stack slots), called "count" in
    // vmspec2

    public INVOKEINTERFACE() {
    }

    public INVOKEINTERFACE(int index, int nargs, ConstantPool cp) {
        super(InstructionOpCodes.INVOKEINTERFACE, index, cp);
        super.setLength(5);
        if (nargs < 1) {
            throw new ClassGenException("Number of arguments must be > 0 " + nargs);
        }
        this.nargs = nargs;
    }

    @Override
    public int consumeStack(ConstantPool cpg) { // nargs is given in byte-code
        return nargs; // nargs includes this reference
    }

    public int getCount() {
        return nargs;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        super.setLength(5);
        nargs = bytes.readUnsignedByte();
        bytes.readByte(); // Skip 0 byte
    }

    @Override
    public String toString() {
        return super.toString() + " " + nargs;
    }
}
