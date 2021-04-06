package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.LineNumber;
import com.wade.decompiler.classfile.attribute.LineNumberTable;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LineNumberTableGen extends AttributeGen {
    private List<LineNumberGen> lineNumberTable;

    public LineNumberTableGen(LineNumberTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        List<LineNumber> lineNumberTable = attribute.getLineNumberTable();
        this.lineNumberTable = new ArrayList<>();
        for (LineNumber entry : attribute.getLineNumberTable()) {
            this.lineNumberTable.add(new LineNumberGen(entry));
        }
    }
}
