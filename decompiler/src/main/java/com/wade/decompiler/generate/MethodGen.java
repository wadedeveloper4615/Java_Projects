package com.wade.decompiler.generate;

import java.io.IOException;

import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.generate.attribute.AttributeGen;
import com.wade.decompiler.generate.attribute.CodeGen;
import com.wade.decompiler.generate.attribute.LineNumberTableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public class MethodGen extends FieldOrMethodGen {
    private CodeGen code;
    private LineNumberTableGen lineNumberTable;
    private LocalVariableTableGen localVariableTable;

    public MethodGen(Method value, ConstantPool constantPool) throws IOException {
        super(value, constantPool);
        for (AttributeGen attr : super.getAttributes()) {
            if (attr instanceof CodeGen) {
                code = (CodeGen) attr;
                lineNumberTable = code.getLineNumberTable();
                localVariableTable = code.getLocalVariableTable();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodGen other = (MethodGen) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (lineNumberTable == null) {
            if (other.lineNumberTable != null)
                return false;
        } else if (!lineNumberTable.equals(other.lineNumberTable))
            return false;
        if (localVariableTable == null) {
            if (other.localVariableTable != null)
                return false;
        } else if (!localVariableTable.equals(other.localVariableTable))
            return false;
        return true;
    }

    public CodeGen getCode() {
        return code;
    }

    public LineNumberTableGen getLineNumberTable() {
        return lineNumberTable;
    }

    public LocalVariableTableGen getLocalVariableTable() {
        return localVariableTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((lineNumberTable == null) ? 0 : lineNumberTable.hashCode());
        result = prime * result + ((localVariableTable == null) ? 0 : localVariableTable.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "\n\t\tMethodGen [\n\t\t\tcode=" + code + ",\n\t\t\tlineNumberTable=" + lineNumberTable + ",\n\t\t\tlocalVariableTable=" + localVariableTable + "\n\t\t]";
    }
}
