package com.wade.decompiler.util;

import com.wade.decompiler.classfile.ClassFormatException;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.generate.attribute.LocalVariableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public abstract class Utility {

    public static String accessToString(ClassAccessFlagsList access_flags) {
        return accessToString(access_flags, false);
    }

    public static String accessToString(ClassAccessFlagsList access_flags, boolean for_class) {
        return access_flags.toString(for_class);
    }

    public static String classOrInterface(ClassAccessFlagsList flags) {
        return flags.isInterface() ? "interface" : "class";
    }

    public static String compactClassName(String str) {
        return compactClassName(str, true);
    }

    public static String compactClassName(String str, boolean chopit) {
        return compactClassName(str, "java.lang.", chopit);
    }

    public static String compactClassName(String str, String prefix, boolean chopit) {
        int len = prefix.length();
        str = str.replace('/', '.');
        if (chopit) {
            if (str.startsWith(prefix) && (str.substring(len).indexOf('.') == -1)) {
                str = str.substring(len);
            }
        }
        return str;
    }

    public static String convertString(String label) {
        char[] ch = label.toCharArray();
        StringBuilder buf = new StringBuilder();
        for (char element : ch) {
            switch (element) {
                case '\n':
                    buf.append("\\n");
                    break;
                case '\r':
                    buf.append("\\r");
                    break;
                case '\"':
                    buf.append("\\\"");
                    break;
                case '\'':
                    buf.append("\\'");
                    break;
                case '\\':
                    buf.append("\\\\");
                    break;
                default:
                    buf.append(element);
                    break;
            }
        }
        return buf.toString();
    }

    public static String extractClassName(String className, boolean b) {
        String name = Utility.compactClassName(className, b);
        int index = name.lastIndexOf(".") + 1;
        return name.substring(index);
    }

    public static String fillup(String str, int length, boolean left_justify, char fill) {
        int len = length - str.length();
        char[] buf = new char[(len < 0) ? 0 : len];
        for (int j = 0; j < buf.length; j++) {
            buf[j] = fill;
        }
        if (left_justify) {
            return str + new String(buf);
        }
        return new String(buf) + str;
    }

    public static String format(int i, int length, boolean left_justify, char fill) {
        return fillup(Integer.toString(i), length, left_justify, fill);
    }

    public static String methodSignatureToString(String signature, String name, String access, boolean chopit, LocalVariableTableGen vars, boolean constructor) throws ClassFormatException {
        final StringBuilder buf = new StringBuilder("(");
        String type;
        int index;
        int var_index = access.contains("static") ? 0 : 1;
        try {
            // Skip any type arguments to read argument declarations between `(' and `)'
            index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                final String param_type = typeSignatureToString(signature.substring(index), chopit);
                buf.append(param_type);
                if (vars != null) {
                    LocalVariableGen l = vars.getLocalVariable(var_index, 0);
                    if (l != null) {
                        buf.append(" ").append(l.getName());
                    }
                } else {
                    buf.append(" arg").append(var_index);
                }
                if ("double".equals(param_type) || "long".equals(param_type)) {
                    var_index += 2;
                } else {
                    var_index++;
                }
                buf.append(", ");
                index++;
            }
            index++;
            type = typeSignatureToString(signature.substring(index), chopit);
        } catch (final StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 2);
        }
        buf.append(")");
        if (constructor) {
            type = "";
            access = access.trim();
        } else {
            access += " ";
        }
        return access + type + " " + name + buf.toString();
    }

    public static String replace(String str, String old, String new_) {
        int index;
        int old_index;
        try {
            if (str.contains(old)) {
                StringBuilder buf = new StringBuilder();
                old_index = 0;
                while ((index = str.indexOf(old, old_index)) != -1) {
                    buf.append(str.substring(old_index, index));
                    buf.append(new_);
                    old_index = index + old.length();
                }
                buf.append(str.substring(old_index));
                str = buf.toString();
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println(e);
        }
        return str;
    }

    public static String toString(int[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0;; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0;; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static byte typeOfSignature(String signature) throws ClassFormatException {
        try {
            switch (signature.charAt(0)) {
                case 'B':
                    return (byte) TypeEnum.T_BYTE.getTag();
                case 'C':
                    return (byte) TypeEnum.T_CHAR.getTag();
                case 'D':
                    return (byte) TypeEnum.T_DOUBLE.getTag();
                case 'F':
                    return (byte) TypeEnum.T_FLOAT.getTag();
                case 'I':
                    return (byte) TypeEnum.T_INT.getTag();
                case 'J':
                    return (byte) TypeEnum.T_LONG.getTag();
                case 'L':
                case 'T':
                    return (byte) TypeEnum.T_REFERENCE.getTag();
                case '[':
                    return (byte) TypeEnum.T_ARRAY.getTag();
                case 'V':
                    return (byte) TypeEnum.T_VOID.getTag();
                case 'Z':
                    return (byte) TypeEnum.T_BOOLEAN.getTag();
                case 'S':
                    return (byte) TypeEnum.T_SHORT.getTag();
                case '!':
                case '+':
                case '*':
                    return typeOfSignature(signature.substring(1));
                default:
                    throw new ClassFormatException("Invalid method signature: " + signature);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
    }

    public static String typeSignatureToString(String signature, boolean chopit) throws ClassFormatException {
        switch (signature.charAt(0)) {
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            case 'F':
                return "float";
            case 'I':
                return "int";
            case 'J':
                return "long";
            case 'S':
                return "short";
            case 'Z':
                return "boolean";
            case 'V':
                return "void";
        }
        System.out.println(signature);
        return null;
    }
}
