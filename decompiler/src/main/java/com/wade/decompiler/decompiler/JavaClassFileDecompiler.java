package com.wade.decompiler.decompiler;

import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.generate.FieldGen;
import com.wade.decompiler.generate.JavaClassGen;
import com.wade.decompiler.generate.MethodGen;
import com.wade.decompiler.generate.attribute.CodeGen;
import com.wade.decompiler.generate.attribute.LocalVariableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import com.wade.decompiler.generate.instructions.InstructionGen;
import com.wade.decompiler.util.Utility;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class JavaClassFileDecompiler {
    private JavaClassGen jgen;

    public JavaClassFileDecompiler(JavaClassGen jgen) {
        this.jgen = jgen;
        ClassAccessFlagsList accessFlags = jgen.getAccessFlags();
        boolean isAbstract = accessFlags.isAbstract();
        boolean isAnnotation = accessFlags.isAnnotation();
        String type = Utility.classType(accessFlags);
        String access = Utility.accessToString(accessFlags, true);
        String fileBase = String.format("%s %s %s extends %s", access, type, jgen.getClassName(), jgen.getSuperClassName());
        String[] interfaceNames = jgen.getInterfaceNames();
        int size = interfaceNames.length;
        if (size > 0) {
            fileBase += " implements ";
            for (int i = 0; i < size; i++) {
                fileBase += interfaceNames[i];
                if (i < size - 1) {
                    fileBase += ", ";
                }
            }
        }
        System.out.println("/*");
        System.out.println("\tVersion=" + jgen.getVersion());
        System.out.println(jgen.getConstantPool().toString());
        System.out.println("*/");
        System.out.println(fileBase + " {");
        deompileFields(jgen.getFields());
        System.out.println();
        deompileMethods(jgen.getMethods(), isAbstract, isAnnotation);
        System.out.println("}");
    }

    private void decompileInstructions(CodeGen codeGen, LocalVariableTableGen localVariables) {
        System.out.println("\t\t/*");
        if (codeGen != null) {
            System.out.println("\t\t\tlength     = " + codeGen.getLength());
            System.out.println("\t\t\tmax locals = " + codeGen.getMaxLocals());
            System.out.println("\t\t\tmax stack  = " + codeGen.getMaxStack());
            System.out.println("\t\t\tcode size  = " + codeGen.getCodeSize());
            for (LocalVariableGen lv : localVariables.getLocalVariableTable()) {
                System.out.println("\t\t\t" + lv.toString());
            }
            System.out.println();
            int offset = 0;
            for (Instruction instr : codeGen.getInstructions()) {
                System.out.println("\t\t\t " + offset + ") " + instr.toString());
                offset += instr.getLength();
            }
        }
        System.out.println("\t\t*/");
        System.out.println();
        for (InstructionGen instr : codeGen.getDecompiledInstructions()) {
            if (instr != null)
                System.out.println("\t\t" + instr.toString());
        }
    }

    private void deompileFields(FieldGen[] fields) {
        for (FieldGen fg : fields) {
            String access = Utility.accessToString(fg.getAccessFlags(), true);
            String signature = Utility.typeSignatureToString(fg.getSignature(), false);
            String fieldBase = String.format("\t%s %s %s", access, signature, fg.getName());
            if (fg.getConstant() != null) {
                fieldBase += " = " + fg.getConstant().getValueAsString() + ";";
            } else {
                fieldBase += ";";
            }
            System.out.println(fieldBase);
        }
    }

    private void deompileMethods(MethodGen[] methods, boolean isAbstract, boolean isAnnotation) {
        for (MethodGen mg : methods) {
            ClassAccessFlagsList flags = mg.getAccessFlags();
            boolean isNative = flags.isNative();
            if (isAbstract || isAnnotation) {
                flags.remove(ClassAccessFlags.ACC_ABSTRACT);
            }
            String access = Utility.accessToString(flags, true);
            String name = mg.getName();
            if (name.equals("<init>")) {
                name = Utility.extractClassName(jgen.getClassName(), false);
            }
            String signature = Utility.methodSignatureToString(mg.getSignature(), name, access, true, mg.getLocalVariableTable());
            if (!(isAbstract || isAnnotation || isNative)) {
                System.out.println("\t" + signature + "{");
                decompileInstructions(mg.getCode(), mg.getLocalVariableTable());
                System.out.println("\t}");
            } else {
                System.out.println("\t" + signature);
            }
            System.out.println();
        }
    }
}