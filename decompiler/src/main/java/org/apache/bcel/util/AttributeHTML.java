package org.apache.bcel.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.Code;
import org.apache.bcel.classfile.attribute.ExceptionTable;
import org.apache.bcel.classfile.attribute.InnerClasses;
import org.apache.bcel.classfile.attribute.LineNumberTable;
import org.apache.bcel.classfile.attribute.LocalVariableTable;
import org.apache.bcel.classfile.attribute.SourceFile;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.classfile.constant.ConstantValue;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.enums.ClassFileConstants;

final class AttributeHTML {
    private final String class_name;
    private final PrintWriter file;
    private int attr_count = 0;
    private final ConstantHTML constant_html;
    private final ConstantPool constant_pool;

    AttributeHTML(final String dir, final String class_name, final ConstantPool constant_pool, final ConstantHTML constant_html) throws IOException {
        this.class_name = class_name;
        this.constant_pool = constant_pool;
        this.constant_html = constant_html;
        file = new PrintWriter(new FileOutputStream(dir + class_name + "_attributes.html"));
        file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
    }

    void close() {
        file.println("</TABLE></BODY></HTML>");
        file.close();
    }

    private String codeLink(final int link, final int method_number) {
        return "<A HREF=\"" + class_name + "_code.html#code" + method_number + "@" + link + "\" TARGET=Code>" + link + "</A>";
    }

    void writeAttribute(final Attribute attribute, final String anchor) {
        writeAttribute(attribute, anchor, 0);
    }

    void writeAttribute(final Attribute attribute, final String anchor, final int method_number) {
        ClassFileAttributes tag = attribute.getTag();
        int index;
        if (tag == ClassFileAttributes.ATTR_UNKNOWN) {
            return;
        }
        attr_count++;
        if (attr_count % 2 == 0) {
            file.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
        } else {
            file.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
        }
        file.println("<H4><A NAME=\"" + anchor + "\">" + attr_count + " " + tag.getName() + "</A></H4>");
        switch (tag) {
            case ATTR_CODE:
                final Code c = (Code) attribute;
                file.print("<UL><LI>Maximum stack size = " + c.getMaxStack() + "</LI>\n<LI>Number of local variables = " + c.getMaxLocals() + "</LI>\n<LI><A HREF=\"" + class_name + "_code.html#method" + method_number + "\" TARGET=Code>Byte code</A></LI></UL>\n");
                final CodeException[] ce = c.getExceptionTable();
                final int len = ce.length;
                if (len > 0) {
                    file.print("<P><B>Exceptions handled</B><UL>");
                    for (final CodeException cex : ce) {
                        final int catch_type = cex.getCatchType();
                        file.print("<LI>");
                        if (catch_type != 0) {
                            file.print(constant_html.referenceConstant(catch_type));
                        } else {
                            file.print("Any Exception");
                        }
                        file.print("<BR>(Ranging from lines " + codeLink(cex.getStartPC(), method_number) + " to " + codeLink(cex.getEndPC(), method_number) + ", handled at line " + codeLink(cex.getHandlerPC(), method_number) + ")</LI>");
                    }
                    file.print("</UL>");
                }
                break;
            case ATTR_CONSTANT_VALUE:
                index = ((ConstantValue) attribute).getConstantValueIndex();
                file.print("<UL><LI><A HREF=\"" + class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">Constant value index(" + index + ")</A></UL>\n");
                break;
            case ATTR_SOURCE_FILE:
                index = ((SourceFile) attribute).getSourceFileIndex();
                file.print("<UL><LI><A HREF=\"" + class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">Source file index(" + index + ")</A></UL>\n");
                break;
            case ATTR_EXCEPTIONS:
                final int[] indices = ((ExceptionTable) attribute).getExceptionIndexTable();
                file.print("<UL>");
                for (final int indice : indices) {
                    file.print("<LI><A HREF=\"" + class_name + "_cp.html#cp" + indice + "\" TARGET=\"ConstantPool\">Exception class index(" + indice + ")</A>\n");
                }
                file.print("</UL>\n");
                break;
            case ATTR_LINE_NUMBER_TABLE:
                final LineNumber[] line_numbers = ((LineNumberTable) attribute).getLineNumberTable();
                file.print("<P>");
                for (int i = 0; i < line_numbers.length; i++) {
                    file.print("(" + line_numbers[i].getStartPC() + ",&nbsp;" + line_numbers[i].getLineNumber() + ")");
                    if (i < line_numbers.length - 1) {
                        file.print(", ");
                    }
                }
                break;
            case ATTR_LOCAL_VARIABLE_TABLE:
                final LocalVariable[] vars = ((LocalVariableTable) attribute).getLocalVariableTable();
                file.print("<UL>");
                for (final LocalVariable var : vars) {
                    index = var.getSignatureIndex();
                    String signature = ((ConstantUtf8) constant_pool.getConstant(index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
                    signature = Utility.signatureToString(signature, false);
                    final int start = var.getStartPC();
                    final int end = start + var.getLength();
                    file.println("<LI>" + Class2HTML.referenceType(signature) + "&nbsp;<B>" + var.getName() + "</B> in slot %" + var.getIndex() + "<BR>Valid from lines " + "<A HREF=\"" + class_name + "_code.html#code" + method_number + "@" + start + "\" TARGET=Code>" + start + "</A> to " + "<A HREF=\"" + class_name + "_code.html#code" + method_number + "@" + end + "\" TARGET=Code>" + end + "</A></LI>");
                }
                file.print("</UL>\n");
                break;
            case ATTR_INNER_CLASSES:
                final InnerClass[] classes = ((InnerClasses) attribute).getInnerClasses();
                file.print("<UL>");
                for (final InnerClass classe : classes) {
                    String name;
                    String access;
                    index = classe.getInnerNameIndex();
                    if (index > 0) {
                        name = ((ConstantUtf8) constant_pool.getConstant(index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
                    } else {
                        name = "&lt;anonymous&gt;";
                    }
                    access = Utility.accessToString(classe.getInnerAccessFlags());
                    file.print("<LI><FONT COLOR=\"#FF0000\">" + access + "</FONT> " + constant_html.referenceConstant(classe.getInnerClassIndex()) + " in&nbsp;class " + constant_html.referenceConstant(classe.getOuterClassIndex()) + " named " + name + "</LI>\n");
                }
                file.print("</UL>\n");
                break;
            default:
                file.print("<P>" + attribute);
        }
        file.println("</TD></TR>");
        file.flush();
    }
}
