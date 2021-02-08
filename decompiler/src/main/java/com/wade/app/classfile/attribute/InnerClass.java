package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public class InnerClass {
    private int innerClassIndex;
    private int outerClassIndex;
    private int innerNameIndex;
    private int innerAccessFlags;

    public InnerClass(DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public InnerClass(int innerClassIndex, int outerClassIndex, int innerNameIndex, int innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = innerAccessFlags;
    }

    public int getInnerAccessFlags() {
        return innerAccessFlags;
    }

    public int getInnerClassIndex() {
        return innerClassIndex;
    }

    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    public int getOuterClassIndex() {
        return outerClassIndex;
    }
}
