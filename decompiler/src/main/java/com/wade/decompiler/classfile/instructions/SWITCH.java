package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.CompoundInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.classfile.instructions.base.InstructionList;

public class SWITCH implements CompoundInstruction {
    private int[] match;
    private InstructionHandle[] targets;
    private Select instruction;
    private int matchLength;

    public SWITCH(int[] match, InstructionHandle[] targets, InstructionHandle target, ConstantPool cp) {
        this(match, targets, target, 1, cp);
    }

    public SWITCH(int[] match, InstructionHandle[] targets, InstructionHandle target, int max_gap, ConstantPool cp) {
        this.match = match.clone();
        this.targets = targets.clone();
        if ((matchLength = match.length) < 2) {
            instruction = new TABLESWITCH(match, targets, target, cp);
        } else {
            sort(0, matchLength - 1);
            if (matchIsOrdered(max_gap)) {
                fillup(max_gap, target);
                instruction = new TABLESWITCH(this.match, this.targets, target, cp);
            } else {
                instruction = new LOOKUPSWITCH(this.match, this.targets, target, cp);
            }
        }
    }

    private void fillup(int max_gap, InstructionHandle target) {
        int max_size = matchLength + matchLength * max_gap;
        int[] m_vec = new int[max_size];
        InstructionHandle[] t_vec = new InstructionHandle[max_size];
        int count = 1;
        m_vec[0] = match[0];
        t_vec[0] = targets[0];
        for (int i = 1; i < matchLength; i++) {
            int prev = match[i - 1];
            int gap = match[i] - prev;
            for (int j = 1; j < gap; j++) {
                m_vec[count] = prev + j;
                t_vec[count] = target;
                count++;
            }
            m_vec[count] = match[i];
            t_vec[count] = targets[i];
            count++;
        }
        match = new int[count];
        targets = new InstructionHandle[count];
        System.arraycopy(m_vec, 0, match, 0, count);
        System.arraycopy(t_vec, 0, targets, 0, count);
    }

    public Instruction getInstruction() {
        return instruction;
    }

    @Override
    public InstructionList getInstructionList() {
        return new InstructionList(instruction);
    }

    private boolean matchIsOrdered(int max_gap) {
        for (int i = 1; i < matchLength; i++) {
            if (match[i] - match[i - 1] > max_gap) {
                return false;
            }
        }
        return true;
    }

    private void sort(int l, int r) {
        int i = l;
        int j = r;
        int h;
        int m = match[(l + r) >>> 1];
        InstructionHandle h2;
        do {
            while (match[i] < m) {
                i++;
            }
            while (m < match[j]) {
                j--;
            }
            if (i <= j) {
                h = match[i];
                match[i] = match[j];
                match[j] = h; // Swap elements
                h2 = targets[i];
                targets[i] = targets[j];
                targets[j] = h2; // Swap instructions, too
                i++;
                j--;
            }
        } while (i <= j);
        if (l < j) {
            sort(l, j);
        }
        if (i < r) {
            sort(i, r);
        }
    }
}
