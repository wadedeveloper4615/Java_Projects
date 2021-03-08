package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ALOAD;
import com.wade.decompiler.classfile.instructions.GETFIELD;
import com.wade.decompiler.classfile.instructions.IADD;
import com.wade.decompiler.classfile.instructions.ICONST;
import com.wade.decompiler.classfile.instructions.ILOAD;
import com.wade.decompiler.classfile.instructions.INVOKESPECIAL;
import com.wade.decompiler.classfile.instructions.IRETURN;
import com.wade.decompiler.classfile.instructions.ISTORE;
import com.wade.decompiler.classfile.instructions.LLOAD;
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
            case ILOAD_0 -> new LoadGen((ILOAD) instr);
            case ILOAD_1 -> new LoadGen((ILOAD) instr);
            case ILOAD_2 -> new LoadGen((ILOAD) instr);
            case ILOAD_3 -> new LoadGen((ILOAD) instr);
            case RETURN -> new ReturnGen((RETURN) instr);
            case IRETURN -> new ReturnGen((IRETURN) instr);
            case PUTFIELD -> new PutFieldGen((PUTFIELD) instr);
            case GETFIELD -> new GetFieldGen((GETFIELD) instr);
            case ICONST_M1 -> new IConstGen((ICONST) instr);
            case ICONST_0 -> new IConstGen((ICONST) instr);
            case ICONST_1 -> new IConstGen((ICONST) instr);
            case ICONST_2 -> new IConstGen((ICONST) instr);
            case ICONST_3 -> new IConstGen((ICONST) instr);
            case ICONST_4 -> new IConstGen((ICONST) instr);
            case ICONST_5 -> new IConstGen((ICONST) instr);
            case ISTORE_0 -> new IStoreGen((ISTORE) instr);
            case ISTORE_1 -> new IStoreGen((ISTORE) instr);
            case ISTORE_2 -> new IStoreGen((ISTORE) instr);
            case ISTORE_3 -> new IStoreGen((ISTORE) instr);
            case IADD -> new IAddGen((IADD) instr);
            case LLOAD_0 -> new LoadGen((LLOAD) instr);
            case LLOAD_1 -> new LoadGen((LLOAD) instr);
            case LLOAD_2 -> new LoadGen((LLOAD) instr);
            case LLOAD_3 -> new LoadGen((LLOAD) instr);
            default -> {
                System.out.println(instr);
                yield null;
            }
        };
        return instrgen;
    }
}
