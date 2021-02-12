
package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.MethodParameter;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public class MethodParameters extends Attribute {

    private MethodParameter[] parameters = new MethodParameter[0];

    public MethodParameters(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);

        final int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input);
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitMethodParameters(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final MethodParameters c = (MethodParameters) clone();
        c.parameters = new MethodParameter[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            c.parameters[i] = parameters[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeByte(parameters.length);
        for (final MethodParameter parameter : parameters) {
            parameter.dump(file);
        }
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(final MethodParameter[] parameters) {
        this.parameters = parameters;
    }
}
