package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;

public class ConstantFieldref extends ConstantCP {
    public ConstantFieldref(DataInputStream input) throws IOException {
        super(Const.CONSTANT_Fieldref, input);
    }

    public ConstantFieldref(final int class_index, final int name_and_type_index) {
        super(Const.CONSTANT_Fieldref, class_index, name_and_type_index);
    }
}
