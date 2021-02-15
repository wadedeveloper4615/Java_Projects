package org.apache.bcel.generic.base;

import org.apache.bcel.classfile.constant.Constant;
import org.apache.bcel.classfile.constant.ConstantClass;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.InstructionOpCodes;
//import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    protected int index;

    protected CPInstruction() {
    }

    protected CPInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, (short) 3);
        // setIndex(index);
    }
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        out.writeByte(super.getOpcode().getOpcode());
//        out.writeShort(index);
//    }
//    @Override
//    public final int getIndex() {
//        return index;
//    }
//    @Override
//    public Type getType(final ConstantPoolGen cpg) {
//        final ConstantPool cp = cpg.getConstantPool();
//        String name = cp.getConstantString(index, ClassFileConstants.CONSTANT_Class);
//        if (!name.startsWith("[")) {
//            name = "L" + name + ";";
//        }
//        return Type.getType(name);
//    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) {// throws IOException {
//        setIndex(bytes.readUnsignedShort());
//        super.setLength(3);
    }
//
//    @Override
//    public void setIndex(final int index) {
//        if (index < 0) {
//            throw new ClassGenException("Negative index value: " + index);
//        }
//        this.index = index;
//    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }

    @Override
    public String toString(final ConstantPool cp) {
        final Constant c = cp.getConstant(index);
        String str = cp.constantToString(c);
        if (c instanceof ConstantClass) {
            str = str.replace('.', '/');
        }
        return super.getOpcode().getName() + " " + str;
    }
}
