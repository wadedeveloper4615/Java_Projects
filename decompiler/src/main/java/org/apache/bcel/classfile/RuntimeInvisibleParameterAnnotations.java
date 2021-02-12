
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.constant.ConstantPool;

public class RuntimeInvisibleParameterAnnotations extends ParameterAnnotations {

    public RuntimeInvisibleParameterAnnotations(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }
}
