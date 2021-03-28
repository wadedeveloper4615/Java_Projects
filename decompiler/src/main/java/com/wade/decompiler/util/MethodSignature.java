package com.wade.decompiler.util;

import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.generate.attribute.LocalVariableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class MethodSignature {
    private final boolean constructor;
    private final String className;
    private final String name;
    private final boolean chopit;
    private final String access;
    private TypeData[] parameterTypes;
    private TypeData returnType;

    public MethodSignature(String signature, String name, String access, String className, boolean chopit, LocalVariableTableGen localVariableTable, boolean constructor) {
        this.constructor = constructor;
        this.className = className;
        this.name = name;
        this.chopit = chopit;
        this.access = access;
        // System.out.println(" original signature = " + signature);
        int start = signature.indexOf('(') + 1;
        if (start <= 0) {
            throw new ClassFormatException("Invalid method signature: " + signature);
        }
        int end = signature.indexOf(')');
        extractReturnType(signature, end);

        extractParameters(signature, access, constructor, start, end, localVariableTable);
        // System.out.println(" parameterTypes = " + Arrays.toString(parameterTypes));
        // System.out.println(" returnType = " + returnType);
    }

    private TypeEnum convertToType(String signature) {
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
                return convertToType(signature.substring(1));
            default:
                throw new ClassFormatException("Invalid method signature: " + signature);
        }
    }

    public void extractParameters(String signature, String access, boolean constructor, int start, int end, LocalVariableTableGen vars) {
        int var_index = access.contains("static") ? 0 : 1;
        String parameterString = signature.substring(start, end);
        List<TypeData> pt = new ArrayList<>();
        for (int i = 0; i < parameterString.length(); i++) {
            TypeEnum baseType = convertToType(parameterString.substring(i));
            TypeEnum indexType = TypeEnum.T_UNKNOWN;
            String ref = "";
            if (baseType == TypeEnum.T_ARRAY) {
                i++;
                indexType = convertToType(parameterString.substring(i));
            }
            if (baseType == TypeEnum.T_REFERENCE || indexType == TypeEnum.T_REFERENCE) {
                while (parameterString.charAt(i) != ';') {
                    ref += parameterString.charAt(i);
                    i++;
                }
                ref += parameterString.charAt(i);
                indexType = TypeEnum.T_UNKNOWN;
            }
            String name = "";
            if (vars != null) {
                LocalVariableGen l = vars.getLocalVariable(var_index, 0);
                if (l != null) {
                    name = l.getName();
                }
            } else {
                name = " arg" + var_index;
            }
            if (baseType == TypeEnum.T_DOUBLE || baseType == TypeEnum.T_LONG) {
                var_index += 2;
            } else {
                var_index++;
            }
            pt.add(new TypeData(baseType, indexType, ref, name));
        }
        parameterTypes = pt.toArray(new TypeData[pt.size()]);
        if (constructor) {
            returnType = new TypeData(TypeEnum.T_NONE, TypeEnum.T_NONE);
        }
    }

    public void extractReturnType(String signature, int end) {
        String returnString = signature.substring(end + 1);
        TypeEnum type = convertToType(returnString);
        String ref = " ";
        if (type == TypeEnum.T_REFERENCE) {
            int i = 1;
            while (returnString.charAt(i) != ';') {
                ref += returnString.charAt(i);
                i++;
            }
            ref += returnString.charAt(i);
        }
        returnType = new TypeData(type, TypeEnum.T_UNKNOWN, ref, null);
    }

    private String getType(TypeEnum type, TypeData td) {
        String reference;
        switch (type) {
            case T_BYTE:
                return "byte";
            case T_CHAR:
                return "char";
            case T_DOUBLE:
                return "double";
            case T_FLOAT:
                return "float";
            case T_INTEGER:
                return "int";
            case T_LONG:
                return "long";
            case T_REFERENCE:
                reference = td.getReference();
                if (reference != null) {
                    int index = reference.indexOf(';');
                    return Utility.compactClassName(reference.substring(1, index), chopit);
                }
                return "???";
            case T_ARRAY:
                if (td.getIndexType() == TypeEnum.T_UNKNOWN) {
                    reference = td.getReference();
                    if (reference != null) {
                        int index = reference.indexOf(';');
                        return Utility.compactClassName(reference.substring(1, index), chopit) + "[]";
                    }
                    return "???";
                }
                return getType(td.getIndexType(), td) + "[]";
            case T_VOID:
                return "void";
            case T_BOOLEAN:
                return "bool";
            case T_SHORT:
                return "short";
            default:
                return "???";
        }
    }

    private String parameterToString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append('(');
        for (TypeData td : this.parameterTypes) {
            String type = getType(td.getBaseType(), td);
            String name = td.getName();
            buffer.append(type + " " + name + ",");
        }
        String string;
        String str = buffer.toString();
        if (str.length() > 1) {
            string = str.substring(0, str.length() - 1);
        } else {
            string = str;
        }
        string += ')';
        return string;
    }

    public String signaturetoString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.access);
        buffer.append(" ");
        if (constructor) {
            buffer.append(Utility.extractClassName(this.className));
        } else {
            buffer.append(getType(returnType.baseType, returnType));
            buffer.append(" ");
            buffer.append(this.name);
        }
        buffer.append(parameterToString());
        // System.out.println(buffer.toString());
        return buffer.toString();
    }
}
