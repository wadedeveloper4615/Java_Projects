package com.wade.decompiler.classfile.attribute;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class Signature extends Attribute {
    private static class MyByteArrayInputStream extends ByteArrayInputStream {
        public MyByteArrayInputStream(String data) {
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

    public Signature(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public Signature(int name_index, int length, int signatureIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_SIGNATURE, name_index, length, constant_pool);
        this.signatureIndex = signatureIndex;
    }

    public Signature(Signature c) {
        this(c.getNameIndex(), c.getLength(), c.getSignatureIndex(), c.getConstantPool());
    }

    @Override
    public void accept(Visitor v) {
        // System.err.println("Visiting non-standard Signature object");
        v.visitSignature(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(signatureIndex);
    }

    public String getSignature() {
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public void setSignatureIndex(int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    @Override
    public String toString() {
        String s = getSignature();
        return "Signature: " + s;
    }

    private static boolean identStart(int ch) {
        return ch == 'T' || ch == 'L';
    }

    // @since 6.0 is no longer
    public static boolean isActualParameterList(String s) {
        return s.startsWith("L") && s.endsWith(">;");
    }

    // @since 6.0 is no longer
    public static boolean isFormalParameterList(String s) {
        return s.startsWith("<") && (s.indexOf(':') > 0);
    }

    private static void matchGJIdent(MyByteArrayInputStream in, StringBuilder buf) {
        int ch;
        matchIdent(in, buf);
        ch = in.read();
        if ((ch == '<') || ch == '(') { // Parameterized or method
            // System.out.println("Enter <");
            buf.append((char) ch);
            matchGJIdent(in, buf);
            while (((ch = in.read()) != '>') && (ch != ')')) { // List of parameters
                if (ch == -1) {
                    throw new IllegalArgumentException("Illegal signature: " + in.getData() + " reaching EOF");
                }
                // System.out.println("Still no >");
                buf.append(", ");
                in.unread();
                matchGJIdent(in, buf); // Recursive call
            }
            // System.out.println("Exit >");
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

    private static void matchIdent(MyByteArrayInputStream in, StringBuilder buf) {
        int ch;
        if ((ch = in.read()) == -1) {
            throw new IllegalArgumentException("Illegal signature: " + in.getData() + " no ident, reaching EOF");
        }
        // System.out.println("return from ident:" + (char)ch);
        if (!identStart(ch)) {
            StringBuilder buf2 = new StringBuilder();
            int count = 1;
            while (Character.isJavaIdentifierPart((char) ch)) {
                buf2.append((char) ch);
                count++;
                ch = in.read();
            }
            if (ch == ':') { // Ok, formal parameter
                in.skip("Ljava/lang/Object".length());
                buf.append(buf2);
                ch = in.read();
                in.unread();
                // System.out.println("so far:" + buf2 + ":next:" +(char)ch);
            } else {
                for (int i = 0; i < count; i++) {
                    in.unread();
                }
            }
            return;
        }
        StringBuilder buf2 = new StringBuilder();
        ch = in.read();
        do {
            buf2.append((char) ch);
            ch = in.read();
            // System.out.println("within ident:"+ (char)ch);
        } while ((ch != -1) && (Character.isJavaIdentifierPart((char) ch) || (ch == '/')));
        buf.append(buf2.toString().replace('/', '.'));
        // System.out.println("regular return ident:"+ (char)ch + ":" + buf2);
        if (ch != -1) {
            in.unread();
        }
    }

    public static String translate(String s) {
        // System.out.println("Sig:" + s);
        StringBuilder buf = new StringBuilder();
        matchGJIdent(new MyByteArrayInputStream(s), buf);
        return buf.toString();
    }
}
