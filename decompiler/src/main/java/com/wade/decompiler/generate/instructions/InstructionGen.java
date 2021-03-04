package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ALOAD;
import com.wade.decompiler.classfile.instructions.GETFIELD;
import com.wade.decompiler.classfile.instructions.ILOAD;
import com.wade.decompiler.classfile.instructions.INVOKESPECIAL;
import com.wade.decompiler.classfile.instructions.IRETURN;
import com.wade.decompiler.classfile.instructions.PUTFIELD;
import com.wade.decompiler.classfile.instructions.RETURN;
import com.wade.decompiler.classfile.instructions.base.Instruction;

public class InstructionGen {
    public static InstructionGen read(Instruction instr) {
        InstructionGen instrgen = switch (instr.getOpcode()) {
            case ALOAD_0 -> new AloadGen((ALOAD) instr);
            case ALOAD_1 -> new AloadGen((ALOAD) instr);
            case ALOAD_2 -> new AloadGen((ALOAD) instr);
            case ALOAD_3 -> new AloadGen((ALOAD) instr);
            case INVOKESPECIAL -> new InvokeSpecialGen((INVOKESPECIAL) instr);
            case ILOAD_0 -> new ILoadGen((ILOAD) instr);
            case ILOAD_1 -> new ILoadGen((ILOAD) instr);
            case ILOAD_2 -> new ILoadGen((ILOAD) instr);
            case ILOAD_3 -> new ILoadGen((ILOAD) instr);
            case RETURN -> new ReturnGen((RETURN) instr);
            case IRETURN -> new ReturnGen((IRETURN) instr);
            case PUTFIELD -> new PutFielldGen((PUTFIELD) instr);
            case GETFIELD -> new GetFielldGen((GETFIELD) instr);
            default -> {
                System.out.println(instr);
                yield null;
            }
        };
        return instrgen;
    }
}
