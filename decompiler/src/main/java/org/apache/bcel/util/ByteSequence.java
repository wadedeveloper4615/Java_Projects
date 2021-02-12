package org.apache.bcel.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public final class ByteSequence extends DataInputStream {
    private final ByteArrayStream byteStream;

    public ByteSequence(final byte[] bytes) {
        super(new ByteArrayStream(bytes));
        byteStream = (ByteArrayStream) in;
    }

    public int getIndex() {
        return byteStream.getPosition();
    }

    void unreadByte() {
        byteStream.unreadByte();
    }

    private static final class ByteArrayStream extends ByteArrayInputStream {
        ByteArrayStream(final byte[] bytes) {
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
}
