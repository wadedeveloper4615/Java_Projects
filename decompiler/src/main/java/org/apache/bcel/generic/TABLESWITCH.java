package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.util.ByteSequence;

public class TABLESWITCH extends Select {
    public TABLESWITCH() {
    }

    public TABLESWITCH(final int[] match, final InstructionHandle[] targets, final InstructionHandle defaultTarget) {
        super(org.apache.bcel.Const.TABLESWITCH, match, targets, defaultTarget);
        final short _length = (short) (13 + getMatch_length() * 4);
        super.setLength(_length);
        setFixed_length(_length);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitSelect(this);
        v.visitTABLESWITCH(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        final int _match_length = getMatch_length();
        final int low = (_match_length > 0) ? super.getMatch(0) : 0;
        out.writeInt(low);
        final int high = (_match_length > 0) ? super.getMatch(_match_length - 1) : 0;
        out.writeInt(high);
        for (int i = 0; i < _match_length; i++) {
            out.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        final int low = bytes.readInt();
        final int high = bytes.readInt();
        final int _match_length = high - low + 1;
        setMatch_length(_match_length);
        final short _fixed_length = (short) (13 + _match_length * 4);
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
