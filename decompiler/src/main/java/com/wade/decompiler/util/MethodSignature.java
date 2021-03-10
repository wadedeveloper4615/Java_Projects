package com.wade.decompiler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@SuppressWarnings("unused")
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class MethodSignature {
    private TypeData[] parameterTypes;
    private TypeData returnType;

    public MethodSignature(String signature, String name, String access, boolean b, LocalVariableTableGen localVariableTable) {
        int start = signature.indexOf('(') + 1;
        if (start <= 0) {
            throw new ClassFormatException("Invalid method signature: " + signature);
        }
        int end = signature.indexOf(')');
        String parameterString = signature.substring(start, end);
        String returnString = signature.substring(end + 1);
        returnType = new TypeData(convertToType(returnString), TypeEnum.T_UNKNOWN);
        ;

        List<TypeData> pt = new ArrayList<>();
        for (int i = 0; i < parameterString.length(); i++) {
            TypeEnum baseType = convertToType(parameterString.substring(i));
            TypeEnum indexType = TypeEnum.T_UNKNOWN;
            String ref = "";
            if (baseType == TypeEnum.T_ARRAY) {
                i++;
                indexType = convertToType(parameterString.substring(i));
            }
            if (baseType == TypeEnum.T_REFERENCE) {
                while (parameterString.charAt(i) != ';') {
                    ref += parameterString.charAt(i);
                    i++;
                }
                ref += parameterString.charAt(i);
                indexType = TypeEnum.T_UNKNOWN;
            }
            pt.add(new TypeData(baseType, indexType, ref));
        }
        parameterTypes = pt.toArray(new TypeData[pt.size()]);
        System.out.println(" parameterTypes = " + Arrays.toString(parameterTypes));
        System.out.println(" returnType = " + returnType);
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

    public String signaturetoString() {
        return null;
    }
}
