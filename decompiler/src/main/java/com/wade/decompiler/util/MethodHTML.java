package com.wade.decompiler.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.wade.decompiler.classfile.Attribute;
import com.wade.decompiler.classfile.Code;
import com.wade.decompiler.classfile.ConstantValue;
import com.wade.decompiler.classfile.ExceptionTable;
import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.Utility;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileAttributes;

class MethodHTML {
    private String className; // name of current class
    private PrintWriter file; // file to write to
    private ConstantHTML constantHtml;
    private AttributeHTML attribute_html;

    MethodHTML(String dir, String class_name, Method[] methods, Field[] fields, ConstantHTML constant_html, AttributeHTML attribute_html) throws IOException {
        this.className = class_name;
        this.attribute_html = attribute_html;
        this.constantHtml = constant_html;
        file = new PrintWriter(new FileOutputStream(dir + class_name + "_methods.html"));
        file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
        file.println("<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH>" + "<TH ALIGN=LEFT>Field&nbsp;name</TH></TR>");
        for (Field field : fields) {
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

    /**
     * @throws IOException
     */
    private void writeField(Field field) throws IOException {
        String type = Utility.signatureToString(field.getSignature());
        String name = field.getName();
        String access = Utility.accessToString(new ClassAccessFlagsList(field.getFlags()));
        Attribute[] attributes;
        access = Utility.replace(access, " ", "&nbsp;");
        file.print("<TR><TD><FONT COLOR=\"#FF0000\">" + access + "</FONT></TD>\n<TD>" + Class2HTML.referenceType(type) + "</TD><TD><A NAME=\"field" + name + "\">" + name + "</A></TD>");
        attributes = field.getAttributes();
        // Write them to the Attributes.html file with anchor "<name>[<i>]"
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], name + "@" + i);
        }
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i].getTag() == ClassFileAttributes.ATTR_CONSTANT_VALUE) { // Default value
                String str = ((ConstantValue) attributes[i]).toString();
                // Reference attribute in _attributes.html
                file.print("<TD>= <A HREF=\"" + className + "_attributes.html#" + name + "@" + i + "\" TARGET=\"Attributes\">" + str + "</TD>\n");
                break;
            }
        }
        file.println("</TR>");
    }

    private void writeMethod(Method method, int method_number) {
        // Get raw signature
        String signature = method.getSignature();
        // Get array of strings containing the argument types
        String[] args = Utility.methodSignatureArgumentTypes(signature, false);
        // Get return type string
        String type = Utility.methodSignatureReturnType(signature, false);
        // Get method name
        String name = method.getName();
        String html_name;
        // Get method's access flags
        String access = Utility.accessToString(new ClassAccessFlagsList(method.getFlags()));
        // Get the method's attributes, the Code Attribute in particular
        Attribute[] attributes = method.getAttributes();
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
        // Check for thrown exceptions
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], "method" + method_number + "@" + i, method_number);
            ClassFileAttributes tag = attributes[i].getTag();
            if (tag == ClassFileAttributes.ATTR_EXCEPTIONS) {
                file.print("<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>");
                int[] exceptions = ((ExceptionTable) attributes[i]).getExceptionIndexTable();
                for (int j = 0; j < exceptions.length; j++) {
                    file.print(constantHtml.referenceConstant(exceptions[j]));
                    if (j < exceptions.length - 1) {
                        file.print(", ");
                    }
                }
                file.println("</TD></TR>");
            } else if (tag == ClassFileAttributes.ATTR_CODE) {
                Attribute[] c_a = ((Code) attributes[i]).getAttributes();
                for (int j = 0; j < c_a.length; j++) {
                    attribute_html.writeAttribute(c_a[j], "method" + method_number + "@" + i + "@" + j, method_number);
                }
            }
        }
    }
}
