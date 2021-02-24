package com.wade.decompiler.decompiler;

import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.generate.FieldGen;
import com.wade.decompiler.generate.JavaClassGen;
import com.wade.decompiler.generate.MethodGen;
import com.wade.decompiler.generate.attribute.CodeGen;
import com.wade.decompiler.generate.attribute.LocalVariableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import com.wade.decompiler.util.Utility;

public class JavaClassFileDecompiler {
    private JavaClassGen jgen;

    public JavaClassFileDecompiler(JavaClassGen jgen) {
        this.jgen = jgen;
        String access = Utility.accessToString(jgen.getAccessFlags(), true);
        String type = Utility.classOrInterface(jgen.getAccessFlags());
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
        System.out.println(jgen.getConstantPool().toString("\t"));
        System.out.println("*/");
        System.out.println(fileBase + " {");
        deompileFields(jgen.getFields());
        System.out.println();
        deompileMethods(jgen.getMethods());
        System.out.println("}");
    }

    private void decompileInstructions(CodeGen codeGen, LocalVariableTableGen localVariables) {
        System.out.println("\t\t/*");
        System.out.println("\t\t\tlength     = " + codeGen.getLength());
        System.out.println("\t\t\tmax locals = " + codeGen.getMaxLocals());
        System.out.println("\t\t\tmax stack  = " + codeGen.getMaxStack());
        for (LocalVariableGen lv : localVariables.getLocalVariableTable()) {
            System.out.println("\t\t\t" + lv.toString());
        }
        System.out.println();
        for (Instruction instr : codeGen.getInstructions()) {
            System.out.println("\t\t\t" + instr.toString());
        }
        System.out.println("\t\t*/");
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

    private void deompileMethods(MethodGen[] methods) {
        for (MethodGen mg : methods) {
            String access = Utility.accessToString(mg.getAccessFlags(), true);
            String name = mg.getName();
            boolean constructor = false;
            if (name.equals("<init>")) {
                name = Utility.extractClassName(jgen.getClassName(), false);
                constructor = true;
            }
            String signature = Utility.methodSignatureToString(mg.getSignature(), name, access, true, mg.getLocalVariableTable(), constructor);
            System.out.println("\t" + signature + "{");
            decompileInstructions(mg.getCode(), mg.getLocalVariableTable());
            System.out.println("\t}");
            System.out.println();
        }

    }
}
