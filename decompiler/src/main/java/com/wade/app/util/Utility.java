package com.wade.app.util;

import java.util.ArrayList;
import java.util.List;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;
import com.wade.app.classfile.attribute.LocalVariable;
import com.wade.app.classfile.attribute.LocalVariableTable;
import com.wade.app.enums.ClassAccessFlags;

public abstract class Utility {
    private static ThreadLocal<Integer> consumed_chars = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    private static int FREE_CHARS = 48;
    private static int[] CHAR_MAP = new int[FREE_CHARS];
    private static int[] MAP_CHAR = new int[256]; // Reverse map

    static {
        int j = 0;
        for (int i = 'A'; i <= 'Z'; i++) {
            CHAR_MAP[j] = i;
            MAP_CHAR[i] = j;
            j++;
        }
        for (int i = 'g'; i <= 'z'; i++) {
            CHAR_MAP[j] = i;
            MAP_CHAR[i] = j;
            j++;
        }
        CHAR_MAP[j] = '$';
        MAP_CHAR['$'] = j;
        j++;
        CHAR_MAP[j] = '_';
        MAP_CHAR['_'] = j;
    }

    public static String accessToString(ClassAccessFlags[] accessFlags, boolean for_class) {
        StringBuilder buf = new StringBuilder();
        for (ClassAccessFlags flag : accessFlags) {
            buf.append(accessToString(flag.getFlag(), for_class) + " ");
        }
        return buf.toString();
    }

    public static String accessToString(int access_flags) {
        return accessToString(access_flags, false);
    }

    public static String accessToString(int access_flags, boolean for_class) {
        StringBuilder buf = new StringBuilder();
        for (ClassAccessFlags flag : ClassAccessFlags.values()) {
            if (flag.getFlag() < ClassAccessFlags.MAX_ACC_FLAG_I.getFlag()) {
                if ((access_flags & flag.getFlag()) != 0) {
                    if (for_class && ((flag == ClassAccessFlags.ACC_SUPER) || (flag == ClassAccessFlags.ACC_INTERFACE))) {
                        continue;
                    }
                    buf.append(flag.getName());
                }
            }
        }
        return buf.toString().trim();
    }

    private static short byteToShort(byte b) {
        return (b < 0) ? (short) (256 + b) : (short) b;
    }

    public static String classOrInterface(int access_flags) {
        return ((access_flags & ClassAccessFlags.ACC_INTERFACE.getFlag()) != 0) ? "interface" : "class";
    }

    public static int clearBit(int flag, int i) {
        int bit = pow2(i);
        return (flag & bit) == 0 ? flag : flag ^ bit;
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

    private static int countBrackets(String brackets) {
        char[] chars = brackets.toCharArray();
        int count = 0;
        boolean open = false;
        for (char c : chars) {
            switch (c) {
                case '[':
                    if (open) {
                        throw new IllegalArgumentException("Illegally nested brackets:" + brackets);
                    }
                    open = true;
                    break;
                case ']':
                    if (!open) {
                        throw new IllegalArgumentException("Illegally nested brackets:" + brackets);
                    }
                    open = false;
                    count++;
                    break;
                default:
                    // Don't care
                    break;
            }
        }
        if (open) {
            throw new IllegalArgumentException("Illegally nested brackets:" + brackets);
        }
        return count;
    }

    static boolean equals(byte[] a, byte[] b) {
        int size;
        if ((size = a.length) != b.length) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
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

    public static String getSignature(String type) {
        StringBuilder buf = new StringBuilder();
        char[] chars = type.toCharArray();
        boolean char_found = false;
        boolean delim = false;
        int index = -1;
        loop: for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                case '\f':
                    if (char_found) {
                        delim = true;
                    }
                    break;
                case '[':
                    if (!char_found) {
                        throw new IllegalArgumentException("Illegal type: " + type);
                    }
                    index = i;
                    break loop;
                default:
                    char_found = true;
                    if (!delim) {
                        buf.append(chars[i]);
                    }
            }
        }
        int brackets = 0;
        if (index > 0) {
            brackets = countBrackets(type.substring(index));
        }
        type = buf.toString();
        buf.setLength(0);
        for (int i = 0; i < brackets; i++) {
            buf.append('[');
        }
        boolean found = false;
        for (int i = Const.T_BOOLEAN; (i <= Const.T_VOID) && !found; i++) {
            if (Const.getTypeName(i).equals(type)) {
                found = true;
                buf.append(Const.getShortTypeName(i));
            }
        }
        if (!found) {
            buf.append('L').append(type.replace('.', '/')).append(';');
        }
        return buf.toString();
    }

    public static boolean isJavaIdentifierPart(char ch) {
        return ((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) || ((ch >= '0') && (ch <= '9')) || (ch == '_');
    }

    public static boolean isSet(int flag, int i) {
        return (flag & pow2(i)) != 0;
    }

    public static String[] methodSignatureArgumentTypes(String signature) throws ClassFormatException {
        return methodSignatureArgumentTypes(signature, true);
    }

    public static String[] methodSignatureArgumentTypes(String signature, boolean chopit) throws ClassFormatException {
        List<String> vec = new ArrayList<>();
        int index;
        try {
            index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                vec.add(typeSignatureToString(signature.substring(index), chopit));
                index += unwrap(consumed_chars);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        return vec.toArray(new String[vec.size()]);
    }

    public static String methodSignatureReturnType(String signature) throws ClassFormatException {
        return methodSignatureReturnType(signature, true);
    }

    public static String methodSignatureReturnType(String signature, boolean chopit) throws ClassFormatException {
        int index;
        String type;
        try {
            index = signature.lastIndexOf(')') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            type = typeSignatureToString(signature.substring(index), chopit);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        return type;
    }

    public static String methodSignatureToString(String signature, String name, String className, String access) {
        return methodSignatureToString(signature, name, className, access, true);
    }

    public static String methodSignatureToString(String signature, String name, String className, String access, boolean chopit) {
        return methodSignatureToString(signature, name, className, access, chopit, null);
    }

    public static String methodSignatureToString(String signature, String name, String className, String access, boolean chopit, LocalVariableTable vars) throws ClassFormatException {
        StringBuilder buf = new StringBuilder("(");
        String type;
        int index;
        int var_index = access.contains("static") ? 0 : 1;
        try {
            index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                String param_type = typeSignatureToString(signature.substring(index), chopit);
                buf.append(param_type);
                if (vars != null) {
                    LocalVariable l = vars.getLocalVariable(var_index, 0);
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
                index += unwrap(consumed_chars);
            }
            index++;
            type = typeSignatureToString(signature.substring(index), chopit);
            if (name.equals("<init>")) {
                name = className;
                type = "";
            } else {
                type = " " + type;
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 2);
        }
        buf.append(")");
        return access + type + " " + name + buf.toString();
    }

    public static String methodTypeToSignature(String ret, String[] argv) throws ClassFormatException {
        StringBuilder buf = new StringBuilder("(");
        String str;
        if (argv != null) {
            for (String element : argv) {
                str = getSignature(element);
                if (str.endsWith("V")) {
                    throw new ClassFormatException("Invalid type: " + element);
                }
                buf.append(str);
            }
        }
        str = getSignature(ret);
        buf.append(")").append(str);
        return buf.toString();
    }

    private static int pow2(int n) {
        return 1 << n;
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

    /*
     * public static short searchOpcode(String name) { name =
     * name.toLowerCase(Locale.ENGLISH); for (short i = 0; i <
     * Const.OPCODE_NAMES_LENGTH; i++) { if (Const.getOpcodeName(i).equals(name)) {
     * return i; } } return -1; }
     */
    public static int setBit(int flag, int i) {
        return flag | pow2(i);
    }

    public static String signatureToString(String signature) {
        return signatureToString(signature, true);
    }

    public static String signatureToString(String signature, boolean chopit) {
        String type = "";
        String typeParams = "";
        int index = 0;
        if (signature.charAt(0) == '<') {
            typeParams = typeParamTypesToString(signature, chopit);
            index += unwrap(consumed_chars);
        }
        if (signature.charAt(index) == '(') {
            type = typeParams + typeSignaturesToString(signature.substring(index), chopit, ')');
            index += unwrap(consumed_chars);
            type = type + typeSignatureToString(signature.substring(index), chopit);
            index += unwrap(consumed_chars);
            return type;
        }
        type = typeSignatureToString(signature.substring(index), chopit);
        index += unwrap(consumed_chars); // update position
        if ((typeParams.length() == 0) && (index == signature.length())) {
            return type;
        }
        StringBuilder typeClass = new StringBuilder(typeParams);
        typeClass.append(" extends ");
        typeClass.append(type);
        if (index < signature.length()) {
            typeClass.append(" implements ");
            typeClass.append(typeSignatureToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars); // update position
        }
        while (index < signature.length()) {
            typeClass.append(", ");
            typeClass.append(typeSignatureToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars);
        }
        return typeClass.toString();
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            short b = byteToShort(bytes[i]);
            String hex = Integer.toHexString(b);
            if (b < 0x10) {
                buf.append('0');
            }
            buf.append(hex);
            if (i < bytes.length - 1) {
                buf.append(' ');
            }
        }
        return buf.toString();
    }

    public static byte typeOfMethodSignature(String signature) throws ClassFormatException {
        int index;
        try {
            if (signature.charAt(0) != '(') {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            index = signature.lastIndexOf(')') + 1;
            return typeOfSignature(signature.substring(index));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
    }

    public static byte typeOfSignature(String signature) throws ClassFormatException {
        try {
            switch (signature.charAt(0)) {
                case 'B':
                    return Const.T_BYTE;
                case 'C':
                    return Const.T_CHAR;
                case 'D':
                    return Const.T_DOUBLE;
                case 'F':
                    return Const.T_FLOAT;
                case 'I':
                    return Const.T_INT;
                case 'J':
                    return Const.T_LONG;
                case 'L':
                case 'T':
                    return Const.T_REFERENCE;
                case '[':
                    return Const.T_ARRAY;
                case 'V':
                    return Const.T_VOID;
                case 'Z':
                    return Const.T_BOOLEAN;
                case 'S':
                    return Const.T_SHORT;
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

    private static String typeParamTypesToString(String signature, boolean chopit) {
        // The first character is guranteed to be '<'
        StringBuilder typeParams = new StringBuilder("<");
        int index = 1; // skip the '<'
        // get the first TypeParameter
        typeParams.append(typeParamTypeToString(signature.substring(index), chopit));
        index += unwrap(consumed_chars); // update position
        // are there more TypeParameters?
        while (signature.charAt(index) != '>') {
            typeParams.append(", ");
            typeParams.append(typeParamTypeToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars); // update position
        }
        wrap(consumed_chars, index + 1); // account for the '>' char
        return typeParams.append(">").toString();
    }

    private static String typeParamTypeToString(String signature, boolean chopit) {
        int index = signature.indexOf(':');
        if (index <= 0) {
            throw new ClassFormatException("Invalid type parameter signature: " + signature);
        }
        StringBuilder typeParam = new StringBuilder(signature.substring(0, index));
        index++; // account for the ':'
        if (signature.charAt(index) != ':') {
            typeParam.append(" extends ");
            typeParam.append(typeSignatureToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars); // update position
        }
        // look for interface bounds
        while (signature.charAt(index) == ':') {
            index++; // skip over the ':'
            typeParam.append(" & ");
            typeParam.append(typeSignatureToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars); // update position
        }
        wrap(consumed_chars, index);
        return typeParam.toString();
    }

    private static String typeSignaturesToString(String signature, boolean chopit, char term) {
        StringBuilder typeList = new StringBuilder(signature.substring(0, 1));
        int index = 1; // skip the 'open' character
        if (signature.charAt(index) != term) {
            typeList.append(typeSignatureToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars); // update position
        }
        while (signature.charAt(index) != term) {
            typeList.append(", ");
            typeList.append(typeSignatureToString(signature.substring(index), chopit));
            index += unwrap(consumed_chars); // update position
        }
        wrap(consumed_chars, index + 1); // account for the term char
        return typeList.append(term).toString();
    }

    public static String typeSignatureToString(String signature, boolean chopit) throws ClassFormatException {
        wrap(consumed_chars, 1);
        try {
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
                case 'T': { // TypeVariableSignature
                    int index = signature.indexOf(';'); // Look for closing `;'
                    if (index < 0) {
                        throw new ClassFormatException("Invalid type variable signature: " + signature);
                    }
                    // corrected concurrent private static field acess
                    wrap(consumed_chars, index + 1); // "Tblabla;" `T' and `;' are removed
                    return compactClassName(signature.substring(1, index), chopit);
                }
                case 'L': { // Full class name
                    // should this be a while loop? can there be more than
                    // one generic clause? (markro)
                    int fromIndex = signature.indexOf('<'); // generic type?
                    if (fromIndex < 0) {
                        fromIndex = 0;
                    } else {
                        fromIndex = signature.indexOf('>', fromIndex);
                        if (fromIndex < 0) {
                            throw new ClassFormatException("Invalid signature: " + signature);
                        }
                    }
                    int index = signature.indexOf(';', fromIndex); // Look for closing `;'
                    if (index < 0) {
                        throw new ClassFormatException("Invalid signature: " + signature);
                    }

                    // check to see if there are any TypeArguments
                    int bracketIndex = signature.substring(0, index).indexOf('<');
                    if (bracketIndex < 0) {
                        // just a class identifier
                        wrap(consumed_chars, index + 1); // "Lblabla;" `L' and `;' are removed
                        return compactClassName(signature.substring(1, index), chopit);
                    }
                    // but make sure we are not looking past the end of the current item
                    fromIndex = signature.indexOf(';');
                    if (fromIndex < 0) {
                        throw new ClassFormatException("Invalid signature: " + signature);
                    }
                    if (fromIndex < bracketIndex) {
                        // just a class identifier
                        wrap(consumed_chars, fromIndex + 1); // "Lblabla;" `L' and `;' are removed
                        return compactClassName(signature.substring(1, fromIndex), chopit);
                    }

                    // we have TypeArguments; build up partial result
                    // as we recurse for each TypeArgument
                    StringBuilder type = new StringBuilder(compactClassName(signature.substring(1, bracketIndex), chopit)).append("<");
                    int consumed_chars = bracketIndex + 1; // Shadows global var

                    // check for wildcards
                    if (signature.charAt(consumed_chars) == '+') {
                        type.append("? extends ");
                        consumed_chars++;
                    } else if (signature.charAt(consumed_chars) == '-') {
                        type.append("? super ");
                        consumed_chars++;
                    }

                    // get the first TypeArgument
                    if (signature.charAt(consumed_chars) == '*') {
                        type.append("?");
                        consumed_chars++;
                    } else {
                        type.append(typeSignatureToString(signature.substring(consumed_chars), chopit));
                        // update our consumed count by the number of characters the for type argument
                        consumed_chars = unwrap(Utility.consumed_chars) + consumed_chars;
                        wrap(Utility.consumed_chars, consumed_chars);
                    }

                    // are there more TypeArguments?
                    while (signature.charAt(consumed_chars) != '>') {
                        type.append(", ");
                        // check for wildcards
                        if (signature.charAt(consumed_chars) == '+') {
                            type.append("? extends ");
                            consumed_chars++;
                        } else if (signature.charAt(consumed_chars) == '-') {
                            type.append("? super ");
                            consumed_chars++;
                        }
                        if (signature.charAt(consumed_chars) == '*') {
                            type.append("?");
                            consumed_chars++;
                        } else {
                            type.append(typeSignatureToString(signature.substring(consumed_chars), chopit));
                            // update our consumed count by the number of characters the for type argument
                            consumed_chars = unwrap(Utility.consumed_chars) + consumed_chars;
                            wrap(Utility.consumed_chars, consumed_chars);
                        }
                    }

                    // process the closing ">"
                    consumed_chars++;
                    type.append(">");

                    if (signature.charAt(consumed_chars) == '.') {
                        // we have a ClassTypeSignatureSuffix
                        type.append(".");
                        // convert SimpleClassTypeSignature to fake ClassTypeSignature
                        // and then recurse to parse it
                        type.append(typeSignatureToString("L" + signature.substring(consumed_chars + 1), chopit));
                        // update our consumed count by the number of characters the for type argument
                        // note that this count includes the "L" we added, but that is ok
                        // as it accounts for the "." we didn't consume
                        consumed_chars = unwrap(Utility.consumed_chars) + consumed_chars;
                        wrap(Utility.consumed_chars, consumed_chars);
                        return type.toString();
                    }
                    if (signature.charAt(consumed_chars) != ';') {
                        throw new ClassFormatException("Invalid signature: " + signature);
                    }
                    wrap(Utility.consumed_chars, consumed_chars + 1); // remove ";"
                    return type.toString();
                }
                case 'S':
                    return "short";
                case 'Z':
                    return "boolean";
                case '[': { // Array declaration
                    int n;
                    StringBuilder brackets;
                    String type;
                    int consumed_chars; // Shadows global var
                    brackets = new StringBuilder(); // Accumulate []'s
                    // Count opening brackets and look for optional size argument
                    for (n = 0; signature.charAt(n) == '['; n++) {
                        brackets.append("[]");
                    }
                    consumed_chars = n; // Remember value
                    // The rest of the string denotes a `<field_type>'
                    type = typeSignatureToString(signature.substring(n), chopit);
                    // corrected concurrent private static field acess
                    // Utility.consumed_chars += consumed_chars; is replaced by:
                    int _temp = unwrap(Utility.consumed_chars) + consumed_chars;
                    wrap(Utility.consumed_chars, _temp);
                    return type + brackets.toString();
                }
                case 'V':
                    return "void";
                default:
                    throw new ClassFormatException("Invalid signature: `" + signature + "'");
            }
        } catch (StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid signature: " + signature, e);
        }
    }

    private static int unwrap(ThreadLocal<Integer> tl) {
        return tl.get().intValue();
    }

    private static void wrap(ThreadLocal<Integer> tl, int value) {
        tl.set(Integer.valueOf(value));
    }
}
