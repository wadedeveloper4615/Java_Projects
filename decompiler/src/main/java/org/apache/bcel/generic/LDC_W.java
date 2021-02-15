package org.apache.bcel.generic;

public class LDC_W extends LDC {
    public LDC_W() {
    }

    public LDC_W(final int index) {
        super(index);
    }
//
//    @Override
//    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
//        // setIndex(bytes.readUnsignedShort());
//        super.setOpcode(InstructionOpCodes.LDC_W);
//        super.setLength(3);
//    }
}
