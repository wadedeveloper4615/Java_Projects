package org.apache.bcel.classfile.attribute;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.enums.ClassFileConstants;

public final class Signature extends Attribute {
    private static final class MyByteArrayInputStream extends ByteArrayInputStream {
        MyByteArrayInputStream(final String data) {
            super(data.getBytes());
        }

        String getData() {
            return new String(buf);
        }

        void unread() {
            if (pos > 0) {
                pos--;
            }
        }
    }

    private int signatureIndex;

    public Signature(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public Signature(final int name_index, final int length, final int signatureIndex, final ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_SIGNATURE, name_index, length, constant_pool);
        this.signatureIndex = signatureIndex;
    }

    public Signature(final Signature c) {
        this(c.getNameIndex(), c.getLength(), c.getSignatureIndex(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitSignature(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(signatureIndex);
    }

    public String getSignature() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public void setSignatureIndex(final int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    @Override
    public String toString() {
        final String s = getSignature();
        return "Signature: " + s;
    }

    private static boolean identStart(final int ch) {
        return ch == 'T' || ch == 'L';
    }

    public static boolean isActualParameterList(final String s) {
        return s.startsWith("L") && s.endsWith(">;");
    }

    public static boolean isFormalParameterList(final String s) {
        return s.startsWith("<") && (s.indexOf(':') > 0);
    }

    private static void matchGJIdent(final MyByteArrayInputStream in, final StringBuilder buf) {
        int ch;
        matchIdent(in, buf);
        ch = in.read();
        if ((ch == '<') || ch == '(') {
            buf.append((char) ch);
            matchGJIdent(in, buf);
            while (((ch = in.read()) != '>') && (ch != ')')) {
                if (ch == -1) {
                    throw new IllegalArgumentException("Illegal signature: " + in.getData() + " reaching EOF");
                }
                buf.append(", ");
                in.unread();
                matchGJIdent(in, buf);
            }
            buf.append((char) ch);
        } else {
            in.unread();
        }
        ch = in.read();
        if (identStart(ch)) {
            in.unread();
            matchGJIdent(in, buf);
        } else if (ch == ')') {
            in.unread();
            return;
        } else if (ch != ';') {
            throw new IllegalArgumentException("Illegal signature: " + in.getData() + " read " + (char) ch);
        }
    }

    private static void matchIdent(final MyByteArrayInputStream in, final StringBuilder buf) {
        int ch;
        if ((ch = in.read()) == -1) {
            throw new IllegalArgumentException("Illegal signature: " + in.getData() + " no ident, reaching EOF");
        }
        if (!identStart(ch)) {
            final StringBuilder buf2 = new StringBuilder();
            int count = 1;
            while (Character.isJavaIdentifierPart((char) ch)) {
                buf2.append((char) ch);
                count++;
                ch = in.read();
            }
            if (ch == ':') {
                in.skip("Ljava/lang/Object".length());
                buf.append(buf2);
                ch = in.read();
                in.unread();
            } else {
                for (int i = 0; i < count; i++) {
                    in.unread();
                }
            }
            return;
        }
        final StringBuilder buf2 = new StringBuilder();
        ch = in.read();
        do {
            buf2.append((char) ch);
            ch = in.read();
        } while ((ch != -1) && (Character.isJavaIdentifierPart((char) ch) || (ch == '/')));
        buf.append(buf2.toString().replace('/', '.'));
        if (ch != -1) {
            in.unread();
        }
    }

    public static String translate(final String s) {
        final StringBuilder buf = new StringBuilder();
        matchGJIdent(new MyByteArrayInputStream(s), buf);
        return buf.toString();
    }
}
