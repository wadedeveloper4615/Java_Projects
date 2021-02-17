package com.wade.decompiler.generic;

import com.wade.decompiler.generic.base.CompoundInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.InstructionList;

public final class SWITCH implements CompoundInstruction {
    private int[] match;
    private InstructionHandle[] targets;
    private Select instruction;
    private int matchLength;

    public SWITCH(final int[] match, final InstructionHandle[] targets, final InstructionHandle target, final int max_gap) {
        this.match = match.clone();
        this.targets = targets.clone();
        if ((matchLength = match.length) < 2) {
            instruction = new TABLESWITCH(match, targets, target);
        } else {
            sort(0, matchLength - 1);
            if (matchIsOrdered(max_gap)) {
                fillup(max_gap, target);
                instruction = new TABLESWITCH(this.match, this.targets, target);
            } else {
                instruction = new LOOKUPSWITCH(this.match, this.targets, target);
            }
        }
    }

    public SWITCH(final int[] match, final InstructionHandle[] targets, final InstructionHandle target) {
        this(match, targets, target, 1);
    }

    private void fillup(final int max_gap, final InstructionHandle target) {
        final int max_size = matchLength + matchLength * max_gap;
        final int[] m_vec = new int[max_size];
        final InstructionHandle[] t_vec = new InstructionHandle[max_size];
        int count = 1;
        m_vec[0] = match[0];
        t_vec[0] = targets[0];
        for (int i = 1; i < matchLength; i++) {
            final int prev = match[i - 1];
            final int gap = match[i] - prev;
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

    private void sort(final int l, final int r) {
        int i = l;
        int j = r;
        int h;
        final int m = match[(l + r) >>> 1];
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

    private boolean matchIsOrdered(final int max_gap) {
        for (int i = 1; i < matchLength; i++) {
            if (match[i] - match[i - 1] > max_gap) {
                return false;
            }
        }
        return true;
    }

    @Override
    public InstructionList getInstructionList() {
        return new InstructionList(instruction);
    }

    public Instruction getInstruction() {
        return instruction;
    }
}
