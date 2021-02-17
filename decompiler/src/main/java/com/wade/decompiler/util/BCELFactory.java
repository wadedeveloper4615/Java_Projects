package com.wade.decompiler.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.wade.decompiler.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.CHECKCAST;
import com.wade.decompiler.generic.IINC;
import com.wade.decompiler.generic.INSTANCEOF;
import com.wade.decompiler.generic.InvokeInstruction;
import com.wade.decompiler.generic.LDC;
import com.wade.decompiler.generic.LDC2_W;
import com.wade.decompiler.generic.MULTIANEWARRAY;
import com.wade.decompiler.generic.NEWARRAY;
import com.wade.decompiler.generic.RET;
import com.wade.decompiler.generic.ReturnInstruction;
import com.wade.decompiler.generic.Select;
import com.wade.decompiler.generic.base.AllocationInstruction;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.BranchHandle;
import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.FieldInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.InstructionConst;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.LocalVariableInstruction;
import com.wade.decompiler.generic.gen.CodeExceptionGen;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.EmptyVisitor;
import com.wade.decompiler.generic.gen.MethodGen;
import com.wade.decompiler.generic.type.ArrayType;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.Type;

class BCELFactory extends EmptyVisitor {
    private static String CONSTANT_PREFIX = Const.class.getSimpleName() + ".";
    private MethodGen _mg;
    private PrintWriter _out;
    private ConstantPoolGen _cp;
    private Map<Instruction, InstructionHandle> branch_map = new HashMap<>();
    // Memorize BranchInstructions that need an update
    private List<BranchInstruction> branches = new ArrayList<>();

    BCELFactory(MethodGen mg, PrintWriter out) {
        _mg = mg;
        _cp = mg.getConstantPool();
        _out = out;
    }

    private void createConstant(Object value) {
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
            ObjectType ot = (ObjectType) value;
            embed = "new ObjectType(\"" + ot.getClassName() + "\")";
        }
        _out.println("il.append(new PUSH(_cp, " + embed + "));");
    }

    public void start() {
        if (!_mg.isAbstract() && !_mg.isNative()) {
            for (InstructionHandle ih = _mg.getInstructionList().getStart(); ih != null; ih = ih.getNext()) {
                Instruction i = ih.getInstruction();
                if (i instanceof BranchInstruction) {
                    branch_map.put(i, ih); // memorize container
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
        for (BranchInstruction bi : branches) {
            BranchHandle bh = (BranchHandle) branch_map.get(bi);
            int pos = bh.getPosition();
            String name = bi.getName() + "_" + pos;
            int t_pos = bh.getTarget().getPosition();
            _out.println("    " + name + ".setTarget(ih_" + t_pos + ");");
            if (bi instanceof Select) {
                InstructionHandle[] ihs = ((Select) bi).getTargets();
                for (int j = 0; j < ihs.length; j++) {
                    t_pos = ihs[j].getPosition();
                    _out.println("    " + name + ".setTarget(" + j + ", ih_" + t_pos + ");");
                }
            }
        }
    }

    private void updateExceptionHandlers() {
        CodeExceptionGen[] handlers = _mg.getExceptionHandlers();
        for (CodeExceptionGen h : handlers) {
            String type = (h.getCatchType() == null) ? "null" : BCELifier.printType(h.getCatchType());
            _out.println("    method.addExceptionHandler(" + "ih_" + h.getStartPC().getPosition() + ", " + "ih_" + h.getEndPC().getPosition() + ", " + "ih_" + h.getHandlerPC().getPosition() + ", " + type + ");");
        }
    }

    @Override
    public void visitAllocationInstruction(AllocationInstruction i) {
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
                //$FALL-THROUGH$
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
    public void visitArrayInstruction(ArrayInstruction i) {
        short opcode = (short) i.getOpcode().getOpcode();
        Type type = i.getType(_cp);
        String kind = (opcode < Const.IASTORE) ? "Load" : "Store";
        _out.println("il.append(_factory.createArray" + kind + "(" + BCELifier.printType(type) + "));");
    }

    @Override
    public void visitBranchInstruction(BranchInstruction bi) {
        BranchHandle bh = (BranchHandle) branch_map.get(bi);
        int pos = bh.getPosition();
        String name = bi.getName() + "_" + pos;
        if (bi instanceof Select) {
            Select s = (Select) bi;
            branches.add(bi);
            StringBuilder args = new StringBuilder("new int[] { ");
            int[] matchs = s.getMatchs();
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
            int t_pos = bh.getTarget().getPosition();
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
    public void visitCHECKCAST(CHECKCAST i) {
        Type type = i.getType(_cp);
        _out.println("il.append(_factory.createCheckCast(" + BCELifier.printType(type) + "));");
    }

    @Override
    public void visitConstantPushInstruction(ConstantPushInstruction i) {
        createConstant(i.getValue());
    }

    @Override
    public void visitFieldInstruction(FieldInstruction i) {
        short opcode = (short) i.getOpcode().getOpcode();
        String class_name = i.getClassName(_cp);
        String field_name = i.getFieldName(_cp);
        Type type = i.getFieldType(_cp);
        _out.println("il.append(_factory.createFieldAccess(\"" + class_name + "\", \"" + field_name + "\", " + BCELifier.printType(type) + ", " + CONSTANT_PREFIX + Const.getOpcodeName(opcode).toUpperCase(Locale.ENGLISH) + "));");
    }

    @Override
    public void visitINSTANCEOF(INSTANCEOF i) {
        Type type = i.getType(_cp);
        _out.println("il.append(new INSTANCEOF(_cp.addClass(" + BCELifier.printType(type) + ")));");
    }

    private boolean visitInstruction(Instruction i) {
        short opcode = (short) i.getOpcode().getOpcode();
        if ((InstructionConst.getInstruction(opcode) != null) && !(i instanceof ConstantPushInstruction) && !(i instanceof ReturnInstruction)) { // Handled below
            _out.println("il.append(InstructionConst." + i.getName().toUpperCase(Locale.ENGLISH) + ");");
            return true;
        }
        return false;
    }

    @Override
    public void visitInvokeInstruction(InvokeInstruction i) {
        short opcode = (short) i.getOpcode().getOpcode();
        String class_name = i.getClassName(_cp);
        String method_name = i.getMethodName(_cp);
        Type type = i.getReturnType(_cp);
        Type[] arg_types = i.getArgumentTypes(_cp);
        _out.println("il.append(_factory.createInvoke(\"" + class_name + "\", \"" + method_name + "\", " + BCELifier.printType(type) + ", " + BCELifier.printArgumentTypes(arg_types) + ", " + CONSTANT_PREFIX + Const.getOpcodeName(opcode).toUpperCase(Locale.ENGLISH) + "));");
    }

    @Override
    public void visitLDC(LDC i) {
        createConstant(i.getValue(_cp));
    }

    @Override
    public void visitLDC2_W(LDC2_W i) {
        createConstant(i.getValue(_cp));
    }

    @Override
    public void visitLocalVariableInstruction(LocalVariableInstruction i) {
        short opcode = (short) i.getOpcode().getOpcode();
        Type type = i.getType(_cp);
        if (opcode == Const.IINC) {
            _out.println("il.append(new IINC(" + i.getIndex() + ", " + ((IINC) i).getIncrement() + "));");
        } else {
            String kind = (opcode < Const.ISTORE) ? "Load" : "Store";
            _out.println("il.append(_factory.create" + kind + "(" + BCELifier.printType(type) + ", " + i.getIndex() + "));");
        }
    }

    @Override
    public void visitRET(RET i) {
        _out.println("il.append(new RET(" + i.getIndex() + ")));");
    }

    @Override
    public void visitReturnInstruction(ReturnInstruction i) {
        Type type = i.getType(_cp);
        _out.println("il.append(_factory.createReturn(" + BCELifier.printType(type) + "));");
    }
}
