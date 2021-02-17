package com.wade.decompiler.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.Attribute;
import com.wade.decompiler.classfile.Code;
import com.wade.decompiler.classfile.CodeException;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.classfile.ConstantValue;
import com.wade.decompiler.classfile.ExceptionTable;
import com.wade.decompiler.classfile.InnerClass;
import com.wade.decompiler.classfile.InnerClasses;
import com.wade.decompiler.classfile.LineNumber;
import com.wade.decompiler.classfile.LineNumberTable;
import com.wade.decompiler.classfile.LocalVariable;
import com.wade.decompiler.classfile.LocalVariableTable;
import com.wade.decompiler.classfile.SourceFile;
import com.wade.decompiler.classfile.Utility;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

class AttributeHTML {
    private String class_name; // name of current class
    private PrintWriter file; // file to write to
    private int attr_count = 0;
    private ConstantHTML constant_html;
    private ConstantPool constant_pool;

    AttributeHTML(String dir, String class_name, ConstantPool constant_pool, ConstantHTML constant_html) throws IOException {
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

    private String codeLink(int link, int method_number) {
        return "<A HREF=\"" + class_name + "_code.html#code" + method_number + "@" + link + "\" TARGET=Code>" + link + "</A>";
    }

    void writeAttribute(Attribute attribute, String anchor) {
        writeAttribute(attribute, anchor, 0);
    }

    void writeAttribute(Attribute attribute, String anchor, int method_number) {
        ClassFileAttributes tag = attribute.getTag();
        int index;
        if (tag == ClassFileAttributes.ATTR_UNKNOWN) {
            return;
        }
        attr_count++; // Increment number of attributes found so far
        if (attr_count % 2 == 0) {
            file.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
        } else {
            file.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
        }
        file.println("<H4><A NAME=\"" + anchor + "\">" + attr_count + " " + Const.getAttributeName(tag.getTag()) + "</A></H4>");
        switch (tag) {
            case ATTR_CODE:
                Code c = (Code) attribute;
                // Some directly printable values
                file.print("<UL><LI>Maximum stack size = " + c.getMaxStack() + "</LI>\n<LI>Number of local variables = " + c.getMaxLocals() + "</LI>\n<LI><A HREF=\"" + class_name + "_code.html#method" + method_number + "\" TARGET=Code>Byte code</A></LI></UL>\n");
                // Get handled exceptions and list them
                CodeException[] ce = c.getExceptionTable();
                int len = ce.length;
                if (len > 0) {
                    file.print("<P><B>Exceptions handled</B><UL>");
                    for (CodeException cex : ce) {
                        int catch_type = cex.getCatchType(); // Index in constant pool
                        file.print("<LI>");
                        if (catch_type != 0) {
                            file.print(constant_html.referenceConstant(catch_type)); // Create Link to _cp.html
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
                // Reference _cp.html
                file.print("<UL><LI><A HREF=\"" + class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">Constant value index(" + index + ")</A></UL>\n");
                break;
            case ATTR_SOURCE_FILE:
                index = ((SourceFile) attribute).getSourceFileIndex();
                // Reference _cp.html
                file.print("<UL><LI><A HREF=\"" + class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">Source file index(" + index + ")</A></UL>\n");
                break;
            case ATTR_EXCEPTIONS:
                // List thrown exceptions
                int[] indices = ((ExceptionTable) attribute).getExceptionIndexTable();
                file.print("<UL>");
                for (int indice : indices) {
                    file.print("<LI><A HREF=\"" + class_name + "_cp.html#cp" + indice + "\" TARGET=\"ConstantPool\">Exception class index(" + indice + ")</A>\n");
                }
                file.print("</UL>\n");
                break;
            case ATTR_LINE_NUMBER_TABLE:
                LineNumber[] line_numbers = ((LineNumberTable) attribute).getLineNumberTable();
                // List line number pairs
                file.print("<P>");
                for (int i = 0; i < line_numbers.length; i++) {
                    file.print("(" + line_numbers[i].getStartPC() + ",&nbsp;" + line_numbers[i].getLineNumber() + ")");
                    if (i < line_numbers.length - 1) {
                        file.print(", "); // breakable
                    }
                }
                break;
            case ATTR_LOCAL_VARIABLE_TABLE:
                LocalVariable[] vars = ((LocalVariableTable) attribute).getLocalVariableTable();
                // List name, range and type
                file.print("<UL>");
                for (LocalVariable var : vars) {
                    index = var.getSignatureIndex();
                    String signature = ((ConstantUtf8) constant_pool.getConstant(index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
                    signature = Utility.signatureToString(signature, false);
                    int start = var.getStartPC();
                    int end = start + var.getLength();
                    file.println("<LI>" + Class2HTML.referenceType(signature) + "&nbsp;<B>" + var.getName() + "</B> in slot %" + var.getIndex() + "<BR>Valid from lines " + "<A HREF=\"" + class_name + "_code.html#code" + method_number + "@" + start + "\" TARGET=Code>" + start + "</A> to " + "<A HREF=\"" + class_name + "_code.html#code" + method_number + "@" + end + "\" TARGET=Code>" + end + "</A></LI>");
                }
                file.print("</UL>\n");
                break;
            case ATTR_INNER_CLASSES:
                InnerClass[] classes = ((InnerClasses) attribute).getInnerClasses();
                // List inner classes
                file.print("<UL>");
                for (InnerClass classe : classes) {
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
            default: // Such as Unknown attribute or Deprecated
                file.print("<P>" + attribute);
        }
        file.println("</TD></TR>");
        file.flush();
    }
}
