
package org.apache.bcel.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.bcel.Const;
import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;

public class Class2HTML implements Constants {

    private static String class_package; // name of package, unclean to make it static, but ...
    private static String class_name; // name of current class, dito
    private static ConstantPool constant_pool;
    private static final Set<String> basic_types = new HashSet<>();
    static {
        basic_types.add("int");
        basic_types.add("short");
        basic_types.add("boolean");
        basic_types.add("void");
        basic_types.add("char");
        basic_types.add("byte");
        basic_types.add("long");
        basic_types.add("double");
        basic_types.add("float");
    }
    private final JavaClass java_class; // current class object

    private final String dir;

    public Class2HTML(final JavaClass java_class, final String dir) throws IOException {
        final Method[] methods = java_class.getMethods();
        this.java_class = java_class;
        this.dir = dir;
        class_name = java_class.getClassName().getName(); // Remember full name
        constant_pool = java_class.getConstantPool();
        // Get package name by tacking off everything after the last `.'
        final int index = class_name.lastIndexOf('.');
        if (index > -1) {
            class_package = class_name.substring(0, index);
        } else {
            class_package = ""; // default package
        }
        final ConstantHTML constant_html = new ConstantHTML(dir, class_name, class_package, methods, constant_pool);

        final AttributeHTML attribute_html = new AttributeHTML(dir, class_name, constant_pool, constant_html);
        new MethodHTML(dir, class_name, methods, java_class.getFields(), constant_html, attribute_html);
        // Write main file (with frames, yuk)
        writeMainHTML(attribute_html);
        new CodeHTML(dir, class_name, methods, constant_pool, constant_html);
        attribute_html.close();
    }

    private void writeMainHTML(final AttributeHTML attribute_html) throws IOException {
        try (PrintWriter file = new PrintWriter(new FileOutputStream(dir + class_name + ".html"))) {
            file.println("<HTML>\n" + "<HEAD><TITLE>Documentation for " + class_name + "</TITLE>" + "</HEAD>\n" + "<FRAMESET BORDER=1 cols=\"30%,*\">\n" + "<FRAMESET BORDER=1 rows=\"80%,*\">\n" + "<FRAME NAME=\"ConstantPool\" SRC=\"" + class_name + "_cp.html" + "\"\n MARGINWIDTH=\"0\" " + "MARGINHEIGHT=\"0\" FRAMEBORDER=\"1\" SCROLLING=\"AUTO\">\n" + "<FRAME NAME=\"Attributes\" SRC=\"" + class_name + "_attributes.html" + "\"\n MARGINWIDTH=\"0\" "
                    + "MARGINHEIGHT=\"0\" FRAMEBORDER=\"1\" SCROLLING=\"AUTO\">\n" + "</FRAMESET>\n" + "<FRAMESET BORDER=1 rows=\"80%,*\">\n" + "<FRAME NAME=\"Code\" SRC=\"" + class_name + "_code.html\"\n MARGINWIDTH=0 " + "MARGINHEIGHT=0 FRAMEBORDER=1 SCROLLING=\"AUTO\">\n" + "<FRAME NAME=\"Methods\" SRC=\"" + class_name + "_methods.html\"\n MARGINWIDTH=0 " + "MARGINHEIGHT=0 FRAMEBORDER=1 SCROLLING=\"AUTO\">\n" + "</FRAMESET></FRAMESET></HTML>");
        }
        final Attribute[] attributes = java_class.getAttributes();
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], "class" + i);
        }
    }

    public static void main(final String[] argv) throws IOException {
        final String[] file_name = new String[argv.length];
        int files = 0;
        ClassParser parser = null;
        JavaClass java_class = null;
        String zip_file = null;
        final char sep = File.separatorChar;
        String dir = "." + sep; // Where to store HTML files

        for (int i = 0; i < argv.length; i++) {
            if (argv[i].charAt(0) == '-') { // command line switch
                if (argv[i].equals("-d")) { // Specify target directory, default '.'
                    dir = argv[++i];
                    if (!dir.endsWith("" + sep)) {
                        dir = dir + sep;
                    }
                    final File store = new File(dir);
                    if (!store.isDirectory()) {
                        final boolean created = store.mkdirs(); // Create target directory if necessary
                        if (!created) {
                            if (!store.isDirectory()) {
                                System.out.println("Tried to create the directory " + dir + " but failed");
                            }
                        }
                    }
                } else if (argv[i].equals("-zip")) {
                    zip_file = argv[++i];
                } else {
                    System.out.println("Unknown option " + argv[i]);
                }
            } else {
                file_name[files++] = argv[i];
            }
        }
        if (files == 0) {
            System.err.println("Class2HTML: No input files specified.");
        } else { // Loop through files ...
            for (int i = 0; i < files; i++) {
                System.out.print("Processing " + file_name[i] + "...");
                if (zip_file == null) {
                    parser = new ClassParser(file_name[i]); // Create parser object from file
                } else {
                    parser = new ClassParser(zip_file, file_name[i]); // Create parser object from zip file
                }
                java_class = parser.parse();
                new Class2HTML(java_class, dir);
                System.out.println("Done.");
            }
        }
    }

    static String referenceClass(final int index) {
        String str = constant_pool.getConstantString(index, Const.CONSTANT_Class);
        str = Utility.compactClassName(str);
        str = Utility.compactClassName(str, class_package + ".", true);
        return "<A HREF=\"" + class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + str + "</A>";
    }

    static String referenceType(final String type) {
        String short_type = Utility.compactClassName(type);
        short_type = Utility.compactClassName(short_type, class_package + ".", true);
        final int index = type.indexOf('['); // Type is an array?
        String base_type = type;
        if (index > -1) {
            base_type = type.substring(0, index); // Tack of the `['
        }
        // test for basic type
        if (basic_types.contains(base_type)) {
            return "<FONT COLOR=\"#00FF00\">" + type + "</FONT>";
        }
        return "<A HREF=\"" + base_type + ".html\" TARGET=_top>" + short_type + "</A>";
    }

    static String toHTML(final String str) {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch;
            switch (ch = str.charAt(i)) {
                case '<':
                    buf.append("&lt;");
                    break;
                case '>':
                    buf.append("&gt;");
                    break;
                case '\n':
                    buf.append("\\n");
                    break;
                case '\r':
                    buf.append("\\r");
                    break;
                default:
                    buf.append(ch);
            }
        }
        return buf.toString();
    }
}
