package com.wade.decompiler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.wade.decompiler.enums.TypeEnum;

class MethodSignatureTest {
    @Test
    void testMethodSignatureArrayPrimitives() {
        MethodSignature ms = new MethodSignature("([B[C[D[F[I[J[V[Z[S)I", "name", "public", "cname", false, null, false);
        assertNotNull(ms.getReturnType());
        assertNotNull(ms.getParameterTypes());
        assertEquals(TypeEnum.T_INTEGER, ms.getReturnType().getBaseType());
        assertEquals(9, ms.getParameterTypes().length);
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[0].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[1].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[2].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[3].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[4].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[5].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[6].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[7].getBaseType());
        assertEquals(TypeEnum.T_ARRAY, ms.getParameterTypes()[8].getBaseType());

        assertEquals(TypeEnum.T_BYTE, ms.getParameterTypes()[0].getIndexType());
        assertEquals(TypeEnum.T_CHAR, ms.getParameterTypes()[1].getIndexType());
        assertEquals(TypeEnum.T_DOUBLE, ms.getParameterTypes()[2].getIndexType());
        assertEquals(TypeEnum.T_FLOAT, ms.getParameterTypes()[3].getIndexType());
        assertEquals(TypeEnum.T_INTEGER, ms.getParameterTypes()[4].getIndexType());
        assertEquals(TypeEnum.T_LONG, ms.getParameterTypes()[5].getIndexType());
        assertEquals(TypeEnum.T_VOID, ms.getParameterTypes()[6].getIndexType());
        assertEquals(TypeEnum.T_BOOLEAN, ms.getParameterTypes()[7].getIndexType());
        assertEquals(TypeEnum.T_SHORT, ms.getParameterTypes()[8].getIndexType());
        assertNotNull(ms.toString());
        assertNotNull(ms.hashCode());
    }

    @Test
    void testMethodSignaturePrimitives() {
        MethodSignature ms = new MethodSignature("(BCDFIJVZS)I", "name", "public", "cname", false, null, false);
        assertNotNull(ms.getReturnType());
        assertNotNull(ms.getParameterTypes());
        assertEquals(TypeEnum.T_INTEGER, ms.getReturnType().getBaseType());
        assertEquals(9, ms.getParameterTypes().length);
        assertEquals(TypeEnum.T_BYTE, ms.getParameterTypes()[0].getBaseType());
        assertEquals(TypeEnum.T_CHAR, ms.getParameterTypes()[1].getBaseType());
        assertEquals(TypeEnum.T_DOUBLE, ms.getParameterTypes()[2].getBaseType());
        assertEquals(TypeEnum.T_FLOAT, ms.getParameterTypes()[3].getBaseType());
        assertEquals(TypeEnum.T_INTEGER, ms.getParameterTypes()[4].getBaseType());
        assertEquals(TypeEnum.T_LONG, ms.getParameterTypes()[5].getBaseType());
        assertEquals(TypeEnum.T_VOID, ms.getParameterTypes()[6].getBaseType());
        assertEquals(TypeEnum.T_BOOLEAN, ms.getParameterTypes()[7].getBaseType());
        assertEquals(TypeEnum.T_SHORT, ms.getParameterTypes()[8].getBaseType());
        assertNotNull(ms.toString());
        assertNotNull(ms.hashCode());
    }

    @Test
    void testMethodSignatureReferences() {
        MethodSignature ms = new MethodSignature("(ILjava/lang/Object;J)V", "name", "public", "cname", false, null, false);
        assertNotNull(ms.getReturnType());
        assertNotNull(ms.getParameterTypes());
        assertEquals(TypeEnum.T_VOID, ms.getReturnType().getBaseType());
        assertEquals(3, ms.getParameterTypes().length);
        assertEquals(TypeEnum.T_INTEGER, ms.getParameterTypes()[0].getBaseType());
        assertEquals(TypeEnum.T_REFERENCE, ms.getParameterTypes()[1].getBaseType());
        assertEquals(TypeEnum.T_LONG, ms.getParameterTypes()[2].getBaseType());
        assertNotNull(ms.toString());
        assertNotNull(ms.hashCode());
    }
}
