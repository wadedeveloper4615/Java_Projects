package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class TABLESWITCH extends Select {
    public TABLESWITCH() {
    }

    public TABLESWITCH(int[] match, InstructionHandle[] targets, InstructionHandle defaultTarget) {
        super(InstructionOpCodes.TABLESWITCH, match, targets, defaultTarget);
        short _length = (short) (13 + getMatch_length() * 4);
        super.setLength(_length);
        setFixed_length(_length);
    }

    @Override
    public void accept(Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitSelect(this);
        v.visitTABLESWITCH(this);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        super.dump(out);
        int _match_length = getMatch_length();
        int low = (_match_length > 0) ? super.getMatch(0) : 0;
        out.writeInt(low);
        int high = (_match_length > 0) ? super.getMatch(_match_length - 1) : 0;
        out.writeInt(high);
        for (int i = 0; i < _match_length; i++) {
            out.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        int low = bytes.readInt();
        int high = bytes.readInt();
        int _match_length = high - low + 1;
        setMatch_length(_match_length);
        short _fixed_length = (short) (13 + _match_length * 4);
        setFixed_length(_fixed_length);
        super.setLength((short) (_fixed_length + super.getPadding()));
        super.setMatches(new int[_match_length]);
        super.setIndices(new int[_match_length]);
        super.setTargets(new InstructionHandle[_match_length]);
        for (int i = 0; i < _match_length; i++) {
            super.setMatch(i, low + i);
            super.setIndices(i, bytes.readInt());
        }
    }
}
