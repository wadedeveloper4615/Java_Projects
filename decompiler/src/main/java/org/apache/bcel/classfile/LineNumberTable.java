
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class LineNumberTable extends Attribute {

    private static final int MAX_LINE_LENGTH = 72;
    private LineNumber[] lineNumberTable; // Table of line/numbers pairs

    public LineNumberTable(final LineNumberTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLineNumberTable(), c.getConstantPool());
    }

    public LineNumberTable(final int name_index, final int length, final LineNumber[] line_number_table, final ConstantPool constant_pool) {
        super(Const.ATTR_LINE_NUMBER_TABLE, name_index, length, constant_pool);
        this.lineNumberTable = line_number_table;
    }

    LineNumberTable(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (LineNumber[]) null, constant_pool);
        final int line_number_table_length = input.readUnsignedShort();
        lineNumberTable = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            lineNumberTable[i] = new LineNumber(input);
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLineNumberTable(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(lineNumberTable.length);
        for (final LineNumber lineNumber : lineNumberTable) {
            lineNumber.dump(file);
        }
    }

    public LineNumber[] getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(final LineNumber[] lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        final StringBuilder line = new StringBuilder();
        final String newLine = System.getProperty("line.separator", "\n");
        for (int i = 0; i < lineNumberTable.length; i++) {
            line.append(lineNumberTable[i].toString());
            if (i < lineNumberTable.length - 1) {
                line.append(", ");
            }
            if ((line.length() > MAX_LINE_LENGTH) && (i < lineNumberTable.length - 1)) {
                line.append(newLine);
                buf.append(line);
                line.setLength(0);
            }
        }
        buf.append(line);
        return buf.toString();
    }

    public int getSourceLine(final int pos) {
        int l = 0;
        int r = lineNumberTable.length - 1;
        if (r < 0) {
            return -1;
        }
        int min_index = -1;
        int min = -1;

        do {
            final int i = (l + r) >>> 1;
            final int j = lineNumberTable[i].getStartPC();
            if (j == pos) {
                return lineNumberTable[i].getLineNumber();
            } else if (pos < j) {
                r = i - 1;
            } else {
                l = i + 1;
            }

            if (j < pos && j > min) {
                min = j;
                min_index = i;
            }
        } while (l <= r);

        if (min_index < 0) {
            return -1;
        }
        return lineNumberTable[min_index].getLineNumber();
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        // TODO could use the lower level constructor and thereby allow
        // lineNumberTable to be made final
        final LineNumberTable c = (LineNumberTable) clone();
        c.lineNumberTable = new LineNumber[lineNumberTable.length];
        for (int i = 0; i < lineNumberTable.length; i++) {
            c.lineNumberTable[i] = lineNumberTable[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    public int getTableLength() {
        return lineNumberTable == null ? 0 : lineNumberTable.length;
    }
}
