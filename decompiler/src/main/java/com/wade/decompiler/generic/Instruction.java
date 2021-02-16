/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.util.ByteSequence;

/**
 * Abstract super class for all Java byte codes.
 */
public abstract class Instruction implements Cloneable {
    private static InstructionComparator cmp = InstructionComparator.DEFAULT;
    /**
     * @deprecated (since 6.0) will be made private; do not access directly, use
     *             getter/setter
     */
    @Deprecated
    protected short length = 1; // Length of instruction in bytes
    /**
     * @deprecated (since 6.0) will be made private; do not access directly, use
     *             getter/setter
     */
    @Deprecated
    protected short opcode = -1; // Opcode number

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used
     * otherwise.
     */
    Instruction() {
    }

    public Instruction(final short opcode, final short length) {
        this.length = length;
        this.opcode = opcode;
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of
     * implemented interfaces first, then call methods according to the class
     * hierarchy in descending order, i.e., the most specific visitXXX() call comes
     * last.
     *
     * @param v Visitor object
     */
    public abstract void accept(Visitor v);

    /**
     * This method also gives right results for instructions whose effect on the
     * stack depends on the constant pool entry they reference.
     *
     * @return Number of words consumed from stack by this instruction, or
     *         Constants.UNPREDICTABLE, if this can not be computed statically
     */
    public int consumeStack(final ConstantPoolGen cpg) {
        return Const.getConsumeStack(opcode);
    }

    /**
     * Use with caution, since `BranchInstruction's have a `target' reference which
     * is not copied correctly (only basic types are). This also applies for
     * `Select' instructions with their multiple branch targets.
     *
     * @return (shallow) copy of an instruction
     * @see BranchInstruction
     */
    public Instruction copy() {
        Instruction i = null;
        // "Constant" instruction, no need to duplicate
        if (InstructionConst.getInstruction(this.getOpcode()) != null) {
            i = this;
        } else {
            try {
                i = (Instruction) clone();
            } catch (final CloneNotSupportedException e) {
                System.err.println(e);
            }
        }
        return i;
    }

    /**
     * Some instructions may be reused, so don't do anything by default.
     */
    void dispose() {
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(opcode); // Common for all instructions
    }

    /**
     * Check for equality, delegated to comparator
     *
     * @return true if that is an Instruction and has the same opcode
     */
    @Override
    public boolean equals(final Object that) {
        return (that instanceof Instruction) ? cmp.equals(this, (Instruction) that) : false;
    }

    /**
     * @return length (in bytes) of instruction
     */
    public int getLength() {
        return length;
    }

    /**
     * @return name of instruction, i.e., opcode name
     */
    public String getName() {
        return Const.getOpcodeName(opcode);
    }

    /**
     * @return this instructions opcode
     */
    public short getOpcode() {
        return opcode;
    }

    /**
     * calculate the hashCode of this object
     *
     * @return the hashCode
     * @since 6.0
     */
    @Override
    public int hashCode() {
        return opcode;
    }

    /**
     * Read needed data (e.g. index) from file.
     *
     * @param bytes byte sequence to read from
     * @param wide  "wide" instruction flag
     * @throws IOException may be thrown if the implementation needs to read data
     *                     from the file
     */
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
    }

    /**
     * This method also gives right results for instructions whose effect on the
     * stack depends on the constant pool entry they reference.
     *
     * @return Number of words produced onto stack by this instruction, or
     *         Constants.UNPREDICTABLE, if this can not be computed statically
     */
    public int produceStack(final ConstantPoolGen cpg) {
        return Const.getProduceStack(opcode);
    }

    /**
     * Needed in readInstruction and subclasses in this package
     *
     * @since 6.0
     */
    final void setLength(final int length) {
        this.length = (short) length; // TODO check range?
    }

    /**
     * Needed in readInstruction and subclasses in this package
     */
    final void setOpcode(final short opcode) {
        this.opcode = opcode;
    }

    /**
     * @return mnemonic for instruction in verbose format
     */
    @Override
    public String toString() {
        return toString(true);
    }

    /**
     * Long output format:
     * <p>
     * &lt;name of opcode&gt; "["&lt;opcode number&gt;"]" "("&lt;length of
     * instruction&gt;")"
     *
     * @param verbose long/short format switch
     * @return mnemonic for instruction
     */
    public String toString(final boolean verbose) {
        if (verbose) {
            return getName() + "[" + opcode + "](" + length + ")";
        }
        return getName();
    }

    /**
     * @return mnemonic for instruction with sumbolic references resolved
     */
    public String toString(final ConstantPool cp) {
        return toString(false);
    }

    /**
     * Get Comparator object used in the equals() method to determine equality of
     * instructions.
     *
     * @return currently used comparator for equals()
     * @deprecated (6.0) use the built in comparator, or wrap this class in another
     *             object that implements these methods
     */
    @Deprecated
    public static InstructionComparator getComparator() {
        return cmp;
    }

    /**
     * Check if the value can fit in a byte (signed)
     *
     * @param value the value to check
     * @return true if the value is in range
     * @since 6.0
     */
    public static boolean isValidByte(final int value) {
        return value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE;
    }

    /**
     * Check if the value can fit in a short (signed)
     *
     * @param value the value to check
     * @return true if the value is in range
     * @since 6.0
     */
    public static boolean isValidShort(final int value) {
        return value >= Short.MIN_VALUE && value <= Short.MAX_VALUE;
    }

    /**
     * Read an instruction from (byte code) input stream and return the appropiate
     * object.
     * <p>
     * If the Instruction is defined in {@link InstructionConst}, then the singleton
     * instance is returned.
     *
     * @param bytes input stream bytes
     * @return instruction object being read
     * @see InstructionConst#getInstruction(int)
     */
    // @since 6.0 no longer final
    public static Instruction readInstruction(final ByteSequence bytes) throws IOException {
        boolean wide = false;
        short opcode = (short) bytes.readUnsignedByte();
        Instruction obj = null;
        if (opcode == Const.WIDE) { // Read next opcode after wide byte
            wide = true;
            opcode = (short) bytes.readUnsignedByte();
        }
        final Instruction instruction = InstructionConst.getInstruction(opcode);
        if (instruction != null) {
            return instruction; // Used predefined immutable object, if available
        }
        obj = switch (opcode) {
            case Const.BIPUSH -> new BIPUSH();
            case Const.SIPUSH -> new SIPUSH();
            case Const.LDC -> new LDC();
            case Const.LDC_W -> new LDC_W();
            case Const.LDC2_W -> new LDC2_W();
            case Const.ILOAD -> new ILOAD();
            case Const.LLOAD -> new LLOAD();
            case Const.FLOAD -> new FLOAD();
            case Const.DLOAD -> new DLOAD();
            case Const.ALOAD -> new ALOAD();
            case Const.ILOAD_0 -> new ILOAD(0);
            case Const.ILOAD_1 -> new ILOAD(1);
            case Const.ILOAD_2 -> new ILOAD(2);
            case Const.ILOAD_3 -> new ILOAD(3);
            case Const.LLOAD_0 -> new LLOAD(0);
            case Const.LLOAD_1 -> new LLOAD(1);
            case Const.LLOAD_2 -> new LLOAD(2);
            case Const.LLOAD_3 -> new LLOAD(3);
            case Const.FLOAD_0 -> new FLOAD(0);
            case Const.FLOAD_1 -> new FLOAD(1);
            case Const.FLOAD_2 -> new FLOAD(2);
            case Const.FLOAD_3 -> new FLOAD(3);
            case Const.DLOAD_0 -> new DLOAD(0);
            case Const.DLOAD_1 -> new DLOAD(1);
            case Const.DLOAD_2 -> new DLOAD(2);
            case Const.DLOAD_3 -> new DLOAD(3);
            case Const.ALOAD_0 -> new ALOAD(0);
            case Const.ALOAD_1 -> new ALOAD(1);
            case Const.ALOAD_2 -> new ALOAD(2);
            case Const.ALOAD_3 -> new ALOAD(3);
            case Const.ISTORE -> new ISTORE();
            case Const.LSTORE -> new LSTORE();
            case Const.FSTORE -> new FSTORE();
            case Const.DSTORE -> new DSTORE();
            case Const.ASTORE -> new ASTORE();
            case Const.ISTORE_0 -> new ISTORE(0);
            case Const.ISTORE_1 -> new ISTORE(1);
            case Const.ISTORE_2 -> new ISTORE(2);
            case Const.ISTORE_3 -> new ISTORE(3);
            case Const.LSTORE_0 -> new LSTORE(0);
            case Const.LSTORE_1 -> new LSTORE(1);
            case Const.LSTORE_2 -> new LSTORE(2);
            case Const.LSTORE_3 -> new LSTORE(3);
            case Const.FSTORE_0 -> new FSTORE(0);
            case Const.FSTORE_1 -> new FSTORE(1);
            case Const.FSTORE_2 -> new FSTORE(2);
            case Const.FSTORE_3 -> new FSTORE(3);
            case Const.DSTORE_0 -> new DSTORE(0);
            case Const.DSTORE_1 -> new DSTORE(1);
            case Const.DSTORE_2 -> new DSTORE(2);
            case Const.DSTORE_3 -> new DSTORE(3);
            case Const.ASTORE_0 -> new ASTORE(0);
            case Const.ASTORE_1 -> new ASTORE(1);
            case Const.ASTORE_2 -> new ASTORE(2);
            case Const.ASTORE_3 -> new ASTORE(3);
            case Const.IINC -> new IINC();
            case Const.IFEQ -> new IFEQ();
            case Const.IFNE -> new IFNE();
            case Const.IFLT -> new IFLT();
            case Const.IFGE -> new IFGE();
            case Const.IFGT -> new IFGT();
            case Const.IFLE -> new IFLE();
            case Const.IF_ICMPEQ -> new IF_ICMPEQ();
            case Const.IF_ICMPNE -> new IF_ICMPNE();
            case Const.IF_ICMPLT -> new IF_ICMPLT();
            case Const.IF_ICMPGE -> new IF_ICMPGE();
            case Const.IF_ICMPGT -> new IF_ICMPGT();
            case Const.IF_ICMPLE -> new IF_ICMPLE();
            case Const.IF_ACMPEQ -> new IF_ACMPEQ();
            case Const.IF_ACMPNE -> new IF_ACMPNE();
            case Const.GOTO -> new GOTO();
            case Const.JSR -> new JSR();
            case Const.RET -> new RET();
            case Const.TABLESWITCH -> new TABLESWITCH();
            case Const.LOOKUPSWITCH -> new LOOKUPSWITCH();
            case Const.GETSTATIC -> new GETSTATIC();
            case Const.PUTSTATIC -> new PUTSTATIC();
            case Const.GETFIELD -> new GETFIELD();
            case Const.PUTFIELD -> new PUTFIELD();
            case Const.INVOKEVIRTUAL -> new INVOKEVIRTUAL();
            case Const.INVOKESPECIAL -> new INVOKESPECIAL();
            case Const.INVOKESTATIC -> new INVOKESTATIC();
            case Const.INVOKEINTERFACE -> new INVOKEINTERFACE();
            case Const.INVOKEDYNAMIC -> new INVOKEDYNAMIC();
            case Const.NEW -> new NEW();
            case Const.NEWARRAY -> new NEWARRAY();
            case Const.ANEWARRAY -> new ANEWARRAY();
            case Const.CHECKCAST -> new CHECKCAST();
            case Const.INSTANCEOF -> new INSTANCEOF();
            case Const.MULTIANEWARRAY -> new MULTIANEWARRAY();
            case Const.IFNULL -> new IFNULL();
            case Const.IFNONNULL -> new IFNONNULL();
            case Const.GOTO_W -> new GOTO_W();
            case Const.JSR_W -> new JSR_W();
            case Const.BREAKPOINT -> new BREAKPOINT();
            case Const.IMPDEP1 -> new IMPDEP1();
            case Const.IMPDEP2 -> new IMPDEP2();
            default -> throw new ClassGenException("Illegal opcode detected: " + opcode);
        };
        if (wide && !((obj instanceof LocalVariableInstruction) || (obj instanceof IINC) || (obj instanceof RET))) {
            throw new ClassGenException("Illegal opcode after wide: " + opcode);
        }
        obj.setOpcode(opcode);
        obj.initFromFile(bytes, wide); // Do further initializations, if any
        return obj;
    }

    /**
     * Set comparator to be used for equals().
     *
     * @deprecated (6.0) use the built in comparator, or wrap this class in another
     *             object that implements these methods
     */
    @Deprecated
    public static void setComparator(final InstructionComparator c) {
        cmp = c;
    }
}
