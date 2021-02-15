package org.apache.bcel.util;

public class InstructionFinder {
//    public interface CodeConstraint {
//        boolean checkCode(InstructionHandle[] match);
//    }
//
//    private static final int OFFSET = 32767;
//    private static final int NO_OPCODES = 256;
//    private static final Map<String, String> map = new HashMap<>();
//    static {
//        map.put("arithmeticinstruction", "(irem|lrem|iand|ior|ineg|isub|lneg|fneg|fmul|ldiv|fadd|lxor|frem|idiv|land|ixor|ishr|fsub|lshl|fdiv|iadd|lor|dmul|lsub|ishl|imul|lmul|lushr|dneg|iushr|lshr|ddiv|drem|dadd|ladd|dsub)");
//        map.put("invokeinstruction", "(invokevirtual|invokeinterface|invokestatic|invokespecial|invokedynamic)");
//        map.put("arrayinstruction", "(baload|aastore|saload|caload|fastore|lastore|iaload|castore|iastore|aaload|bastore|sastore|faload|laload|daload|dastore)");
//        map.put("gotoinstruction", "(goto|goto_w)");
//        map.put("conversioninstruction", "(d2l|l2d|i2s|d2i|l2i|i2b|l2f|d2f|f2i|i2d|i2l|f2d|i2c|f2l|i2f)");
//        map.put("localvariableinstruction", "(fstore|iinc|lload|dstore|dload|iload|aload|astore|istore|fload|lstore)");
//        map.put("loadinstruction", "(fload|dload|lload|iload|aload)");
//        map.put("fieldinstruction", "(getfield|putstatic|getstatic|putfield)");
//        map.put("cpinstruction", "(ldc2_w|invokeinterface|invokedynamic|multianewarray|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|ldc_w|invokestatic|invokevirtual|putfield|ldc|new|anewarray)");
//        map.put("stackinstruction", "(dup2|swap|dup2_x2|pop|pop2|dup|dup2_x1|dup_x2|dup_x1)");
//        map.put("branchinstruction", "(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)");
//        map.put("returninstruction", "(lreturn|ireturn|freturn|dreturn|areturn|return)");
//        map.put("storeinstruction", "(istore|fstore|dstore|astore|lstore)");
//        map.put("select", "(tableswitch|lookupswitch)");
//        map.put("ifinstruction", "(ifeq|ifgt|if_icmpne|if_icmpeq|ifge|ifnull|ifne|if_icmple|if_icmpge|if_acmpeq|if_icmplt|if_acmpne|ifnonnull|iflt|if_icmpgt|ifle)");
//        map.put("jsrinstruction", "(jsr|jsr_w)");
//        map.put("variablelengthinstruction", "(tableswitch|jsr|goto|lookupswitch)");
//        map.put("unconditionalbranch", "(goto|jsr|jsr_w|athrow|goto_w)");
//        map.put("constantpushinstruction", "(dconst|bipush|sipush|fconst|iconst|lconst)");
//        map.put("typedinstruction",
//                "(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dastore|ret|f2d|f2i|drem|iinc|i2c|checkcast|frem|lreturn|astore|lushr|daload|dneg|fastore|istore|lshl|ldiv|lstore|areturn|ishr|ldc_w|invokeinterface|invokedynamic|aastore|lxor|ishl|l2d|i2f|return|faload|sipush|iushr|caload|instanceof|invokespecial|putfield|fmul|ireturn|laload|d2f|lneg|ixor|i2l|fdiv|lastore|multianewarray|i2b|getstatic|i2d|putstatic|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|freturn|ldc|aconst_null|castore|lmul|ldc2_w|dadd|iconst|f2l|ddiv|dstore|land|jsr|anewarray|dmul|bipush|dsub|sastore|d2i|i2s|lshr|iadd|l2i|lload|bastore|fstore|fneg|iload|fadd|baload|fconst|ior|ineg|dreturn|l2f|lconst|getfield|invokevirtual|invokestatic|iastore)");
//        map.put("popinstruction", "(fstore|dstore|pop|pop2|astore|putstatic|istore|lstore)");
//        map.put("allocationinstruction", "(multianewarray|new|anewarray|newarray)");
//        map.put("indexedinstruction", "(lload|lstore|fload|ldc2_w|invokeinterface|invokedynamic|multianewarray|astore|dload|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|dstore|istore|iinc|ldc_w|ret|fstore|invokestatic|iload|putfield|invokevirtual|ldc|new|aload|anewarray)");
//        map.put("pushinstruction", "(dup|lload|dup2|bipush|fload|ldc2_w|sipush|lconst|fconst|dload|getstatic|ldc_w|aconst_null|dconst|iload|ldc|iconst|aload)");
//        map.put("stackproducer",
//                "(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dup|f2d|f2i|drem|i2c|checkcast|frem|lushr|daload|dneg|lshl|ldiv|ishr|ldc_w|invokeinterface|invokedynamic|lxor|ishl|l2d|i2f|faload|sipush|iushr|caload|instanceof|invokespecial|fmul|laload|d2f|lneg|ixor|i2l|fdiv|getstatic|i2b|swap|i2d|dup2|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|ldc|arraylength|aconst_null|tableswitch|lmul|ldc2_w|iconst|dadd|f2l|ddiv|land|jsr|anewarray|dmul|bipush|dsub|d2i|newarray|i2s|lshr|iadd|lload|l2i|fneg|iload|fadd|baload|fconst|lookupswitch|ior|ineg|lconst|l2f|getfield|invokevirtual|invokestatic)");
//        map.put("stackconsumer",
//                "(imul|lsub|lor|iflt|fcmpg|if_icmpgt|iand|ifeq|if_icmplt|lrem|ifnonnull|idiv|d2l|isub|dcmpg|dastore|if_icmpeq|f2d|f2i|drem|i2c|checkcast|frem|lreturn|astore|lushr|pop2|monitorexit|dneg|fastore|istore|lshl|ldiv|lstore|areturn|if_icmpge|ishr|monitorenter|invokeinterface|invokedynamic|aastore|lxor|ishl|l2d|i2f|return|iushr|instanceof|invokespecial|fmul|ireturn|d2f|lneg|ixor|pop|i2l|ifnull|fdiv|lastore|i2b|if_acmpeq|ifge|swap|i2d|putstatic|fcmpl|ladd|irem|dcmpl|fsub|freturn|ifgt|castore|lmul|dadd|f2l|ddiv|dstore|land|if_icmpne|if_acmpne|dmul|dsub|sastore|ifle|d2i|i2s|lshr|iadd|l2i|bastore|fstore|fneg|fadd|ior|ineg|ifne|dreturn|l2f|if_icmple|getfield|invokevirtual|invokestatic|iastore)");
//        map.put("exceptionthrower", "(irem|lrem|laload|putstatic|baload|dastore|areturn|getstatic|ldiv|anewarray|iastore|castore|idiv|saload|lastore|fastore|putfield|lreturn|caload|getfield|return|aastore|freturn|newarray|instanceof|multianewarray|athrow|faload|iaload|aaload|dreturn|monitorenter|checkcast|bastore|arraylength|new|invokevirtual|sastore|ldc_w|ireturn|invokespecial|monitorexit|invokeinterface|invokedynamic|ldc|invokestatic|daload)");
//        map.put("loadclass", "(multianewarray|invokeinterface|invokedynamic|instanceof|invokespecial|putfield|checkcast|putstatic|invokevirtual|new|getstatic|invokestatic|getfield|anewarray)");
//        map.put("instructiontargeter", "(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)");
//        map.put("if_icmp", "(if_icmpne|if_icmpeq|if_icmple|if_icmpge|if_icmplt|if_icmpgt)");
//        map.put("if_acmp", "(if_acmpeq|if_acmpne)");
//        map.put("if", "(ifeq|ifne|iflt|ifge|ifgt|ifle)");
//        map.put("iconst", precompile(InstructionOpCodes.ICONST_0, InstructionOpCodes.ICONST_5, InstructionOpCodes.ICONST_M1));
//        map.put("lconst", new String(new char[] { '(', makeChar(InstructionOpCodes.LCONST_0), '|', makeChar(InstructionOpCodes.LCONST_1), ')' }));
//        map.put("dconst", new String(new char[] { '(', makeChar(InstructionOpCodes.DCONST_0), '|', makeChar(InstructionOpCodes.DCONST_1), ')' }));
//        map.put("fconst", new String(new char[] { '(', makeChar(InstructionOpCodes.FCONST_0), '|', makeChar(InstructionOpCodes.FCONST_1), '|', makeChar(InstructionOpCodes.FCONST_2), ')' }));
//        map.put("lload", precompile(InstructionOpCodes.LLOAD_0, InstructionOpCodes.LLOAD_3, InstructionOpCodes.LLOAD));
//        map.put("iload", precompile(InstructionOpCodes.ILOAD_0, InstructionOpCodes.ILOAD_3, InstructionOpCodes.ILOAD));
//        map.put("dload", precompile(InstructionOpCodes.DLOAD_0, InstructionOpCodes.DLOAD_3, InstructionOpCodes.DLOAD));
//        map.put("fload", precompile(InstructionOpCodes.FLOAD_0, InstructionOpCodes.FLOAD_3, InstructionOpCodes.FLOAD));
//        map.put("aload", precompile(InstructionOpCodes.ALOAD_0, InstructionOpCodes.ALOAD_3, InstructionOpCodes.ALOAD));
//        map.put("lstore", precompile(InstructionOpCodes.LSTORE_0, InstructionOpCodes.LSTORE_3, InstructionOpCodes.LSTORE));
//        map.put("istore", precompile(InstructionOpCodes.ISTORE_0, InstructionOpCodes.ISTORE_3, InstructionOpCodes.ISTORE));
//        map.put("dstore", precompile(InstructionOpCodes.DSTORE_0, InstructionOpCodes.DSTORE_3, InstructionOpCodes.DSTORE));
//        map.put("fstore", precompile(InstructionOpCodes.FSTORE_0, InstructionOpCodes.FSTORE_3, InstructionOpCodes.FSTORE));
//        map.put("astore", precompile(InstructionOpCodes.ASTORE_0, InstructionOpCodes.ASTORE_3, InstructionOpCodes.ASTORE));
//        for (final Map.Entry<String, String> entry : map.entrySet()) {
//            final String key = entry.getKey();
//            final String value = entry.getValue();
//            final char ch = value.charAt(1);
//            if (ch < OFFSET) {
//                map.put(key, compilePattern(value));
//            }
//        }
//        final StringBuilder buf = new StringBuilder("(");
//        for (short i = 0; i < NO_OPCODES; i++) {
//            if (InstructionOpCodes.read(i).getNumberOfOperands() != Const.UNDEFINED) {
//                buf.append(makeChar(i));
//                if (i < NO_OPCODES - 1) {
//                    buf.append('|');
//                }
//            }
//        }
//        buf.append(')');
//        map.put("instruction", buf.toString());
//    }
//    private final InstructionList il;
//    private String ilString;
//    private InstructionHandle[] handles;
//
//    public InstructionFinder(final InstructionList il) {
//        this.il = il;
//        reread();
//    }
//
//    public final InstructionList getInstructionList() {
//        return il;
//    }
//
//    private InstructionHandle[] getMatch(final int matched_from, final int match_length) {
//        final InstructionHandle[] match = new InstructionHandle[match_length];
//        System.arraycopy(handles, matched_from, match, 0, match_length);
//        return match;
//    }
//
//    public final void reread() {
//        final int size = il.getLength();
//        final char[] buf = new char[size];
//        handles = il.getInstructionHandles();
//        for (int i = 0; i < size; i++) {
//            buf[i] = makeChar((short) handles[i].getInstruction().getOpcode().getOpcode());
//        }
//        ilString = new String(buf);
//    }
//
//    public final Iterator<InstructionHandle[]> search(final String pattern) {
//        return search(pattern, il.getStart(), null);
//    }
//
//    public final Iterator<InstructionHandle[]> search(final String pattern, final CodeConstraint constraint) {
//        return search(pattern, il.getStart(), constraint);
//    }
//
//    public final Iterator<InstructionHandle[]> search(final String pattern, final InstructionHandle from) {
//        return search(pattern, from, null);
//    }
//
//    public final Iterator<InstructionHandle[]> search(final String pattern, final InstructionHandle from, final CodeConstraint constraint) {
//        final String search = compilePattern(pattern);
//        int start = -1;
//        for (int i = 0; i < handles.length; i++) {
//            if (handles[i] == from) {
//                start = i;
//                break;
//            }
//        }
//        if (start == -1) {
//            throw new ClassGenException("Instruction handle " + from + " not found in instruction list.");
//        }
//        final Pattern regex = Pattern.compile(search);
//        final List<InstructionHandle[]> matches = new ArrayList<>();
//        final Matcher matcher = regex.matcher(ilString);
//        while (start < ilString.length() && matcher.find(start)) {
//            final int startExpr = matcher.start();
//            final int endExpr = matcher.end();
//            final int lenExpr = endExpr - startExpr;
//            final InstructionHandle[] match = getMatch(startExpr, lenExpr);
//            if ((constraint == null) || constraint.checkCode(match)) {
//                matches.add(match);
//            }
//            start = endExpr;
//        }
//        return matches.iterator();
//    }
//
//    private static String compilePattern(final String pattern) {
//        final String lower = pattern.toLowerCase(Locale.ENGLISH);
//        final StringBuilder buf = new StringBuilder();
//        final int size = pattern.length();
//        for (int i = 0; i < size; i++) {
//            char ch = lower.charAt(i);
//            if (Character.isLetterOrDigit(ch)) {
//                final StringBuilder name = new StringBuilder();
//                while ((Character.isLetterOrDigit(ch) || ch == '_') && i < size) {
//                    name.append(ch);
//                    if (++i < size) {
//                        ch = lower.charAt(i);
//                    } else {
//                        break;
//                    }
//                }
//                i--;
//                buf.append(mapName(name.toString()));
//            } else if (!Character.isWhitespace(ch)) {
//                buf.append(ch);
//            }
//        }
//        return buf.toString();
//    }
//
//    private static char makeChar(InstructionOpCodes opcode) {
//        return (char) (opcode.getOpcode() + OFFSET);
//    }
//
//    private static char makeChar(final short opcode) {
//        return (char) (opcode + OFFSET);
//    }
//
//    private static String mapName(final String pattern) {
//        final String result = map.get(pattern);
//        if (result != null) {
//            return result;
//        }
//        for (short i = 0; i < NO_OPCODES; i++) {
//            if (pattern.equals(InstructionOpCodes.read(i).getName())) {
//                return "" + makeChar(i);
//            }
//        }
//        throw new IllegalArgumentException("Instruction unknown: " + pattern);
//    }
//
//    private static String precompile(InstructionOpCodes from, InstructionOpCodes to, InstructionOpCodes extra) {
//        final StringBuilder buf = new StringBuilder("(");
//        for (short i = (short) from.getOpcode(); i <= to.getOpcode(); i++) {
//            buf.append(makeChar(i));
//            buf.append('|');
//        }
//        buf.append(makeChar(extra));
//        buf.append(")");
//        return buf.toString();
//    }
////    private static String precompile(final short from, final short to, final short extra) {
////        final StringBuilder buf = new StringBuilder("(");
////        for (short i = from; i <= to; i++) {
////            buf.append(makeChar(i));
////            buf.append('|');
////        }
////        buf.append(makeChar(extra));
////        buf.append(")");
////        return buf.toString();
////    }
}
