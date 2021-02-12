package org.apache.bcel.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.Code;
import org.apache.bcel.classfile.attribute.LocalVariableTable;
import org.apache.bcel.classfile.constant.ConstantFieldref;
import org.apache.bcel.classfile.constant.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.constant.ConstantInvokeDynamic;
import org.apache.bcel.classfile.constant.ConstantMethodref;
import org.apache.bcel.classfile.constant.ConstantNameAndType;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.enums.ClassFileConstants;
import org.apache.bcel.enums.InstructionOpCodes;

final class CodeHTML {
    private static boolean wide = false;
    private final String className;
    private final PrintWriter file;
    private BitSet gotoSet;
    private final ConstantPool constantPool;
    private final ConstantHTML constantHtml;

    CodeHTML(final String dir, final String class_name, final Method[] methods, final ConstantPool constant_pool, final ConstantHTML constant_html) throws IOException {
        this.className = class_name;
        this.constantPool = constant_pool;
        this.constantHtml = constant_html;
        file = new PrintWriter(new FileOutputStream(dir + class_name + "_code.html"));
        file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\">");
        for (int i = 0; i < methods.length; i++) {
            writeMethod(methods[i], i);
        }
        file.println("</BODY></HTML>");
        file.close();
    }

    private String codeToHTML(final ByteSequence bytes, final int method_number) throws IOException {
        InstructionOpCodes opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
        String name;
        String signature;
        int default_offset = 0;
        int low;
        int high;
        int index;
        int class_index;
        int vindex;
        int constant;
        int[] jump_table;
        int no_pad_bytes = 0;
        int offset;
        final StringBuilder buf = new StringBuilder(256);
        buf.append("<TT>").append(opcode.getName()).append("</TT></TD><TD>");
        if ((opcode == InstructionOpCodes.TABLESWITCH) || (opcode == InstructionOpCodes.LOOKUPSWITCH)) {
            final int remainder = bytes.getIndex() % 4;
            no_pad_bytes = (remainder == 0) ? 0 : 4 - remainder;
            for (int i = 0; i < no_pad_bytes; i++) {
                bytes.readByte();
            }
            default_offset = bytes.readInt();
        }
        switch (opcode) {
            case TABLESWITCH:
                low = bytes.readInt();
                high = bytes.readInt();
                offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
                default_offset += offset;
                buf.append("<TABLE BORDER=1><TR>");
                jump_table = new int[high - low + 1];
                for (int i = 0; i < jump_table.length; i++) {
                    jump_table[i] = offset + bytes.readInt();
                    buf.append("<TH>").append(low + i).append("</TH>");
                }
                buf.append("<TH>default</TH></TR>\n<TR>");
                for (final int element : jump_table) {
                    buf.append("<TD><A HREF=\"#code").append(method_number).append("@").append(element).append("\">").append(element).append("</A></TD>");
                }
                buf.append("<TD><A HREF=\"#code").append(method_number).append("@").append(default_offset).append("\">").append(default_offset).append("</A></TD></TR>\n</TABLE>\n");
                break;
            case LOOKUPSWITCH:
                final int npairs = bytes.readInt();
                offset = bytes.getIndex() - 8 - no_pad_bytes - 1;
                jump_table = new int[npairs];
                default_offset += offset;
                buf.append("<TABLE BORDER=1><TR>");
                for (int i = 0; i < npairs; i++) {
                    final int match = bytes.readInt();
                    jump_table[i] = offset + bytes.readInt();
                    buf.append("<TH>").append(match).append("</TH>");
                }
                buf.append("<TH>default</TH></TR>\n<TR>");
                for (int i = 0; i < npairs; i++) {
                    buf.append("<TD><A HREF=\"#code").append(method_number).append("@").append(jump_table[i]).append("\">").append(jump_table[i]).append("</A></TD>");
                }
                buf.append("<TD><A HREF=\"#code").append(method_number).append("@").append(default_offset).append("\">").append(default_offset).append("</A></TD></TR>\n</TABLE>\n");
                break;
            case GOTO:
            case IFEQ:
            case IFGE:
            case IFGT:
            case IFLE:
            case IFLT:
            case IFNE:
            case IFNONNULL:
            case IFNULL:
            case IF_ACMPEQ:
            case IF_ACMPNE:
            case IF_ICMPEQ:
            case IF_ICMPGE:
            case IF_ICMPGT:
            case IF_ICMPLE:
            case IF_ICMPLT:
            case IF_ICMPNE:
            case JSR:
                index = bytes.getIndex() + bytes.readShort() - 1;
                buf.append("<A HREF=\"#code").append(method_number).append("@").append(index).append("\">").append(index).append("</A>");
                break;
            case GOTO_W:
            case JSR_W:
                final int windex = bytes.getIndex() + bytes.readInt() - 1;
                buf.append("<A HREF=\"#code").append(method_number).append("@").append(windex).append("\">").append(windex).append("</A>");
                break;
            case ALOAD:
            case ASTORE:
            case DLOAD:
            case DSTORE:
            case FLOAD:
            case FSTORE:
            case ILOAD:
            case ISTORE:
            case LLOAD:
            case LSTORE:
            case RET:
                if (wide) {
                    vindex = bytes.readShort();
                    wide = false;
                } else {
                    vindex = bytes.readUnsignedByte();
                }
                buf.append("%").append(vindex);
                break;
            case WIDE:
                wide = true;
                buf.append("(wide)");
                break;
            case NEWARRAY:
                buf.append("<FONT COLOR=\"#00FF00\">").append(Const.getTypeName(bytes.readByte())).append("</FONT>");
                break;
            case GETFIELD:
            case GETSTATIC:
            case PUTFIELD:
            case PUTSTATIC:
                index = bytes.readShort();
                final ConstantFieldref c1 = (ConstantFieldref) constantPool.getConstant(index, ClassFileConstants.CONSTANT_Fieldref);
                class_index = c1.getClassIndex();
                name = constantPool.getConstantString(class_index, ClassFileConstants.CONSTANT_Class);
                name = Utility.compactClassName(name, false);
                index = c1.getNameAndTypeIndex();
                final String field_name = constantPool.constantToString(index, ClassFileConstants.CONSTANT_NameAndType);
                if (name.equals(className)) {
                    buf.append("<A HREF=\"").append(className).append("_methods.html#field").append(field_name).append("\" TARGET=Methods>").append(field_name).append("</A>\n");
                } else {
                    buf.append(constantHtml.referenceConstant(class_index)).append(".").append(field_name);
                }
                break;
            case CHECKCAST:
            case INSTANCEOF:
            case NEW:
                index = bytes.readShort();
                buf.append(constantHtml.referenceConstant(index));
                break;
            case INVOKESPECIAL:
            case INVOKESTATIC:
            case INVOKEVIRTUAL:
            case INVOKEINTERFACE:
            case INVOKEDYNAMIC:
                final int m_index = bytes.readShort();
                String str;
                if (opcode == InstructionOpCodes.INVOKEINTERFACE) {
                    bytes.readUnsignedByte();
                    bytes.readUnsignedByte();
                    final ConstantInterfaceMethodref c = (ConstantInterfaceMethodref) constantPool.getConstant(m_index, ClassFileConstants.CONSTANT_InterfaceMethodref);
                    class_index = c.getClassIndex();
                    index = c.getNameAndTypeIndex();
                    name = Class2HTML.referenceClass(class_index);
                } else if (opcode == InstructionOpCodes.INVOKEDYNAMIC) {
                    bytes.readUnsignedByte();
                    bytes.readUnsignedByte();
                    final ConstantInvokeDynamic c = (ConstantInvokeDynamic) constantPool.getConstant(m_index, ClassFileConstants.CONSTANT_InvokeDynamic);
                    index = c.getNameAndTypeIndex();
                    name = "#" + c.getBootstrapMethodAttrIndex();
                } else {
                    final ConstantMethodref c = (ConstantMethodref) constantPool.getConstant(m_index, ClassFileConstants.CONSTANT_Methodref);
                    class_index = c.getClassIndex();
                    index = c.getNameAndTypeIndex();
                    name = Class2HTML.referenceClass(class_index);
                }
                str = Class2HTML.toHTML(constantPool.constantToString(constantPool.getConstant(index, ClassFileConstants.CONSTANT_NameAndType)));
                final ConstantNameAndType c2 = (ConstantNameAndType) constantPool.getConstant(index, ClassFileConstants.CONSTANT_NameAndType);
                signature = constantPool.constantToString(c2.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
                final String[] args = Utility.methodSignatureArgumentTypes(signature, false);
                final String type = Utility.methodSignatureReturnType(signature, false);
                buf.append(name).append(".<A HREF=\"").append(className).append("_cp.html#cp").append(m_index).append("\" TARGET=ConstantPool>").append(str).append("</A>").append("(");
                for (int i = 0; i < args.length; i++) {
                    buf.append(Class2HTML.referenceType(args[i]));
                    if (i < args.length - 1) {
                        buf.append(", ");
                    }
                }
                buf.append("):").append(Class2HTML.referenceType(type));
                break;
            case LDC_W:
            case LDC2_W:
                index = bytes.readShort();
                buf.append("<A HREF=\"").append(className).append("_cp.html#cp").append(index).append("\" TARGET=\"ConstantPool\">").append(Class2HTML.toHTML(constantPool.constantToString(index, constantPool.getConstant(index).getTag()))).append("</a>");
                break;
            case LDC:
                index = bytes.readUnsignedByte();
                buf.append("<A HREF=\"").append(className).append("_cp.html#cp").append(index).append("\" TARGET=\"ConstantPool\">").append(Class2HTML.toHTML(constantPool.constantToString(index, constantPool.getConstant(index).getTag()))).append("</a>");
                break;
            case ANEWARRAY:
                index = bytes.readShort();
                buf.append(constantHtml.referenceConstant(index));
                break;
            case MULTIANEWARRAY:
                index = bytes.readShort();
                final int dimensions = bytes.readByte();
                buf.append(constantHtml.referenceConstant(index)).append(":").append(dimensions).append("-dimensional");
                break;
            case IINC:
                if (wide) {
                    vindex = bytes.readShort();
                    constant = bytes.readShort();
                    wide = false;
                } else {
                    vindex = bytes.readUnsignedByte();
                    constant = bytes.readByte();
                }
                buf.append("%").append(vindex).append(" ").append(constant);
                break;
            default:
                if (opcode.getNumberOfOperands() > 0) {
                    for (int i = 0; i < opcode.getTypeOfOperands().length; i++) {
                        switch (opcode.getTypeOfOperands()[i]) {
                            case Const.T_BYTE:
                                buf.append(bytes.readUnsignedByte());
                                break;
                            case Const.T_SHORT:
                                buf.append(bytes.readShort());
                                break;
                            case Const.T_INT:
                                buf.append(bytes.readInt());
                                break;
                            default:
                                throw new IllegalStateException("Unreachable default case reached! " + opcode.getTypeOfOperands()[i]);
                        }
                        buf.append("&nbsp;");
                    }
                }
        }
        buf.append("</TD>");
        return buf.toString();
    }

    private void findGotos(final ByteSequence bytes, final Code code) throws IOException {
        int index;
        gotoSet = new BitSet(bytes.available());
        InstructionOpCodes opcode;
        if (code != null) {
            final CodeException[] ce = code.getExceptionTable();
            for (final CodeException cex : ce) {
                gotoSet.set(cex.getStartPC());
                gotoSet.set(cex.getEndPC());
                gotoSet.set(cex.getHandlerPC());
            }
            final Attribute[] attributes = code.getAttributes();
            for (final Attribute attribute : attributes) {
                if (attribute.getTag() == ClassFileAttributes.ATTR_LOCAL_VARIABLE_TABLE) {
                    final LocalVariable[] vars = ((LocalVariableTable) attribute).getLocalVariableTable();
                    for (final LocalVariable var : vars) {
                        final int start = var.getStartPC();
                        final int end = start + var.getLength();
                        gotoSet.set(start);
                        gotoSet.set(end);
                    }
                    break;
                }
            }
        }
        for (; bytes.available() > 0;) {
            opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
            switch (opcode) {
                case TABLESWITCH:
                case LOOKUPSWITCH:
                    final int remainder = bytes.getIndex() % 4;
                    final int no_pad_bytes = (remainder == 0) ? 0 : 4 - remainder;
                    int default_offset;
                    int offset;
                    for (int j = 0; j < no_pad_bytes; j++) {
                        bytes.readByte();
                    }
                    default_offset = bytes.readInt();
                    if (opcode == InstructionOpCodes.TABLESWITCH) {
                        final int low = bytes.readInt();
                        final int high = bytes.readInt();
                        offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
                        default_offset += offset;
                        gotoSet.set(default_offset);
                        for (int j = 0; j < (high - low + 1); j++) {
                            index = offset + bytes.readInt();
                            gotoSet.set(index);
                        }
                    } else {
                        final int npairs = bytes.readInt();
                        offset = bytes.getIndex() - 8 - no_pad_bytes - 1;
                        default_offset += offset;
                        gotoSet.set(default_offset);
                        for (int j = 0; j < npairs; j++) {
                            bytes.readInt();
                            index = offset + bytes.readInt();
                            gotoSet.set(index);
                        }
                    }
                    break;
                case GOTO:
                case IFEQ:
                case IFGE:
                case IFGT:
                case IFLE:
                case IFLT:
                case IFNE:
                case IFNONNULL:
                case IFNULL:
                case IF_ACMPEQ:
                case IF_ACMPNE:
                case IF_ICMPEQ:
                case IF_ICMPGE:
                case IF_ICMPGT:
                case IF_ICMPLE:
                case IF_ICMPLT:
                case IF_ICMPNE:
                case JSR:
                    index = bytes.getIndex() + bytes.readShort() - 1;
                    gotoSet.set(index);
                    break;
                case GOTO_W:
                case JSR_W:
                    index = bytes.getIndex() + bytes.readInt() - 1;
                    gotoSet.set(index);
                    break;
                default:
                    bytes.unreadByte();
                    codeToHTML(bytes, 0);
            }
        }
    }

    private void writeMethod(final Method method, final int method_number) throws IOException {
        final String signature = method.getSignature();
        final String[] args = Utility.methodSignatureArgumentTypes(signature, false);
        final String type = Utility.methodSignatureReturnType(signature, false);
        final String name = method.getName();
        final String html_name = Class2HTML.toHTML(name);
        String access = Utility.accessToString(method.getAccessFlags());
        access = Utility.replace(access, " ", "&nbsp;");
        final Attribute[] attributes = method.getAttributes();
        file.print("<P><B><FONT COLOR=\"#FF0000\">" + access + "</FONT>&nbsp;" + "<A NAME=method" + method_number + ">" + Class2HTML.referenceType(type) + "</A>&nbsp<A HREF=\"" + className + "_methods.html#method" + method_number + "\" TARGET=Methods>" + html_name + "</A>(");
        for (int i = 0; i < args.length; i++) {
            file.print(Class2HTML.referenceType(args[i]));
            if (i < args.length - 1) {
                file.print(",&nbsp;");
            }
        }
        file.println(")</B></P>");
        Code c = null;
        byte[] code = null;
        if (attributes.length > 0) {
            file.print("<H4>Attributes</H4><UL>\n");
            for (int i = 0; i < attributes.length; i++) {
                ClassFileAttributes tag = attributes[i].getTag();
                if (tag != ClassFileAttributes.ATTR_UNKNOWN) {
                    file.print("<LI><A HREF=\"" + className + "_attributes.html#method" + method_number + "@" + i + "\" TARGET=Attributes>" + tag.getName() + "</A></LI>\n");
                } else {
                    file.print("<LI>" + attributes[i] + "</LI>");
                }
                if (tag == ClassFileAttributes.ATTR_CODE) {
                    c = (Code) attributes[i];
                    final Attribute[] attributes2 = c.getAttributes();
                    code = c.getCode();
                    file.print("<UL>");
                    for (int j = 0; j < attributes2.length; j++) {
                        tag = attributes2[j].getTag();
                        file.print("<LI><A HREF=\"" + className + "_attributes.html#" + "method" + method_number + "@" + i + "@" + j + "\" TARGET=Attributes>" + tag.getName() + "</A></LI>\n");
                    }
                    file.print("</UL>");
                }
            }
            file.println("</UL>");
        }
        if (code != null) {
            try (ByteSequence stream = new ByteSequence(code)) {
                stream.mark(stream.available());
                findGotos(stream, c);
                stream.reset();
                file.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Byte<BR>offset</TH>" + "<TH ALIGN=LEFT>Instruction</TH><TH ALIGN=LEFT>Argument</TH>");
                for (; stream.available() > 0;) {
                    final int offset = stream.getIndex();
                    final String str = codeToHTML(stream, method_number);
                    String anchor = "";
                    if (gotoSet.get(offset)) {
                        anchor = "<A NAME=code" + method_number + "@" + offset + "></A>";
                    }
                    String anchor2;
                    if (stream.getIndex() == code.length) {
                        anchor2 = "<A NAME=code" + method_number + "@" + code.length + ">" + offset + "</A>";
                    } else {
                        anchor2 = "" + offset;
                    }
                    file.println("<TR VALIGN=TOP><TD>" + anchor2 + "</TD><TD>" + anchor + str + "</TR>");
                }
            }
            file.println("<TR><TD> </A></TD></TR>");
            file.println("</TABLE>");
        }
    }
}
