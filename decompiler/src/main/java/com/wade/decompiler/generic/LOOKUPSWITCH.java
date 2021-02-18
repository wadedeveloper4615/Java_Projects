package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.util.ByteSequence;

public class LOOKUPSWITCH extends Select {
    public LOOKUPSWITCH() {
    }

    public LOOKUPSWITCH(int[] match, InstructionHandle[] targets, InstructionHandle defaultTarget) {
        super(InstructionOpCodes.LOOKUPSWITCH, match, targets, defaultTarget);
        short _length = (short) (9 + getMatch_length() * 8);
        super.setLength(_length);
        setFixed_length(_length);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        super.dump(out);
        int _match_length = getMatch_length();
        out.writeInt(_match_length); // npairs
        for (int i = 0; i < _match_length; i++) {
            out.writeInt(super.getMatch(i)); // match-offset pairs
            out.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.initFromFile(bytes, wide); // reads padding
        int _match_length = bytes.readInt();
        setMatch_length(_match_length);
        short _fixed_length = (short) (9 + _match_length * 8);
        setFixed_length(_fixed_length);
        short _length = (short) (_match_length + super.getPadding());
        super.setLength(_length);
        super.setMatches(new int[_match_length]);
        super.setIndices(new int[_match_length]);
        super.setTargets(new InstructionHandle[_match_length]);
        for (int i = 0; i < _match_length; i++) {
            super.setMatch(i, bytes.readInt());
            super.setIndices(i, bytes.readInt());
        }
    }
}
