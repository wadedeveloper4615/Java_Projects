package org.apache.bcel.generic.control;

public class InstructionFactory implements InstructionConstants {
//    private static class MethodObject {
//        final Type[] arg_types;
//        final Type result_type;
//        final String class_name;
//        final String name;
//
//        public MethodObject(final String c, final String n, final Type r, final Type[] a) {
//            class_name = c;
//            name = n;
//            result_type = r;
//            arg_types = a;
//        }
//
//        public Type[] getArg_types() {
//            return arg_types;
//        }
//
//        public String getClass_name() {
//            return class_name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public Type getResult_type() {
//            return result_type;
//        }
//    }
//
//    private static final String[] short_names = { "C", "F", "D", "B", "S", "I", "L" };
//    private static final MethodObject[] append_mos = { new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.STRING }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.OBJECT }), null, null, new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.BOOLEAN }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.CHAR }), new MethodObject("java.lang.StringBuffer",
//            "append", Type.STRINGBUFFER, new Type[] { Type.FLOAT }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.DOUBLE }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }), new MethodObject("java.lang.StringBuffer",
//                    "append", Type.STRINGBUFFER, new Type[] { Type.LONG }) };
//    @Deprecated
//    protected ClassGen cg;
//    @Deprecated
//    protected ConstantPoolGen cp;
//
//    public InstructionFactory(final ClassGen cg) {
//        this(cg, cg.getConstantPool());
//    }
//
//    public InstructionFactory(final ClassGen cg, final ConstantPoolGen cp) {
//        this.cg = cg;
//        this.cp = cp;
//    }
//
//    public InstructionFactory(final ConstantPoolGen cp) {
//        this(null, cp);
//    }
//
//    public Instruction createAppend(final Type type) {
//        final byte t = type.getType();
//        if (isString(type)) {
//            return createInvoke(append_mos[0], InstructionOpCodes.INVOKEVIRTUAL);
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
//                return createInvoke(append_mos[t], InstructionOpCodes.INVOKEVIRTUAL);
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return createInvoke(append_mos[1], InstructionOpCodes.INVOKEVIRTUAL);
//            default:
//                throw new IllegalArgumentException("No append for this type? " + type);
//        }
//    }
//
//    public Instruction createCast(final Type src_type, final Type dest_type) {
//        if ((src_type instanceof BasicType) && (dest_type instanceof BasicType)) {
//            final byte dest = dest_type.getType();
//            byte src = src_type.getType();
//            if (dest == Const.T_LONG && (src == Const.T_CHAR || src == Const.T_BYTE || src == Const.T_SHORT)) {
//                src = Const.T_INT;
//            }
//            final String name = "org.apache.bcel.generic." + short_names[src - Const.T_CHAR] + "2" + short_names[dest - Const.T_CHAR];
//            Instruction i = null;
//            try {
//                i = (Instruction) java.lang.Class.forName(name).newInstance();
//            } catch (final Exception e) {
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
//    public CHECKCAST createCheckCast(final ReferenceType t) {
//        if (t instanceof ArrayType) {
//            return new CHECKCAST(cp.addArrayClass((ArrayType) t));
//        }
//        return new CHECKCAST(cp.addClass((ObjectType) t));
//    }
//
//    public Instruction createConstant(final Object value) {
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
//    public FieldInstruction createFieldAccess(final String class_name, final String name, final Type type, InstructionOpCodes kind) {
//        int index;
//        final String signature = type.getSignature();
//        index = cp.addFieldref(class_name, name, signature);
//        switch (kind) {
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
//    public GETFIELD createGetField(final String class_name, final String name, final Type t) {
//        return new GETFIELD(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public GETSTATIC createGetStatic(final String class_name, final String name, final Type t) {
//        return new GETSTATIC(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public INSTANCEOF createInstanceOf(final ReferenceType t) {
//        if (t instanceof ArrayType) {
//            return new INSTANCEOF(cp.addArrayClass((ArrayType) t));
//        }
//        return new INSTANCEOF(cp.addClass((ObjectType) t));
//    }
////
////    private InvokeInstruction createInvoke(final MethodObject m, InstructionOpCodes kind) {
////        return createInvoke(m.class_name, m.name, m.result_type, m.arg_types, (short) kind.getOpcode());
////    }
////
////    private InvokeInstruction createInvoke(final MethodObject m, final short kind) {
////        return createInvoke(m.class_name, m.name, m.result_type, m.arg_types, kind);
////    }
//
//    public InvokeInstruction createInvoke(final String class_name, final String name, final Type ret_type, final Type[] arg_types, InstructionOpCodes kind, final boolean use_interface) {
//        if (kind != InstructionOpCodes.INVOKESPECIAL && kind != InstructionOpCodes.INVOKEVIRTUAL && kind != InstructionOpCodes.INVOKESTATIC && kind != InstructionOpCodes.INVOKEINTERFACE && kind != InstructionOpCodes.INVOKEDYNAMIC) {
//            throw new IllegalArgumentException("Unknown invoke kind: " + kind);
//        }
//        int index;
//        int nargs = 0;
//        final String signature = Type.getMethodSignature(ret_type, arg_types);
//        for (final Type arg_type : arg_types) {
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
//                throw new IllegalStateException("Unknown invoke kind: " + kind);
//        }
//    }
//
//    public InvokeInstruction createInvoke(final String class_name, final String name, final Type ret_type, final Type[] arg_types, final short kind) {
//        return createInvoke(class_name, name, ret_type, arg_types, kind, kind == InstructionOpCodes.INVOKEINTERFACE.getOpcode());
//    }
//
//    public InvokeInstruction createInvoke(final String class_name, final String name, final Type ret_type, final Type[] arg_types, short kind, final boolean use_interface) {
//        if (kind != InstructionOpCodes.INVOKESPECIAL.getOpcode() && kind != InstructionOpCodes.INVOKEVIRTUAL.getOpcode() && kind != InstructionOpCodes.INVOKESTATIC.getOpcode() && kind != InstructionOpCodes.INVOKEINTERFACE.getOpcode() && kind != InstructionOpCodes.INVOKEDYNAMIC.getOpcode()) {
//            throw new IllegalArgumentException("Unknown invoke kind: " + kind);
//        }
//        int index;
//        int nargs = 0;
//        final String signature = Type.getMethodSignature(ret_type, arg_types);
//        for (final Type arg_type : arg_types) {
//            nargs += arg_type.getSize();
//        }
//        if (use_interface) {
//            index = cp.addInterfaceMethodref(class_name, name, signature);
//        } else {
//            index = cp.addMethodref(class_name, name, signature);
//        }
//        if (kind == (short) InstructionOpCodes.INVOKESPECIAL.getOpcode()) {
//            return new INVOKESPECIAL(index);
//        } else if (kind == (short) InstructionOpCodes.INVOKEVIRTUAL.getOpcode()) {
//            return new INVOKEVIRTUAL(index);
//        } else if (kind == (short) InstructionOpCodes.INVOKESTATIC.getOpcode()) {
//            return new INVOKESTATIC(index);
//        } else if (kind == (short) InstructionOpCodes.INVOKEINTERFACE.getOpcode()) {
//            return new INVOKEINTERFACE(index, nargs + 1);
//        } else if (kind == (short) InstructionOpCodes.INVOKEDYNAMIC.getOpcode()) {
//            return new INVOKEDYNAMIC(index);
//        } else {
//            throw new IllegalStateException("Unknown invoke kind: " + kind);
//        }
//    }
//
//    public NEW createNew(final ObjectType t) {
//        return new NEW(cp.addClass(t));
//    }
//
//    public NEW createNew(final String s) {
//        return createNew(ObjectType.getInstance(s));
//    }
//
//    public Instruction createNewArray(final Type t, final short dim) {
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
//    public InstructionList createPrintln(final String s) {
//        final InstructionList il = new InstructionList();
//        final int out = cp.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;");
//        final int println = cp.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V");
//        il.append(new GETSTATIC(out));
//        il.append(new PUSH(cp, s));
//        il.append(new INVOKEVIRTUAL(println));
//        return il;
//    }
//
//    public PUTFIELD createPutField(final String class_name, final String name, final Type t) {
//        return new PUTFIELD(cp.addFieldref(class_name, name, t.getSignature()));
//    }
//
//    public PUTSTATIC createPutStatic(final String class_name, final String name, final Type t) {
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
//    public void setClassGen(final ClassGen c) {
//        cg = c;
//    }
//
//    public void setConstantPool(final ConstantPoolGen c) {
//        cp = c;
//    }
//
//    public static ArrayInstruction createArrayLoad(final Type type) {
//        switch (type.getType()) {
//            case Const.T_BOOLEAN:
//            case Const.T_BYTE:
//                return InstructionConst.BALOAD;
//            case Const.T_CHAR:
//                return InstructionConst.CALOAD;
//            case Const.T_SHORT:
//                return InstructionConst.SALOAD;
//            case Const.T_INT:
//                return InstructionConst.IALOAD;
//            case Const.T_FLOAT:
//                return InstructionConst.FALOAD;
//            case Const.T_DOUBLE:
//                return InstructionConst.DALOAD;
//            case Const.T_LONG:
//                return InstructionConst.LALOAD;
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return InstructionConst.AALOAD;
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    public static ArrayInstruction createArrayStore(final Type type) {
//        switch (type.getType()) {
//            case Const.T_BOOLEAN:
//            case Const.T_BYTE:
//                return InstructionConst.BASTORE;
//            case Const.T_CHAR:
//                return InstructionConst.CASTORE;
//            case Const.T_SHORT:
//                return InstructionConst.SASTORE;
//            case Const.T_INT:
//                return InstructionConst.IASTORE;
//            case Const.T_FLOAT:
//                return InstructionConst.FASTORE;
//            case Const.T_DOUBLE:
//                return InstructionConst.DASTORE;
//            case Const.T_LONG:
//                return InstructionConst.LASTORE;
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return InstructionConst.AASTORE;
//            default:
//                throw new IllegalArgumentException("Invalid type " + type);
//        }
//    }
//
//    private static ArithmeticInstruction createBinaryDoubleOp(final char op) {
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
//    private static ArithmeticInstruction createBinaryFloatOp(final char op) {
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
//    private static ArithmeticInstruction createBinaryIntOp(final char first, final String op) {
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
//    private static ArithmeticInstruction createBinaryLongOp(final char first, final String op) {
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
//    public static ArithmeticInstruction createBinaryOperation(final String op, final Type type) {
//        final char first = op.charAt(0);
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
//    public static BranchInstruction createBranchInstruction(InstructionOpCodes opcode, final InstructionHandle target) {
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
//    public static StackInstruction createDup(final int size) {
//        return (size == 2) ? InstructionConst.DUP2 : InstructionConst.DUP;
//    }
//
//    public static StackInstruction createDup_1(final int size) {
//        return (size == 2) ? InstructionConst.DUP2_X1 : InstructionConst.DUP_X1;
//    }
//
//    public static StackInstruction createDup_2(final int size) {
//        return (size == 2) ? InstructionConst.DUP2_X2 : InstructionConst.DUP_X2;
//    }
//
//    public static LocalVariableInstruction createLoad(final Type type, final int index) {
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
//    public static Instruction createNull(final Type type) {
//        switch (type.getType()) {
//            case Const.T_ARRAY:
//            case Const.T_OBJECT:
//                return InstructionConst.ACONST_NULL;
//            case Const.T_INT:
//            case Const.T_SHORT:
//            case Const.T_BOOLEAN:
//            case Const.T_CHAR:
//            case Const.T_BYTE:
//                return InstructionConst.ICONST_0;
//            case Const.T_FLOAT:
//                return InstructionConst.FCONST_0;
//            case Const.T_DOUBLE:
//                return InstructionConst.DCONST_0;
//            case Const.T_LONG:
//                return InstructionConst.LCONST_0;
//            case Const.T_VOID:
//                return InstructionConst.NOP;
//            default:
//                throw new IllegalArgumentException("Invalid type: " + type);
//        }
//    }
//
//    public static StackInstruction createPop(final int size) {
//        return (size == 2) ? InstructionConst.POP2 : InstructionConst.POP;
//    }
//
//    public static ReturnInstruction createReturn(final Type type) {
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
//    public static LocalVariableInstruction createStore(final Type type, final int index) {
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
//    private static boolean isString(final Type type) {
//        return (type instanceof ObjectType) && ((ObjectType) type).getClassName().equals("java.lang.String");
//    }
}
