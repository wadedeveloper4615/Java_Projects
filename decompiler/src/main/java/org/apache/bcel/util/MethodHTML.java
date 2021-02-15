package org.apache.bcel.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.Code;
import org.apache.bcel.classfile.attribute.ExceptionTable;
import org.apache.bcel.classfile.constant.ConstantValue;
import org.apache.bcel.enums.ClassFileAttributes;

final class MethodHTML {
    private final String className;
    private final PrintWriter file;
    private final ConstantHTML constantHtml;
    private final AttributeHTML attribute_html;

    MethodHTML(final String dir, final String class_name, final Method[] methods, final Field[] fields, final ConstantHTML constant_html, final AttributeHTML attribute_html) throws IOException {
        this.className = class_name;
        this.attribute_html = attribute_html;
        this.constantHtml = constant_html;
        file = new PrintWriter(new FileOutputStream(dir + class_name + "_methods.html"));
        file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
        file.println("<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH>" + "<TH ALIGN=LEFT>Field&nbsp;name</TH></TR>");
        for (final Field field : fields) {
            writeField(field);
        }
        file.println("</TABLE>");
        file.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Access&nbsp;flags</TH>" + "<TH ALIGN=LEFT>Return&nbsp;type</TH><TH ALIGN=LEFT>Method&nbsp;name</TH>" + "<TH ALIGN=LEFT>Arguments</TH></TR>");
        for (int i = 0; i < methods.length; i++) {
            writeMethod(methods[i], i);
        }
        file.println("</TABLE></BODY></HTML>");
        file.close();
    }

    private void writeField(final Field field) {// throws IOException {
        final String type = Utility.signatureToString(field.getSignature());
        final String name = field.getName();
        String access = Utility.accessToString(field.getAccessFlags());
        Attribute[] attributes;
        access = Utility.replace(access, " ", "&nbsp;");
        file.print("<TR><TD><FONT COLOR=\"#FF0000\">" + access + "</FONT></TD>\n<TD>" + Class2HTML.referenceType(type) + "</TD><TD><A NAME=\"field" + name + "\">" + name + "</A></TD>");
        attributes = field.getAttributes();
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], name + "@" + i);
        }
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i].getTag() == ClassFileAttributes.ATTR_CONSTANT_VALUE) {
                final String str = ((ConstantValue) attributes[i]).toString();
                file.print("<TD>= <A HREF=\"" + className + "_attributes.html#" + name + "@" + i + "\" TARGET=\"Attributes\">" + str + "</TD>\n");
                break;
            }
        }
        file.println("</TR>");
    }

    private void writeMethod(final Method method, final int method_number) {
        final String signature = method.getSignature();
        final String[] args = Utility.methodSignatureArgumentTypes(signature, false);
        final String type = Utility.methodSignatureReturnType(signature, false);
        final String name = method.getName();
        String html_name;
        String access = Utility.accessToString(method.getAccessFlags());
        final Attribute[] attributes = method.getAttributes();
        access = Utility.replace(access, " ", "&nbsp;");
        html_name = Class2HTML.toHTML(name);
        file.print("<TR VALIGN=TOP><TD><FONT COLOR=\"#FF0000\"><A NAME=method" + method_number + ">" + access + "</A></FONT></TD>");
        file.print("<TD>" + Class2HTML.referenceType(type) + "</TD><TD>" + "<A HREF=" + className + "_code.html#method" + method_number + " TARGET=Code>" + html_name + "</A></TD>\n<TD>(");
        for (int i = 0; i < args.length; i++) {
            file.print(Class2HTML.referenceType(args[i]));
            if (i < args.length - 1) {
                file.print(", ");
            }
        }
        file.print(")</TD></TR>");
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], "method" + method_number + "@" + i, method_number);
            ClassFileAttributes tag = attributes[i].getTag();
            if (tag == ClassFileAttributes.ATTR_EXCEPTIONS) {
                file.print("<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>");
                final int[] exceptions = ((ExceptionTable) attributes[i]).getExceptionIndexTable();
                for (int j = 0; j < exceptions.length; j++) {
                    file.print(constantHtml.referenceConstant(exceptions[j]));
                    if (j < exceptions.length - 1) {
                        file.print(", ");
                    }
                }
                file.println("</TD></TR>");
            } else if (tag == ClassFileAttributes.ATTR_CODE) {
                final Attribute[] c_a = ((Code) attributes[i]).getAttributes();
                for (int j = 0; j < c_a.length; j++) {
                    attribute_html.writeAttribute(c_a[j], "method" + method_number + "@" + i + "@" + j, method_number);
                }
            }
        }
    }
}
