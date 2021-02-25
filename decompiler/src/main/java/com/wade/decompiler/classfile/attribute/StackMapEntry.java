package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.constants.Const;

public class StackMapEntry {
    private int frameType;
    private int byteCodeOffset;
    private StackMapType[] typesOfLocals;
    private StackMapType[] typesOfStackItems;
    private ConstantPool constantPool;

    public StackMapEntry(DataInput input, ConstantPool constantPool) throws IOException {
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

    public StackMapEntry(int tag, int byteCodeOffset, StackMapType[] typesOfLocals, StackMapType[] typesOfStackItems, ConstantPool constantPool) {
        this.frameType = tag;
        this.byteCodeOffset = byteCodeOffset;
        this.typesOfLocals = typesOfLocals != null ? typesOfLocals : new StackMapType[0];
        this.typesOfStackItems = typesOfStackItems != null ? typesOfStackItems : new StackMapType[0];
        this.constantPool = constantPool;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StackMapEntry other = (StackMapEntry) obj;
        if (byteCodeOffset != other.byteCodeOffset)
            return false;
        if (constantPool == null) {
            if (other.constantPool != null)
                return false;
        } else if (!constantPool.equals(other.constantPool))
            return false;
        if (frameType != other.frameType)
            return false;
        if (!Arrays.equals(typesOfLocals, other.typesOfLocals))
            return false;
        if (!Arrays.equals(typesOfStackItems, other.typesOfStackItems))
            return false;
        return true;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + byteCodeOffset;
        result = prime * result + ((constantPool == null) ? 0 : constantPool.hashCode());
        result = prime * result + frameType;
        result = prime * result + Arrays.hashCode(typesOfLocals);
        result = prime * result + Arrays.hashCode(typesOfStackItems);
        return result;
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
}
