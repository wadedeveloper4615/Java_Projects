
package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.util.ByteSequence;

public class LOOKUPSWITCH extends Select {

    public LOOKUPSWITCH() {
    }

    public LOOKUPSWITCH(final int[] match, final InstructionHandle[] targets, final InstructionHandle defaultTarget) {
        super(org.apache.bcel.Const.LOOKUPSWITCH, match, targets, defaultTarget);

        final short _length = (short) (9 + getMatch_length() * 8);
        super.setLength(_length);
        setFixed_length(_length);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitSelect(this);
        v.visitLOOKUPSWITCH(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        final int _match_length = getMatch_length();
        out.writeInt(_match_length); // npairs
        for (int i = 0; i < _match_length; i++) {
            out.writeInt(super.getMatch(i)); // match-offset pairs
            out.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide); // reads padding
        final int _match_length = bytes.readInt();
        setMatch_length(_match_length);
        final short _fixed_length = (short) (9 + _match_length * 8);
        setFixed_length(_fixed_length);
        final short _length = (short) (_match_length + super.getPadding());
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
