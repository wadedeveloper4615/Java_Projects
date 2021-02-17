package com.wade.decompiler.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ByteSequence extends DataInputStream {
    private static class ByteArrayStream extends ByteArrayInputStream {
        ByteArrayStream(byte[] bytes) {
            super(bytes);
        }

        int getPosition() {
            // pos is protected in ByteArrayInputStream
            return pos;
        }

        void unreadByte() {
            if (pos > 0) {
                pos--;
            }
        }
    }

    private ByteArrayStream byteStream;

    public ByteSequence(byte[] bytes) {
        super(new ByteArrayStream(bytes));
        byteStream = (ByteArrayStream) in;
    }

    public int getIndex() {
        return byteStream.getPosition();
    }

    void unreadByte() {
        byteStream.unreadByte();
    }
}
