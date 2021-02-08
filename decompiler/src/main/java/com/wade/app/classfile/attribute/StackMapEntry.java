package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class StackMapEntry {
    private int frameType;
    private int byteCodeOffset;
    private StackMapType[] typesOfLocals;
    private StackMapType[] typesOfStackItems;
    private ConstantPool constantPool;

    public StackMapEntry(DataInputStream input, ConstantPool constantPool) throws IOException {
        this(input.readByte() & 0xFF, -1, null, null, constantPool);

        if (frameType >= Const.SAME_FRAME && frameType <= Const.SAME_FRAME_MAX) {
            byteCodeOffset = frameType - Const.SAME_FRAME;
        } else if (frameType >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frameType <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
            byteCodeOffset = frameType - Const.SAME_LOCALS_1_STACK_ITEM_FRAME;
            typesOfStackItems = new StackMapType[1];
            typesOfStackItems[0] = new StackMapType(input, constantPool);
        } else if (frameType == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
            byteCodeOffset = input.readShort();
            typesOfStackItems = new StackMapType[1];
            typesOfStackItems[0] = new StackMapType(input, constantPool);
        } else if (frameType >= Const.CHOP_FRAME && frameType <= Const.CHOP_FRAME_MAX) {
            byteCodeOffset = input.readShort();
        } else if (frameType == Const.SAME_FRAME_EXTENDED) {
            byteCodeOffset = input.readShort();
        } else if (frameType >= Const.APPEND_FRAME && frameType <= Const.APPEND_FRAME_MAX) {
            byteCodeOffset = input.readShort();
            int number_of_locals = frameType - 251;
            typesOfLocals = new StackMapType[number_of_locals];
            for (int i = 0; i < number_of_locals; i++) {
                typesOfLocals[i] = new StackMapType(input, constantPool);
            }
        } else if (frameType == Const.FULL_FRAME) {
            byteCodeOffset = input.readShort();
            int number_of_locals = input.readShort();
            typesOfLocals = new StackMapType[number_of_locals];
            for (int i = 0; i < number_of_locals; i++) {
                typesOfLocals[i] = new StackMapType(input, constantPool);
            }
            int number_of_stack_items = input.readShort();
            typesOfStackItems = new StackMapType[number_of_stack_items];
            for (int i = 0; i < number_of_stack_items; i++) {
                typesOfStackItems[i] = new StackMapType(input, constantPool);
            }
        } else {
            /* Can't happen */
            throw new ClassFormatException("Invalid frame type found while parsing stack map table: " + frameType);
        }
    }

    public StackMapEntry(int byteCodeOffset, int numberOfLocals, StackMapType[] typesOfLocals, int numberOfStackItems, StackMapType[] typesOfStackItems, ConstantPool constantPool) {
        this.byteCodeOffset = byteCodeOffset;
        this.typesOfLocals = typesOfLocals != null ? typesOfLocals : new StackMapType[0];
        this.typesOfStackItems = typesOfStackItems != null ? typesOfStackItems : new StackMapType[0];
        this.constantPool = constantPool;
    }

    public StackMapEntry(int tag, int byteCodeOffset, StackMapType[] typesOfLocals, StackMapType[] typesOfStackItems, ConstantPool constantPool) {
        this.frameType = tag;
        this.byteCodeOffset = byteCodeOffset;
        this.typesOfLocals = typesOfLocals != null ? typesOfLocals : new StackMapType[0];
        this.typesOfStackItems = typesOfStackItems != null ? typesOfStackItems : new StackMapType[0];
        this.constantPool = constantPool;
    }

    public int getByteCodeOffset() {
        return byteCodeOffset;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getFrameType() {
        return frameType;
    }

    public StackMapType[] getTypesOfLocals() {
        return typesOfLocals;
    }

    public StackMapType[] getTypesOfStackItems() {
        return typesOfStackItems;
    }
}
