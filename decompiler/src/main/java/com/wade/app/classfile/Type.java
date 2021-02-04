package com.wade.app.classfile;

import java.util.ArrayList;
import java.util.List;

import com.wade.app.Const;
import com.wade.app.exception.ClassFormatException;
import com.wade.app.util.Utility;

public class Type {
    public static final BasicType VOID = new BasicType(Const.T_VOID);
    public static final BasicType BOOLEAN = new BasicType(Const.T_BOOLEAN);
    public static final BasicType INT = new BasicType(Const.T_INT);
    public static final BasicType SHORT = new BasicType(Const.T_SHORT);
    public static final BasicType BYTE = new BasicType(Const.T_BYTE);
    public static final BasicType LONG = new BasicType(Const.T_LONG);
    public static final BasicType DOUBLE = new BasicType(Const.T_DOUBLE);
    public static final BasicType FLOAT = new BasicType(Const.T_FLOAT);
    public static final BasicType CHAR = new BasicType(Const.T_CHAR);
    public static final ObjectType OBJECT = new ObjectType("java.lang.Object");
    public static final ObjectType CLASS = new ObjectType("java.lang.Class");
    public static final ObjectType STRING = new ObjectType("java.lang.String");
    public static final ObjectType STRINGBUFFER = new ObjectType("java.lang.StringBuffer");
    public static final ObjectType THROWABLE = new ObjectType("java.lang.Throwable");
    public static final Type[] NO_ARGS = new Type[0]; // EMPTY, so immutable
    public static final ReferenceType NULL = new ReferenceType() {
    };
    public static final Type UNKNOWN = new Type(Const.T_UNKNOWN, "<unknown object>") {
    };
    private static final ThreadLocal<Integer> consumed_chars = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };
    protected byte type;
    protected String signature;

    protected Type(final byte t, final String s) {
        type = t;
        signature = s;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Type) {
            final Type t = (Type) o;
            return (type == t.type) && signature.equals(t.signature);
        }
        return false;
    }

    /**
     * @return signature for given type.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @return stack size of this type (2 for long and double, 0 for void, 1
     *         otherwise)
     */
    public int getSize() {
        switch (type) {
            case Const.T_DOUBLE:
            case Const.T_LONG:
                return 2;
            case Const.T_VOID:
                return 0;
            default:
                return 1;
        }
    }

    /**
     * @return type as defined in Constants
     */
    public byte getType() {
        return type;
    }

    /**
     * @return hashcode of Type
     */
    @Override
    public int hashCode() {
        return type ^ signature.hashCode();
    }

    /**
     * boolean, short and char variable are considered as int in the stack or local
     * variable area. Returns {@link Type#INT} for {@link Type#BOOLEAN},
     * {@link Type#SHORT} or {@link Type#CHAR}, otherwise returns the given type.
     *
     * @since 6.0
     */
    public Type normalizeForStackOrLocal() {
        if (this == Type.BOOLEAN || this == Type.BYTE || this == Type.SHORT || this == Type.CHAR) {
            return Type.INT;
        }
        return this;
    }

    public void setSignature(final String signature) {
        this.signature = signature;
    }

    static int consumed(final int coded) {
        return coded >> 2;
    }

    static int encode(final int size, final int consumed) {
        return consumed << 2 | size;
    }

    /**
     * Convert arguments of a method (signature) to an array of Type objects.
     *
     * @param signature signature string such as (Ljava/lang/String;)V
     * @return array of argument types
     * @throws ClassFormatException
     */
    public static Type[] getArgumentTypes(final String signature) throws ClassFormatException {
        final List<Type> vec = new ArrayList<>();
        int index;
        Type[] types;
        try {
            // Skip any type arguments to read argument declarations between `(' and `)'
            index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                vec.add(getType(signature.substring(index)));
                // corrected concurrent private static field acess
                index += unwrap(consumed_chars); // update position
            }
        } catch (final StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        types = new Type[vec.size()];
        vec.toArray(types);
        return types;
    }

    static int getArgumentTypesSize(final String signature) throws ClassFormatException {
        int res = 0;
        int index;
        try {
            // Skip any type arguments to read argument declarations between `(' and `)'
            index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                final int coded = getTypeSize(signature.substring(index));
                res += size(coded);
                index += consumed(coded);
            }
        } catch (final StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        return res;
    }

    /**
     * Convert type to Java method signature, e.g. int[] f(java.lang.String x)
     * becomes (Ljava/lang/String;)[I
     *
     * @param return_type what the method returns
     * @param arg_types   what are the argument types
     * @return method signature for given type(s).
     */
    public static String getMethodSignature(final Type return_type, final Type[] arg_types) {
        final StringBuilder buf = new StringBuilder("(");
        if (arg_types != null) {
            for (final Type arg_type : arg_types) {
                buf.append(arg_type.getSignature());
            }
        }
        buf.append(')');
        buf.append(return_type.getSignature());
        return buf.toString();
    }

    /**
     * Convert return value of a method (signature) to a Type object.
     *
     * @param signature signature string such as (Ljava/lang/String;)V
     * @return return type
     * @throws ClassFormatException
     */
    public static Type getReturnType(final String signature) throws ClassFormatException {
        try {
            // Read return type after `)'
            final int index = signature.lastIndexOf(')') + 1;
            return getType(signature.substring(index));
        } catch (final StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
    }

    static int getReturnTypeSize(final String signature) throws StringIndexOutOfBoundsException, ClassFormatException {
        final int index = signature.lastIndexOf(')') + 1;
        return Type.size(getTypeSize(signature.substring(index)));
    }

    public static String getSignature(final java.lang.reflect.Method meth) throws StringIndexOutOfBoundsException, ClassFormatException {
        final StringBuilder sb = new StringBuilder("(");
        final Class<?>[] params = meth.getParameterTypes(); // avoid clone
        for (final Class<?> param : params) {
            sb.append(getType(param).getSignature());
        }
        sb.append(")");
        sb.append(getType(meth.getReturnType()).getSignature());
        return sb.toString();
    }

    public static Type getType(final java.lang.Class<?> cl) throws StringIndexOutOfBoundsException, ClassFormatException {
        if (cl == null) {
            throw new IllegalArgumentException("Class must not be null");
        }
        /*
         * That's an amzingly easy case, because getName() returns the signature. That's
         * what we would have liked anyway.
         */
        if (cl.isArray()) {
            return getType(cl.getName());
        } else if (cl.isPrimitive()) {
            if (cl == Integer.TYPE) {
                return INT;
            } else if (cl == Void.TYPE) {
                return VOID;
            } else if (cl == Double.TYPE) {
                return DOUBLE;
            } else if (cl == Float.TYPE) {
                return FLOAT;
            } else if (cl == Boolean.TYPE) {
                return BOOLEAN;
            } else if (cl == Byte.TYPE) {
                return BYTE;
            } else if (cl == Short.TYPE) {
                return SHORT;
            } else if (cl == Byte.TYPE) {
                return BYTE;
            } else if (cl == Long.TYPE) {
                return LONG;
            } else if (cl == Character.TYPE) {
                return CHAR;
            } else {
                throw new IllegalStateException("Unknown primitive type " + cl);
            }
        } else { // "Real" class
            return ObjectType.getInstance(cl.getName());
        }
    }

    public static Type getType(final String signature) throws StringIndexOutOfBoundsException, ClassFormatException {
        final byte type = Utility.typeOfSignature(signature);
        if (type <= Const.T_VOID) {
            // corrected concurrent private static field acess
            wrap(consumed_chars, 1);
            return BasicType.getType(type);
        } else if (type == Const.T_ARRAY) {
            int dim = 0;
            do { // Count dimensions
                dim++;
            } while (signature.charAt(dim) == '[');
            // Recurse, but just once, if the signature is ok
            final Type t = getType(signature.substring(dim));
            // corrected concurrent private static field acess
            // consumed_chars += dim; // update counter - is replaced by
            final int _temp = unwrap(consumed_chars) + dim;
            wrap(consumed_chars, _temp);
            return new ArrayType(t, dim);
        } else { // type == T_REFERENCE
            // Utility.typeSignatureToString understands how to parse generic types.
            final String parsedSignature = Utility.typeSignatureToString(signature, false);
            wrap(consumed_chars, parsedSignature.length() + 2); // "Lblabla;" `L' and `;' are removed
            return ObjectType.getInstance(parsedSignature.replace('/', '.'));
        }
    }

    public static Type[] getTypes(final java.lang.Class<?>[] classes) throws StringIndexOutOfBoundsException, ClassFormatException {
        final Type[] ret = new Type[classes.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = getType(classes[i]);
        }
        return ret;
    }

    static int getTypeSize(final String signature) throws StringIndexOutOfBoundsException, ClassFormatException {
        final byte type = Utility.typeOfSignature(signature);
        if (type <= Const.T_VOID) {
            return encode(BasicType.getType(type).getSize(), 1);
        } else if (type == Const.T_ARRAY) {
            int dim = 0;
            do { // Count dimensions
                dim++;
            } while (signature.charAt(dim) == '[');
            // Recurse, but just once, if the signature is ok
            final int consumed = consumed(getTypeSize(signature.substring(dim)));
            return encode(1, dim + consumed);
        } else { // type == T_REFERENCE
            final int index = signature.indexOf(';'); // Look for closing `;'
            if (index < 0) {
                throw new ClassFormatException("Invalid signature: " + signature);
            }
            return encode(1, index + 1);
        }
    }

    static int size(final int coded) {
        return coded & 3;
    }

    private static int unwrap(final ThreadLocal<Integer> tl) {
        return tl.get().intValue();
    }

    private static void wrap(final ThreadLocal<Integer> tl, final int value) {
        tl.set(Integer.valueOf(value));
    }
}
