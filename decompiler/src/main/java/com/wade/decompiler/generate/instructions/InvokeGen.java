package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.constant.*;
import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class InvokeGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;
    private Type[] parameterTypes;
    @ToString.Exclude
    private ConstantPool constantPool;
    private Integer index;
    private Integer nargs;
    private String superName;
    private String methodName;
    private String signature;
    private Object constantValue;
    private String constantString;

    public InvokeGen(int offset, INVOKEDYNAMIC instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.getReturnType(getSignature());
        parameterTypes = Type.getArgumentTypes(this.getSignature());
    }

    public InvokeGen(int offset, INVOKEINTERFACE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = instr.getNargs();
        type = Type.getReturnType(getSignature());
        parameterTypes = Type.getArgumentTypes(this.getSignature());
    }

    public InvokeGen(int offset, INVOKESPECIAL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.getReturnType(getSignature());
        parameterTypes = Type.getArgumentTypes(this.getSignature());
    }

    public InvokeGen(int offset, INVOKESTATIC instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.getReturnType(getSignature());
        parameterTypes = Type.getArgumentTypes(this.getSignature());
    }

    public InvokeGen(int offset, INVOKEVIRTUAL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        constantPool = instr.getConstantPool();
        ConstantCP c = (ConstantCP) constantPool.getConstant(instr.getIndex());
        extractConstantPoolInfo(c);
        index = instr.getIndex();
        nargs = null;
        type = Type.getReturnType(getSignature());
        parameterTypes = Type.getArgumentTypes(this.getSignature());
    }

    @Override
    public String decompile(ExpressionStack stack) {
        int numberOfParameters = this.parameterTypes.length;
        List<Expression> listOfParameters = new ArrayList<>();
        for (int i = 1; i <= numberOfParameters; i++) {
            if (!stack.empty()) {
                listOfParameters.add(0, stack.pop());
            }
        }
        if (opcode == InstructionOpCodes.INVOKEVIRTUAL) {
            if (!stack.empty()) {
                Expression item2 = stack.pop();
                return item2.getValue() + "." + this.methodName + "(" + listToListOfStrings(listOfParameters) + ")";
            }
        } else if (opcode == InstructionOpCodes.INVOKESPECIAL) {
            if (!stack.empty()) {
                Expression item2 = stack.pop();
                if (this.methodName.equals("<init>") && item2.getValue().equals("this")) {
                    return "super(" + listToListOfStrings(listOfParameters) + ")";
                }
                return item2.getValue() + "." + this.methodName + "(" + listToListOfStrings(listOfParameters) + ")";
            }
        } else if (opcode == InstructionOpCodes.INVOKEINTERFACE) {
            if (!stack.empty()) {
                Expression item2 = stack.pop();
                if (this.methodName.equals("<init>") && item2.getValue().equals("this")) {
                    return "super(" + listToListOfStrings(listOfParameters) + ")";
                }
                String str = item2.getValue() + "." + this.methodName + "(" + listToListOfStrings(listOfParameters) + ")";
                Expression item = new Expression(ExpressionType.EXPRESSION, str);
                stack.push(item);
                return "pushed " + item.toString();
            }
        } else if (opcode == InstructionOpCodes.INVOKEDYNAMIC) {
            String item2 = ((ObjectType) type).getClassName();
            String str = item2 + "." + this.methodName + "(" + listToListOfStrings(listOfParameters) + ")";
            Expression item = new Expression(ExpressionType.EXPRESSION, str);
            stack.push(item);
            return "pushed " + item.toString();
        } else if (opcode == InstructionOpCodes.INVOKESTATIC) {
            String item2 = ((ObjectType) type).getClassName();
            String str = item2 + "." + this.methodName + "(" + listToListOfStrings(listOfParameters) + ")";
            Expression item = new Expression(ExpressionType.EXPRESSION, str);
            stack.push(item);
            return "pushed " + item.toString();
        }
        return null;
    }

    private void extractConstantPoolInfo(Constant c) {
        if (c instanceof ConstantMethodref) {
            int classIndex = ((ConstantMethodref) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantMethodref) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantInterfaceMethodRef) {
            int classIndex = ((ConstantInterfaceMethodRef) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantInterfaceMethodRef) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantFieldRef) {
            int classIndex = ((ConstantFieldRef) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantFieldRef) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantInvokeDynamic) {
            // int classIndex = ((ConstantInvokeDynamic) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantInvokeDynamic) c).getNameAndTypeIndex();

            // ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex,
            // ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            // superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(),
            // ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantClass) {
            ConstantClass constantClass = (ConstantClass) c;
            constantValue = ((ConstantUtf8) constantPool.getConstant(constantClass.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantUtf8) {
            constantString = ((ConstantUtf8) c).getBytes();
        } else if (c instanceof ConstantNameAndType) {
            methodName = constantPool.constantToString(((ConstantNameAndType) c).getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
            signature = constantPool.constantToString(((ConstantNameAndType) c).getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
        } else if (c instanceof ConstantLong) {
            constantValue = Long.valueOf(((ConstantLong) c).getBytes());
        } else {
            System.out.println(c.getClass().getName());
        }
    }

    private String listToListOfStrings(List<Expression> list) {
        StringBuffer buffer = new StringBuffer();
        for (Expression e : list) {
            buffer.append(e.getValue() + ",");
        }
        String string = buffer.toString();
        if (list.size() >= 1) {
            return string.substring(0, string.length() - 1);
        }
        return string;
    }
}
