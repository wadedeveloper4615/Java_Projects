package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public class StackMapEntry implements Node, Cloneable {
    private int frameType;
    private int byteCodeOffset;
    private StackMapType[] typesOfLocals;
    private StackMapType[] typesOfStackItems;
    private ConstantPool constantPool;

    StackMapEntry(DataInput input, ConstantPool constantPool) throws IOException {
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
            throw new ClassFormatException("Invalid frame type found while parsing stack map table: " + frameType);
        }
    }

    @java.lang.Deprecated
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

    @Override
    public void accept(Visitor v) {
        v.visitStackMapEntry(this);
    }

    public StackMapEntry copy() {
        StackMapEntry e;
        try {
            e = (StackMapEntry) clone();
        } catch (CloneNotSupportedException ex) {
            throw new Error("Clone Not Supported");
        }
        e.typesOfLocals = new StackMapType[typesOfLocals.length];
        for (int i = 0; i < typesOfLocals.length; i++) {
            e.typesOfLocals[i] = typesOfLocals[i].copy();
        }
        e.typesOfStackItems = new StackMapType[typesOfStackItems.length];
        for (int i = 0; i < typesOfStackItems.length; i++) {
            e.typesOfStackItems[i] = typesOfStackItems[i].copy();
        }
        return e;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.write(frameType);
        if (frameType >= Const.SAME_FRAME && frameType <= Const.SAME_FRAME_MAX) {
            // nothing to be done
        } else if (frameType >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frameType <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
            typesOfStackItems[0].dump(file);
        } else if (frameType == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
            file.writeShort(byteCodeOffset);
            typesOfStackItems[0].dump(file);
        } else if (frameType >= Const.CHOP_FRAME && frameType <= Const.CHOP_FRAME_MAX) {
            file.writeShort(byteCodeOffset);
        } else if (frameType == Const.SAME_FRAME_EXTENDED) {
            file.writeShort(byteCodeOffset);
        } else if (frameType >= Const.APPEND_FRAME && frameType <= Const.APPEND_FRAME_MAX) {
            file.writeShort(byteCodeOffset);
            for (StackMapType type : typesOfLocals) {
                type.dump(file);
            }
        } else if (frameType == Const.FULL_FRAME) {
            file.writeShort(byteCodeOffset);
            file.writeShort(typesOfLocals.length);
            for (StackMapType type : typesOfLocals) {
                type.dump(file);
            }
            file.writeShort(typesOfStackItems.length);
            for (StackMapType type : typesOfStackItems) {
                type.dump(file);
            }
        } else {
            throw new ClassFormatException("Invalid Stack map table tag: " + frameType);
        }
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

    int getMapEntrySize() {
        if (frameType >= Const.SAME_FRAME && frameType <= Const.SAME_FRAME_MAX) {
            return 1;
        } else if (frameType >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frameType <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
            return 1 + (typesOfStackItems[0].hasIndex() ? 3 : 1);
        } else if (frameType == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
            return 3 + (typesOfStackItems[0].hasIndex() ? 3 : 1);
        } else if (frameType >= Const.CHOP_FRAME && frameType <= Const.CHOP_FRAME_MAX) {
            return 3;
        } else if (frameType == Const.SAME_FRAME_EXTENDED) {
            return 3;
        } else if (frameType >= Const.APPEND_FRAME && frameType <= Const.APPEND_FRAME_MAX) {
            int len = 3;
            for (StackMapType types_of_local : typesOfLocals) {
                len += types_of_local.hasIndex() ? 3 : 1;
            }
            return len;
        } else if (frameType == Const.FULL_FRAME) {
            int len = 7;
            for (StackMapType types_of_local : typesOfLocals) {
                len += types_of_local.hasIndex() ? 3 : 1;
            }
            for (StackMapType types_of_stack_item : typesOfStackItems) {
                len += types_of_stack_item.hasIndex() ? 3 : 1;
            }
            return len;
        } else {
            throw new IllegalStateException("Invalid StackMap frameType: " + frameType);
        }
    }

    public int getNumberOfLocals() {
        return typesOfLocals.length;
    }

    public int getNumberOfStackItems() {
        return typesOfStackItems.length;
    }

    public StackMapType[] getTypesOfLocals() {
        return typesOfLocals;
    }

    public StackMapType[] getTypesOfStackItems() {
        return typesOfStackItems;
    }

    public void setByteCodeOffset(int new_offset) {
        if (new_offset < 0 || new_offset > 32767) {
            throw new IllegalArgumentException("Invalid StackMap offset: " + new_offset);
        }
        if (frameType >= Const.SAME_FRAME && frameType <= Const.SAME_FRAME_MAX) {
            if (new_offset > Const.SAME_FRAME_MAX) {
                frameType = Const.SAME_FRAME_EXTENDED;
            } else {
                frameType = new_offset;
            }
        } else if (frameType >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frameType <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
            if (new_offset > Const.SAME_FRAME_MAX) {
                frameType = Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED;
            } else {
                frameType = Const.SAME_LOCALS_1_STACK_ITEM_FRAME + new_offset;
            }
        } else if (frameType == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (frameType >= Const.CHOP_FRAME && frameType <= Const.CHOP_FRAME_MAX) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (frameType == Const.SAME_FRAME_EXTENDED) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (frameType >= Const.APPEND_FRAME && frameType <= Const.APPEND_FRAME_MAX) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (frameType == Const.FULL_FRAME) { // CHECKSTYLE IGNORE EmptyBlock
        } else {
            throw new IllegalStateException("Invalid StackMap frameType: " + frameType);
        }
        byteCodeOffset = new_offset;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setFrameType(int f) {
        if (f >= Const.SAME_FRAME && f <= Const.SAME_FRAME_MAX) {
            byteCodeOffset = f - Const.SAME_FRAME;
        } else if (f >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && f <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
            byteCodeOffset = f - Const.SAME_LOCALS_1_STACK_ITEM_FRAME;
        } else if (f == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (f >= Const.CHOP_FRAME && f <= Const.CHOP_FRAME_MAX) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (f == Const.SAME_FRAME_EXTENDED) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (f >= Const.APPEND_FRAME && f <= Const.APPEND_FRAME_MAX) { // CHECKSTYLE IGNORE EmptyBlock
        } else if (f == Const.FULL_FRAME) { // CHECKSTYLE IGNORE EmptyBlock
        } else {
            throw new IllegalArgumentException("Invalid StackMap frameType");
        }
        frameType = f;
    }

    @java.lang.Deprecated
    public void setNumberOfLocals(int n) { // TODO unused
    }

    @java.lang.Deprecated
    public void setNumberOfStackItems(int n) { // TODO unused
    }

    public void setTypesOfLocals(StackMapType[] types) {
        typesOfLocals = types != null ? types : new StackMapType[0];
    }

    public void setTypesOfStackItems(StackMapType[] types) {
        typesOfStackItems = types != null ? types : new StackMapType[0];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(64);
        buf.append("(");
        if (frameType >= Const.SAME_FRAME && frameType <= Const.SAME_FRAME_MAX) {
            buf.append("SAME");
        } else if (frameType >= Const.SAME_LOCALS_1_STACK_ITEM_FRAME && frameType <= Const.SAME_LOCALS_1_STACK_ITEM_FRAME_MAX) {
            buf.append("SAME_LOCALS_1_STACK");
        } else if (frameType == Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED) {
            buf.append("SAME_LOCALS_1_STACK_EXTENDED");
        } else if (frameType >= Const.CHOP_FRAME && frameType <= Const.CHOP_FRAME_MAX) {
            buf.append("CHOP ").append(String.valueOf(251 - frameType));
        } else if (frameType == Const.SAME_FRAME_EXTENDED) {
            buf.append("SAME_EXTENDED");
        } else if (frameType >= Const.APPEND_FRAME && frameType <= Const.APPEND_FRAME_MAX) {
            buf.append("APPEND ").append(String.valueOf(frameType - 251));
        } else if (frameType == Const.FULL_FRAME) {
            buf.append("FULL");
        } else {
            buf.append("UNKNOWN (").append(frameType).append(")");
        }
        buf.append(", offset delta=").append(byteCodeOffset);
        if (typesOfLocals.length > 0) {
            buf.append(", locals={");
            for (int i = 0; i < typesOfLocals.length; i++) {
                buf.append(typesOfLocals[i]);
                if (i < typesOfLocals.length - 1) {
                    buf.append(", ");
                }
            }
            buf.append("}");
        }
        if (typesOfStackItems.length > 0) {
            buf.append(", stack items={");
            for (int i = 0; i < typesOfStackItems.length; i++) {
                buf.append(typesOfStackItems[i]);
                if (i < typesOfStackItems.length - 1) {
                    buf.append(", ");
                }
            }
            buf.append("}");
        }
        buf.append(")");
        return buf.toString();
    }

    public void updateByteCodeOffset(int delta) {
        setByteCodeOffset(byteCodeOffset + delta);
    }
}
