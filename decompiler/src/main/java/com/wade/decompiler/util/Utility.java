package com.wade.decompiler.util;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.generate.attribute.LocalVariableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import com.wade.decompiler.repository.SyntheticRepository;

public abstract class Utility {
    private static final ConsumedChars consumed_chars = new ConsumedChars();

    public static String accessToString(ClassAccessFlagsList access_flags) {
        return accessToString(access_flags, false);
    }

    public static String accessToString(ClassAccessFlagsList access_flags, boolean for_class) {
        StringBuilder buf = new StringBuilder();
        int p = 0;
        for (int i = 0; p < Const.MAX_ACC_FLAG_I; i++) { // Loop through known flags
            p = pow2(i);
            if ((access_flags.getFlags() & p) != 0) {

                if (for_class && ((p == ClassAccessFlags.ACC_SUPER.getFlag()) || (p == ClassAccessFlags.ACC_INTERFACE.getFlag()))) {
                    continue;
                }
                buf.append(ClassAccessFlags.read(i).getName()).append(" ");
            }
        }
        return buf.toString().trim();
    }

    public static String classType(ClassAccessFlagsList flags) {
        boolean isAnnotation = flags.isInterface() && flags.isAnnotation() && flags.isAbstract();
        boolean isInterface = flags.isInterface() && !flags.isAnnotation() && !flags.isAbstract();
        if (isAnnotation) {
            flags.remove(ClassAccessFlags.ACC_ABSTRACT);
            flags.remove(ClassAccessFlags.ACC_ABSTRACT);
            flags.remove(ClassAccessFlags.ACC_ANNOTATION);
            return "@interface";
        } else if (isInterface) return "interface";
        else return "class";
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

    public static Object extractClassName(String path) {
        int index = path.lastIndexOf('.');
        return path.substring(index + 1);
    }

    public static String extractClassName(String className, boolean b) {
        String name = Utility.compactClassName(className, b);
        int index = name.lastIndexOf(".") + 1;
        return name.substring(index);
    }

    public static JavaClass getTestClass(String name) throws ClassNotFoundException {
        return SyntheticRepository.getInstance().loadClass(name);
    }

    public static String[] methodSignatureArgumentTypes(String signature) throws ClassFormatException {
        return methodSignatureArgumentTypes(signature, true);
    }

    public static String[] methodSignatureArgumentTypes(String signature, boolean chopit) throws ClassFormatException {
        List<String> vec = new ArrayList<>();
        int index;
        try {
            // Skip any type arguments to read argument declarations between `(' and `)'
            index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                vec.add(typeSignatureToString(signature.substring(index), chopit));
                // corrected concurrent private static field acess
                index += unwrap(consumed_chars); // update position
            }
        } catch (StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        return vec.toArray(new String[vec.size()]);
    }

    public static String methodSignatureToString(String signature, String name, String access) {
        return methodSignatureToString(signature, name, access, true, null);
    }

    public static String methodSignatureToString(String signature, String name, String access, boolean chopit) {
        return methodSignatureToString(signature, name, access, chopit, null);
    }

    public static String methodSignatureToString(String signature, String name, String access, boolean chopit, LocalVariableTableGen vars) throws ClassFormatException {
        StringBuilder buf = new StringBuilder("(");
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
                String param_type = typeSignatureToString(signature.substring(index), chopit);
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
                // corrected concurrent private static field acess
                index += unwrap(consumed_chars); // update position
            }
            index++; // update position
            // Read return type after `)'
            type = typeSignatureToString(signature.substring(index), chopit);
        } catch (StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        // ignore any throws information in the signature
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 2);
        }
        buf.append(")");
        return access + ((access.length() > 0) ? " " : "") + type + " " + name + buf.toString();
    }

    private static int pow2(int n) {
        return 1 << n;
    }

    public static String replace(String str, String old, String new_) {
        int index;
        int old_index;
        try {
            if (str.contains(old)) { // `old' found in str
                StringBuilder buf = new StringBuilder();
                old_index = 0; // String start offset
                // While we have something to replace
                while ((index = str.indexOf(old, old_index)) != -1) {
                    buf.append(str, old_index, index); // append prefix
                    buf.append(new_); // append replacement
                    old_index = index + old.length(); // Skip `old'.length chars
                }
                buf.append(str.substring(old_index)); // append rest of string
                str = buf.toString();
            }
        } catch (StringIndexOutOfBoundsException e) { // Should not occur
            System.err.println(e);
        }
        return str;
    }

    public static String toString(int[] a) {
        if (a == null) return "null";
        int iMax = a.length - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) return b.append(']').toString();
            b.append(", ");
        }
    }

    public static String toString(Object[] a) {
        if (a == null) return "null";

        int iMax = a.length - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) return b.append(']').toString();
            b.append(", ");
        }
    }

    public static TypeEnum typeOfSignature(String signature) throws ClassFormatException {

        try {
            switch (signature.charAt(0)) {
                case 'B':
                    return TypeEnum.T_BYTE;
                case 'C':
                    return TypeEnum.T_CHAR;
                case 'D':
                    return TypeEnum.T_DOUBLE;
                case 'F':
                    return TypeEnum.T_FLOAT;
                case 'I':
                    return TypeEnum.T_INTEGER;
                case 'J':
                    return TypeEnum.T_LONG;
                case 'L':
                case 'T':
                    return TypeEnum.T_REFERENCE;
                case '[':
                    return TypeEnum.T_ARRAY;
                case 'V':
                    return TypeEnum.T_VOID;
                case 'Z':
                    return TypeEnum.T_BOOLEAN;
                case 'S':
                    return TypeEnum.T_SHORT;
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
        // corrected concurrent private static field acess
        wrap(consumed_chars, 1); // This is the default, read just one char like `B'
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
