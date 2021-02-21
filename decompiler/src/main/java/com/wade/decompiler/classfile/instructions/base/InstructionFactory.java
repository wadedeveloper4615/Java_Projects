package com.wade.decompiler.classfile.instructions.base;

public class InstructionFactory implements InstructionConstants {
//    // N.N. These must agree with the order of Constants.T_CHAR through T_LONG
//    private static String[] short_names = { "C", "F", "D", "B", "S", "I", "L" };
//
//    private static MethodObject[] append_mos = { new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.STRING }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.OBJECT }), null, null, // indices 2, 3
//            new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.BOOLEAN }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.CHAR }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.FLOAT }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.DOUBLE }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }),
//            new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, // No append(byte)
//                    new Type[] { Type.INT }),
//            new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, // No append(short)
//                    new Type[] { Type.INT }),
//            new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.LONG }) };
//
//    private static class MethodObject {
//        Type[] arg_types;
//        Type result_type;
//        String class_name;
//        String name;
//
//        MethodObject(String c, String n, Type r, Type[] a) {
//            class_name = c;
//            name = n;
//            result_type = r;
//            arg_types = a;
//        }
//    }
//
//    public InstructionFactory(ClassGen cg) {
//        this(cg, cg.getConstantPool());
//    }
//
//    public InstructionFactory(ClassGen cg, ConstantPoolGen cp) {
//        this.cg = cg;
//        this.cp = cp;
//    }
//
//    public InstructionFactory(ConstantPoolGen cp) {
//        this(null, cp);
//    }
//
//    public Instruction createAppend(Type type) {
//        byte t = type.getType();
//        if (isString(type)) {
//            return createInvoke(append_mos[0], (short) InstructionOpCodes.INVOKEVIRTUAL.getOpcode());
//        }
//        switch (t) {
//            case Const.T_BOOLEAN:
//            case Const.T_CHAR:
//            case Const.T_FLOAT:
//            case Const.T_DOUBLE:
//            case Const.T_BYTE:
//            case Const.T_SHORT:
//            case Const.T_INT:
//            case Const.T_LONG:
//                return createInvoke(append_mos[t], (short) InstructionOpCodes.INVOKEVIRTUAL.getOpcode());
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return createInvoke(append_mos[1], (short) InstructionOpCodes.INVOKEVIRTUAL.getOpcode());
//            default:
//                throw new IllegalArgumentException("No append for this type? " + type);
//        }
//    }
//
//    @SuppressWarnings("deprecation")
//    public Instruction createCast(Type src_type, Type dest_type) {
//        if ((src_type instanceof BasicType) && (dest_type instanceof BasicType)) {
//            byte dest = dest_type.getType();
//            byte src = src_type.getType();
//            if (dest == Const.T_LONG && (src == Const.T_CHAR || src == Const.T_BYTE || src == Const.T_SHORT)) {
//                src = Const.T_INT;
//            }
//            // String name = "org.apache.bcel.generic." + short_names[src -
//            // Const.T_CHAR] + "2" + short_names[dest - Const.T_CHAR];
//            String name = "com.wade.decompiler.generic." + short_names[src - Const.T_CHAR] + "2" + short_names[dest - Const.T_CHAR];
//            Instruction i = null;
//            try {
//                i = (Instruction) java.lang.Class.forName(name).newInstance();
//            } catch (Exception e) {
//                throw new IllegalArgumentException("Could not find instruction: " + name, e);
//            }
//            return i;
//        } else if ((src_type instanceof ReferenceType) && (dest_type instanceof ReferenceType)) {
//            if (dest_type instanceof ArrayType) {
//                return new CHECKCAST(cp.addArrayClass((ArrayType) dest_type));
//            }
//            return new CHECKCAST(cp.addClass(((ObjectType) dest_type).getClassName()));
//        } else {
//            throw new IllegalArgumentException("Cannot cast " + src_type + " to " + dest_type);
//        }
//    }
//
//    public CHECKCAST createCheckCast(ReferenceType t) {
//        if (t instanceof ArrayType) {
//            return new CHECKCAST(cp.addArrayClass((ArrayType) t));
//        }
//        return new CHECKCAST(cp.addClass((ObjectType) t));
//    }
//
//    public Instruction createConstant(Object value) {
//        PUSH push;
//        if (value instanceof Number) {
//            push = new PUSH(cp, (Number) value);
//        } else if (value instanceof String) {
//            push = new PUSH(cp, (String) value);
//        } else if (value instanceof Boolean) {
//            push = new PUSH(cp, (Boolean) value);
//        } else if (value instanceof Character) {
//            push = new PUSH(cp, (Character) value);
//        } else {
//            throw new ClassGenException("Illegal type: " + value.getClass());
//        }
//        return push.getInstruction();
//    }
//
//    public FieldInstruction createFieldAccess(String class_name, String name, Type type, short kind) {
//        String signature = type.getSignature();
//        int index = cp.addFieldref(class_name, name, signature);
//        switch (InstructionOpCodes.read(kind)) {
//            case GETFIELD:
//                return new GETFIELD(index);
//            case PUTFIELD:
//                return new PUTFIELD(index);
//            case GETSTATIC:
//                return new GETSTATIC(index);
//            case PUTSTATIC:
//                return new PUTSTATIC(index);
//            default:
//                throw new IllegalArgumentException("Unknown getfield kind:" + kind);
//        }
//    }
//
//    public GETFIELD createGetField(String class_name, String name, Type t) {
//        return new GETFIELD(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public GETSTATIC createGetStatic(String class_name, String name, Type t) {
//        return new GETSTATIC(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public INSTANCEOF createInstanceOf(ReferenceType t) {
//        if (t instanceof ArrayType) {
//            return new INSTANCEOF(cp.addArrayClass((ArrayType) t));
//        }
//        return new INSTANCEOF(cp.addClass((ObjectType) t));
//    }
//
//    private InvokeInstruction createInvoke(MethodObject m, short kind) {
//        return createInvoke(m.class_name, m.name, m.result_type, m.arg_types, kind);
//    }
//
//    public InvokeInstruction createInvoke(String class_name, String name, Type ret_type, Type[] arg_types, short kind) {
//        return createInvoke(class_name, name, ret_type, arg_types, kind, kind == InstructionOpCodes.INVOKEINTERFACE.getOpcode());
//    }
//
//    public InvokeInstruction createInvoke(String class_name, String name, Type ret_type, Type[] arg_types, short kind2, boolean use_interface) {
//        InstructionOpCodes kind = InstructionOpCodes.read(kind2);
//        if (kind != InstructionOpCodes.INVOKESPECIAL && kind != InstructionOpCodes.INVOKEVIRTUAL && kind != InstructionOpCodes.INVOKESTATIC && kind != InstructionOpCodes.INVOKEINTERFACE && kind != InstructionOpCodes.INVOKEDYNAMIC) {
//            throw new IllegalArgumentException("Unknown invoke kind: " + kind);
//        }
//        int index;
//        int nargs = 0;
//        String signature = Type.getMethodSignature(ret_type, arg_types);
//        for (Type arg_type : arg_types) {
//            nargs += arg_type.getSize();
//        }
//        if (use_interface) {
//            index = cp.addInterfaceMethodref(class_name, name, signature);
//        } else {
//            index = cp.addMethodref(class_name, name, signature);
//        }
//        switch (kind) {
//            case INVOKESPECIAL:
//                return new INVOKESPECIAL(index);
//            case INVOKEVIRTUAL:
//                return new INVOKEVIRTUAL(index);
//            case INVOKESTATIC:
//                return new INVOKESTATIC(index);
//            case INVOKEINTERFACE:
//                return new INVOKEINTERFACE(index, nargs + 1);
//            case INVOKEDYNAMIC:
//                return new INVOKEDYNAMIC(index);
//            default:
//                // Can't happen
//                throw new IllegalStateException("Unknown invoke kind: " + kind);
//        }
//    }
//
//    public NEW createNew(ObjectType t) {
//        return new NEW(cp.addClass(t));
//    }
//
//    public NEW createNew(String s) {
//        return createNew(ObjectType.getInstance(s));
//    }
//
//    public Instruction createNewArray(Type t, short dim) {
//        if (dim == 1) {
//            if (t instanceof ObjectType) {
//                return new ANEWARRAY(cp.addClass((ObjectType) t));
//            } else if (t instanceof ArrayType) {
//                return new ANEWARRAY(cp.addArrayClass((ArrayType) t));
//            } else {
//                return new NEWARRAY(t.getType());
//            }
//        }
//        ArrayType at;
//        if (t instanceof ArrayType) {
//            at = (ArrayType) t;
//        } else {
//            at = new ArrayType(t, dim);
//        }
//        return new MULTIANEWARRAY(cp.addArrayClass(at), dim);
//    }
//
//    public InstructionList createPrintln(String s) {
//        InstructionList il = new InstructionList();
//        int out = cp.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;");
//        int println = cp.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V");
//        il.append(new GETSTATIC(out));
//        il.append(new PUSH(cp, s));
//        il.append(new INVOKEVIRTUAL(println));
//        return il;
//    }
//
//    public PUTFIELD createPutField(String class_name, String name, Type t) {
//        return new PUTFIELD(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public PUTSTATIC createPutStatic(String class_name, String name, Type t) {
//        return new PUTSTATIC(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public ClassGen getClassGen() {
//        return cg;
//    }
//
//    public ConstantPoolGen getConstantPool() {
//        return cp;
//    }
//
//    public void setClassGen(ClassGen c) {
//        cg = c;
//    }
//
//    public void setConstantPool(ConstantPoolGen c) {
//        cp = c;
//    }
//
//    public static ArrayInstruction createArrayLoad(Type type) {
//        switch (type.getType()) {
//            case Const.T_BOOLEAN:
//            case Const.T_BYTE:
//                return new BALOAD();
//            case Const.T_CHAR:
//                return new CALOAD();
//            case Const.T_SHORT:
//                return new SALOAD();
//            case Const.T_INT:
//                return new IALOAD();
//            case Const.T_FLOAT:
//                return new FALOAD();
//            case Const.T_DOUBLE:
//                return new DALOAD();
//            case Const.T_LONG:
//                return new LALOAD();
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return new AALOAD();
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    public static ArrayInstruction createArrayStore(Type type) {
//        switch (type.getType()) {
//            case Const.T_BOOLEAN:
//            case Const.T_BYTE:
//                return new BASTORE();
//            case Const.T_CHAR:
//                return new CASTORE();
//            case Const.T_SHORT:
//                return new SASTORE();
//            case Const.T_INT:
//                return new IASTORE();
//            case Const.T_FLOAT:
//                return new FASTORE();
//            case Const.T_DOUBLE:
//                return new DASTORE();
//            case Const.T_LONG:
//                return new LASTORE();
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return new AASTORE();
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    private static ArithmeticInstruction createBinaryDoubleOp(char op) {
//        switch (op) {
//            case '-':
//                return InstructionConst.DSUB;
//            case '+':
//                return InstructionConst.DADD;
//            case '*':
//                return InstructionConst.DMUL;
//            case '/':
//                return InstructionConst.DDIV;
//            case '%':
//                return InstructionConst.DREM;
//            default:
//                throw new IllegalArgumentException("Invalid operand " + op);
//        }
//    }
//
//    private static ArithmeticInstruction createBinaryFloatOp(char op) {
//        switch (op) {
//            case '-':
//                return InstructionConst.FSUB;
//            case '+':
//                return InstructionConst.FADD;
//            case '*':
//                return InstructionConst.FMUL;
//            case '/':
//                return InstructionConst.FDIV;
//            case '%':
//                return InstructionConst.FREM;
//            default:
//                throw new IllegalArgumentException("Invalid operand " + op);
//        }
//    }
//
//    private static ArithmeticInstruction createBinaryIntOp(char first, String op) {
//        switch (first) {
//            case '-':
//                return InstructionConst.ISUB;
//            case '+':
//                return InstructionConst.IADD;
//            case '%':
//                return InstructionConst.IREM;
//            case '*':
//                return InstructionConst.IMUL;
//            case '/':
//                return InstructionConst.IDIV;
//            case '&':
//                return InstructionConst.IAND;
//            case '|':
//                return InstructionConst.IOR;
//            case '^':
//                return InstructionConst.IXOR;
//            case '<':
//                return InstructionConst.ISHL;
//            case '>':
//                return op.equals(">>>") ? InstructionConst.IUSHR : InstructionConst.ISHR;
//            default:
//                throw new IllegalArgumentException("Invalid operand " + op);
//        }
//    }
//
//    private static ArithmeticInstruction createBinaryLongOp(char first, String op) {
//        switch (first) {
//            case '-':
//                return InstructionConst.LSUB;
//            case '+':
//                return InstructionConst.LADD;
//            case '%':
//                return InstructionConst.LREM;
//            case '*':
//                return InstructionConst.LMUL;
//            case '/':
//                return InstructionConst.LDIV;
//            case '&':
//                return InstructionConst.LAND;
//            case '|':
//                return InstructionConst.LOR;
//            case '^':
//                return InstructionConst.LXOR;
//            case '<':
//                return InstructionConst.LSHL;
//            case '>':
//                return op.equals(">>>") ? InstructionConst.LUSHR : InstructionConst.LSHR;
//            default:
//                throw new IllegalArgumentException("Invalid operand " + op);
//        }
//    }
//
//    public static ArithmeticInstruction createBinaryOperation(String op, Type type) {
//        char first = op.charAt(0);
//        switch (type.getType()) {
//            case Const.T_BYTE:
//            case Const.T_SHORT:
//            case Const.T_INT:
//            case Const.T_CHAR:
//                return createBinaryIntOp(first, op);
//            case Const.T_LONG:
//                return createBinaryLongOp(first, op);
//            case Const.T_FLOAT:
//                return createBinaryFloatOp(first);
//            case Const.T_DOUBLE:
//                return createBinaryDoubleOp(first);
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    public static BranchInstruction createBranchInstruction(short op, InstructionHandle target) {
//        InstructionOpCodes opcode = InstructionOpCodes.read(op);
//        switch (opcode) {
//            case IFEQ:
//                return new IFEQ(target);
//            case IFNE:
//                return new IFNE(target);
//            case IFLT:
//                return new IFLT(target);
//            case IFGE:
//                return new IFGE(target);
//            case IFGT:
//                return new IFGT(target);
//            case IFLE:
//                return new IFLE(target);
//            case IF_ICMPEQ:
//                return new IF_ICMPEQ(target);
//            case IF_ICMPNE:
//                return new IF_ICMPNE(target);
//            case IF_ICMPLT:
//                return new IF_ICMPLT(target);
//            case IF_ICMPGE:
//                return new IF_ICMPGE(target);
//            case IF_ICMPGT:
//                return new IF_ICMPGT(target);
//            case IF_ICMPLE:
//                return new IF_ICMPLE(target);
//            case IF_ACMPEQ:
//                return new IF_ACMPEQ(target);
//            case IF_ACMPNE:
//                return new IF_ACMPNE(target);
//            case GOTO:
//                return new GOTO(target);
//            case JSR:
//                return new JSR(target);
//            case IFNULL:
//                return new IFNULL(target);
//            case IFNONNULL:
//                return new IFNONNULL(target);
//            case GOTO_W:
//                return new GOTO_W(target);
//            case JSR_W:
//                return new JSR_W(target);
//            default:
//                throw new IllegalArgumentException("Invalid opcode: " + opcode);
//        }
//    }
//
//    public static StackInstruction createDup(int size) {
//        return (size == 2) ? InstructionConst.DUP2 : InstructionConst.DUP;
//    }
//
//    public static StackInstruction createDup_1(int size) {
//        return (size == 2) ? InstructionConst.DUP2_X1 : InstructionConst.DUP_X1;
//    }
//
//    public static StackInstruction createDup_2(int size) {
//        return (size == 2) ? InstructionConst.DUP2_X2 : InstructionConst.DUP_X2;
//    }
//
//    public static LocalVariableInstruction createLoad(Type type, int index) {
//        switch (type.getType()) {
//            case Const.T_BOOLEAN:
//            case Const.T_CHAR:
//            case Const.T_BYTE:
//            case Const.T_SHORT:
//            case Const.T_INT:
//                return new ILOAD(index);
//            case Const.T_FLOAT:
//                return new FLOAD(index);
//            case Const.T_DOUBLE:
//                return new DLOAD(index);
//            case Const.T_LONG:
//                return new LLOAD(index);
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return new ALOAD(index);
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    public static Instruction createNull(Type type) {
//        switch (type.getType()) {
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return new ACONST_NULL();
//            case Const.T_INT:
//            case Const.T_SHORT:
//            case Const.T_BOOLEAN:
//            case Const.T_CHAR:
//            case Const.T_BYTE:
//                return new ICONST(0);
//            case Const.T_FLOAT:
//                return new FCONST(0);
//            case Const.T_DOUBLE:
//                return new DCONST(0);
//            case Const.T_LONG:
//                return new LCONST(0);
//            case Const.T_VOID:
//                return new NOP();
//            default:
//                throw new IllegalArgumentException("Invalid type: " + type);
//        }
//    }
//
//    public static StackInstruction createPop(int size) {
//        return (size == 2) ? InstructionConst.POP2 : InstructionConst.POP;
//    }
//
//    public static ReturnInstruction createReturn(Type type) {
//        switch (type.getType()) {
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return InstructionConst.ARETURN;
//            case Const.T_INT:
//            case Const.T_SHORT:
//            case Const.T_BOOLEAN:
//            case Const.T_CHAR:
//            case Const.T_BYTE:
//                return InstructionConst.IRETURN;
//            case Const.T_FLOAT:
//                return InstructionConst.FRETURN;
//            case Const.T_DOUBLE:
//                return InstructionConst.DRETURN;
//            case Const.T_LONG:
//                return InstructionConst.LRETURN;
//            case Const.T_VOID:
//                return InstructionConst.RETURN;
//            default:
//                throw new IllegalArgumentException("Invalid type: " + type);
//        }
//    }
//
//    public static LocalVariableInstruction createStore(Type type, int index) {
//        switch (type.getType()) {
//            case Const.T_BOOLEAN:
//            case Const.T_CHAR:
//            case Const.T_BYTE:
//            case Const.T_SHORT:
//            case Const.T_INT:
//                return new ISTORE(index);
//            case Const.T_FLOAT:
//                return new FSTORE(index);
//            case Const.T_DOUBLE:
//                return new DSTORE(index);
//            case Const.T_LONG:
//                return new LSTORE(index);
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return new ASTORE(index);
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    public static Instruction createThis() {
//        return new ALOAD(0);
//    }
//
//    private static boolean isString(Type type) {
//        return (type instanceof ObjectType) && ((ObjectType) type).getClassName().equals("java.lang.String");
//    }
}
