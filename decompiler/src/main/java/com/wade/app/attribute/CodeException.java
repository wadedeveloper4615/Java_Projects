package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Constants;
import com.wade.app.constantpool.Node;

public final class CodeException implements Cloneable, Node, Constants {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public CodeException(final CodeException c) {
        this(c.getStartPC(), c.getEndPC(), c.getHandlerPC(), c.getCatchType());
    }

    public CodeException(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public CodeException(final int startPc, final int endPc, final int handlerPc, final int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    public int getCatchType() {
        return catchType;
    }

    public int getEndPC() {
        return endPc;
    }

    public int getHandlerPC() {
        return handlerPc;
    }

    public int getStartPC() {
        return startPc;
    }
}
