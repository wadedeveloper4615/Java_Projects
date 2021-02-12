package org.apache.bcel.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.IINC;
import org.apache.bcel.generic.INSTANCEOF;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LDC2_W;
import org.apache.bcel.generic.MULTIANEWARRAY;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.RET;
import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.AllocationInstruction;
import org.apache.bcel.generic.base.ArrayInstruction;
import org.apache.bcel.generic.base.ArrayType;
import org.apache.bcel.generic.base.BranchHandle;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.EmptyVisitor;
import org.apache.bcel.generic.base.FieldInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.InvokeInstruction;
import org.apache.bcel.generic.base.LocalVariableInstruction;
import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.control.InstructionConst;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.gen.CodeExceptionGen;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.generic.gen.MethodGen;

class BCELFactory extends EmptyVisitor {
    private static final String CONSTANT_PREFIX = Const.class.getSimpleName() + ".";
    private final MethodGen _mg;
    private final PrintWriter _out;
    private final ConstantPoolGen _cp;
    private final Map<Instruction, InstructionHandle> branch_map = new HashMap<>();
    private final List<BranchInstruction> branches = new ArrayList<>();

    BCELFactory(final MethodGen mg, final PrintWriter out) {
        _mg = mg;
        _cp = mg.getConstantPool();
        _out = out;
    }

    private void createConstant(final Object value) {
        String embed = value.toString();
        if (value instanceof String) {
            embed = '"' + Utility.convertString(embed) + '"';
        } else if (value instanceof Character) {
            embed = "(char)0x" + Integer.toHexString(((Character) value).charValue());
        } else if (value instanceof Float) {
            embed += "f";
        } else if (value instanceof Long) {
            embed += "L";
        } else if (value instanceof ObjectType) {
            final ObjectType ot = (ObjectType) value;
            embed = "new ObjectType(\"" + ot.getClassName() + "\")";
        }
        _out.println("il.append(new PUSH(_cp, " + embed + "));");
    }

    public void start() throws Throwable {
        if (!_mg.isAbstract() && !_mg.isNative()) {
            for (InstructionHandle ih = _mg.getInstructionList().getStart(); ih != null; ih = ih.getNext()) {
                final Instruction i = ih.getInstruction();
                if (i instanceof BranchInstruction) {
                    branch_map.put(i, ih);
                }
                if (ih.hasTargeters()) {
                    if (i instanceof BranchInstruction) {
                        _out.println("    InstructionHandle ih_" + ih.getPosition() + ";");
                    } else {
                        _out.print("    InstructionHandle ih_" + ih.getPosition() + " = ");
                    }
                } else {
                    _out.print("    ");
                }
                if (!visitInstruction(i)) {
                    i.accept(this);
                }
            }
            updateBranchTargets();
            updateExceptionHandlers();
        }
    }

    private void updateBranchTargets() {
        for (final BranchInstruction bi : branches) {
            final BranchHandle bh = (BranchHandle) branch_map.get(bi);
            final int pos = bh.getPosition();
            final String name = bi.getName() + "_" + pos;
            int t_pos = bh.getTarget().getPosition();
            _out.println("    " + name + ".setTarget(ih_" + t_pos + ");");
            if (bi instanceof Select) {
                final InstructionHandle[] ihs = ((Select) bi).getTargets();
                for (int j = 0; j < ihs.length; j++) {
                    t_pos = ihs[j].getPosition();
                    _out.println("    " + name + ".setTarget(" + j + ", ih_" + t_pos + ");");
                }
            }
        }
    }

    private void updateExceptionHandlers() {
        final CodeExceptionGen[] handlers = _mg.getExceptionHandlers();
        for (final CodeExceptionGen h : handlers) {
            final String type = (h.getCatchType() == null) ? "null" : BCELifier.printType(h.getCatchType());
            _out.println("    method.addExceptionHandler(" + "ih_" + h.getStartPC().getPosition() + ", " + "ih_" + h.getEndPC().getPosition() + ", " + "ih_" + h.getHandlerPC().getPosition() + ", " + type + ");");
        }
    }

    @Override
    public void visitAllocationInstruction(final AllocationInstruction i) {
        Type type;
        if (i instanceof CPInstruction) {
            type = ((CPInstruction) i).getType(_cp);
        } else {
            type = ((NEWARRAY) i).getType();
        }
        InstructionOpCodes opcode = ((Instruction) i).getOpcode();
        int dim = 1;
        switch (opcode) {
            case NEW:
                _out.println("il.append(_factory.createNew(\"" + ((ObjectType) type).getClassName() + "\"));");
                break;
            case MULTIANEWARRAY:
                dim = ((MULTIANEWARRAY) i).getDimensions();
            case ANEWARRAY:
            case NEWARRAY:
                if (type instanceof ArrayType) {
                    type = ((ArrayType) type).getBasicType();
                }
                _out.println("il.append(_factory.createNewArray(" + BCELifier.printType(type) + ", (short) " + dim + "));");
                break;
            default:
                throw new IllegalArgumentException("Unhandled opcode: " + opcode);
        }
    }

    @Override
    public void visitArrayInstruction(final ArrayInstruction i) {
        InstructionOpCodes opcode = i.getOpcode();
        final Type type = i.getType(_cp);
        final String kind = (opcode.getOpcode() < InstructionOpCodes.IASTORE.getOpcode()) ? "Load" : "Store";
        _out.println("il.append(_factory.createArray" + kind + "(" + BCELifier.printType(type) + "));");
    }

    @Override
    public void visitBranchInstruction(final BranchInstruction bi) {
        final BranchHandle bh = (BranchHandle) branch_map.get(bi);
        final int pos = bh.getPosition();
        final String name = bi.getName() + "_" + pos;
        if (bi instanceof Select) {
            final Select s = (Select) bi;
            branches.add(bi);
            final StringBuilder args = new StringBuilder("new int[] { ");
            final int[] matchs = s.getMatchs();
            for (int i = 0; i < matchs.length; i++) {
                args.append(matchs[i]);
                if (i < matchs.length - 1) {
                    args.append(", ");
                }
            }
            args.append(" }");
            _out.print("Select " + name + " = new " + bi.getName().toUpperCase(Locale.ENGLISH) + "(" + args + ", new InstructionHandle[] { ");
            for (int i = 0; i < matchs.length; i++) {
                _out.print("null");
                if (i < matchs.length - 1) {
                    _out.print(", ");
                }
            }
            _out.println(" }, null);");
        } else {
            final int t_pos = bh.getTarget().getPosition();
            String target;
            if (pos > t_pos) {
                target = "ih_" + t_pos;
            } else {
                branches.add(bi);
                target = "null";
            }
            _out.println("    BranchInstruction " + name + " = _factory.createBranchInstruction(" + CONSTANT_PREFIX + bi.getName().toUpperCase(Locale.ENGLISH) + ", " + target + ");");
        }
        if (bh.hasTargeters()) {
            _out.println("    ih_" + pos + " = il.append(" + name + ");");
        } else {
            _out.println("    il.append(" + name + ");");
        }
    }

    @Override
    public void visitCHECKCAST(final CHECKCAST i) {
        final Type type = i.getType(_cp);
        _out.println("il.append(_factory.createCheckCast(" + BCELifier.printType(type) + "));");
    }

    @Override
    public void visitConstantPushInstruction(final ConstantPushInstruction i) {
        createConstant(i.getValue());
    }

    @Override
    public void visitFieldInstruction(final FieldInstruction i) {
        InstructionOpCodes opcode = i.getOpcode();
        final String class_name = i.getClassName(_cp);
        final String field_name = i.getFieldName(_cp);
        final Type type = i.getFieldType(_cp);
        _out.println("il.append(_factory.createFieldAccess(\"" + class_name + "\", \"" + field_name + "\", " + BCELifier.printType(type) + ", " + CONSTANT_PREFIX + opcode.getName().toUpperCase(Locale.ENGLISH) + "));");
    }

    @Override
    public void visitINSTANCEOF(final INSTANCEOF i) {
        final Type type = i.getType(_cp);
        _out.println("il.append(new INSTANCEOF(_cp.addClass(" + BCELifier.printType(type) + ")));");
    }

    private boolean visitInstruction(final Instruction i) {
        InstructionOpCodes opcode = i.getOpcode();
        if ((InstructionConst.getInstruction(opcode.getOpcode()) != null) && !(i instanceof ConstantPushInstruction) && !(i instanceof ReturnInstruction)) {
            _out.println("il.append(InstructionConst." + i.getName().toUpperCase(Locale.ENGLISH) + ");");
            return true;
        }
        return false;
    }

    @Override
    public void visitInvokeInstruction(final InvokeInstruction i) {
        InstructionOpCodes opcode = i.getOpcode();
        final String class_name = i.getClassName(_cp);
        final String method_name = i.getMethodName(_cp);
        final Type type = i.getReturnType(_cp);
        final Type[] arg_types = i.getArgumentTypes(_cp);
        _out.println("il.append(_factory.createInvoke(\"" + class_name + "\", \"" + method_name + "\", " + BCELifier.printType(type) + ", " + BCELifier.printArgumentTypes(arg_types) + ", " + CONSTANT_PREFIX + opcode.getName().toUpperCase(Locale.ENGLISH) + "));");
    }

    @Override
    public void visitLDC(final LDC i) {
        createConstant(i.getValue(_cp));
    }

    @Override
    public void visitLDC2_W(final LDC2_W i) {
        createConstant(i.getValue(_cp));
    }

    @Override
    public void visitLocalVariableInstruction(final LocalVariableInstruction i) {
        InstructionOpCodes opcode = i.getOpcode();
        final Type type = i.getType(_cp);
        if (opcode == InstructionOpCodes.IINC) {
            _out.println("il.append(new IINC(" + i.getIndex() + ", " + ((IINC) i).getIncrement() + "));");
        } else {
            String kind = (opcode.getOpcode() < InstructionOpCodes.ISTORE.getOpcode()) ? "Load" : "Store";
            _out.println("il.append(_factory.create" + kind + "(" + BCELifier.printType(type) + ", " + i.getIndex() + "));");
        }
    }

    @Override
    public void visitRET(final RET i) {
        _out.println("il.append(new RET(" + i.getIndex() + ")));");
    }

    @Override
    public void visitReturnInstruction(final ReturnInstruction i) {
        final Type type = i.getType(_cp);
        _out.println("il.append(_factory.createReturn(" + BCELifier.printType(type) + "));");
    }
}
