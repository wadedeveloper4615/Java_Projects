package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;

public class ConstantUtf8 extends Constant {
    private static volatile int considered = 0;
    private static volatile int created = 0;
    private static volatile int hits = 0;
    private static volatile int skipped = 0;
    private String value;

    public ConstantUtf8(final ConstantUtf8 constantUtf8) {
        this(constantUtf8.getBytes());
    }

    ConstantUtf8(final DataInputStream dataInput) throws IOException {
        super(Const.CONSTANT_Utf8);
        value = dataInput.readUTF();
        created++;
    }

    /**
     * @param value Data
     */
    public ConstantUtf8(final String value) {
        super(Const.CONSTANT_Utf8);
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        this.value = value;
        created++;
    }

    public String getBytes() {
        return value;
    }
}
