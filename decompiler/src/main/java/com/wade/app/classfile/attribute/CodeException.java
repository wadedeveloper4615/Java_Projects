package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public  class CodeException {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public CodeException(DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public CodeException(int startPc, int endPc, int handlerPc, int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    public int getCatchType() {
        return catchType;
    }

    public int getEndPc() {
        return endPc;
    }

    public int getHandlerPc() {
        return handlerPc;
    }

    public int getStartPc() {
        return startPc;
    }
}
