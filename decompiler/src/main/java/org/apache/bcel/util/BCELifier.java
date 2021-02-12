
package org.apache.bcel.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import org.apache.bcel.Const;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.constant.ConstantValue;
import org.apache.bcel.enums.ClassAccessFlags;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.ArrayType;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.generic.gen.MethodGen;

public class BCELifier extends org.apache.bcel.classfile.EmptyVisitor {

    public enum FLAGS {
        UNKNOWN, CLASS, METHOD,
    }

    // The base package name for imports; assumes Const is at the top level
    // N.B we use the class so renames will be detected by the compiler/IDE
    private static final String BASE_PACKAGE = Const.class.getPackage().getName();
    private static final String CONSTANT_PREFIX = Const.class.getSimpleName() + ".";

    private final JavaClass _clazz;
    private final PrintWriter _out;
    private final ConstantPoolGen _cp;

    public BCELifier(final JavaClass clazz, final OutputStream out) {
        _clazz = clazz;
        _out = new PrintWriter(out);
        _cp = new ConstantPoolGen(_clazz.getConstantPool());
    }

    private void printCreate() {
        _out.println("  public void create(OutputStream out) throws IOException {");
        final Field[] fields = _clazz.getFields();
        if (fields.length > 0) {
            _out.println("    createFields();");
        }
        final Method[] methods = _clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            _out.println("    createMethod_" + i + "();");
        }
        _out.println("    _cg.getJavaClass().dump(out);");
        _out.println("  }");
        _out.println();
    }

    private void printMain() {
        final String class_name = _clazz.getClassName().getName();
        _out.println("  public static void main(String[] args) throws Exception {");
        _out.println("    " + class_name + "Creator creator = new " + class_name + "Creator();");
        _out.println("    creator.create(new FileOutputStream(\"" + class_name + ".class\"));");
        _out.println("  }");
    }

    public void start() throws Throwable {
        visitJavaClass(_clazz);
        _out.flush();
    }

    @Override
    public void visitField(final Field field) {
        _out.println();
        _out.println("    field = new FieldGen(" + printFlags(field.getAccessFlags()) + ", " + printType(field.getSignature()) + ", \"" + field.getName() + "\", _cp);");
        final ConstantValue cv = field.getConstantValue();
        if (cv != null) {
            final String value = cv.toString();
            _out.println("    field.setInitValue(" + value + ")");
        }
        _out.println("    _cg.addField(field.getField());");
    }

    @Override
    public void visitJavaClass(final JavaClass clazz) {
        String class_name = clazz.getClassName().getName();
        final String super_name = clazz.getSuperClassName().getName();
        final String package_name = clazz.getPackageName();
        final String inter = Utility.printArray(clazz.getInterfaceNames(), false, true);
        if (!"".equals(package_name)) {
            class_name = class_name.substring(package_name.length() + 1);
            _out.println("package " + package_name + ";");
            _out.println();
        }
        _out.println("import " + BASE_PACKAGE + ".generic.*;");
        _out.println("import " + BASE_PACKAGE + ".classfile.*;");
        _out.println("import " + BASE_PACKAGE + ".*;");
        _out.println("import java.io.*;");
        _out.println();
        _out.println("public class " + class_name + "Creator {");
        _out.println("  private InstructionFactory _factory;");
        _out.println("  private ConstantPoolGen    _cp;");
        _out.println("  private ClassGen           _cg;");
        _out.println();
        _out.println("  public " + class_name + "Creator() {");
        _out.println("    _cg = new ClassGen(\"" + (("".equals(package_name)) ? class_name : package_name + "." + class_name) + "\", \"" + super_name + "\", " + "\"" + clazz.getSourceFileName() + "\", " + printFlags(clazz.getAccessFlags(), FLAGS.CLASS) + ", " + "new String[] { " + inter + " });");
        _out.println("    _cg.setMajor(" + clazz.getVersion().getMajor() + ");");
        _out.println("    _cg.setMinor(" + clazz.getVersion().getMinor() + ");");
        _out.println();
        _out.println("    _cp = _cg.getConstantPool();");
        _out.println("    _factory = new InstructionFactory(_cg, _cp);");
        _out.println("  }");
        _out.println();
        printCreate();
        final Field[] fields = clazz.getFields();
        if (fields.length > 0) {
            _out.println("  private void createFields() {");
            _out.println("    FieldGen field;");
            for (final Field field : fields) {
                field.accept(this);
            }
            _out.println("  }");
            _out.println();
        }
        final Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            _out.println("  private void createMethod_" + i + "() {");
            methods[i].accept(this);
            _out.println("  }");
            _out.println();
        }
        printMain();
        _out.println("}");
    }

    @Override
    public void visitMethod(final Method method) {
        final MethodGen mg = new MethodGen(method, _clazz.getClassName(), _cp);
        _out.println("    InstructionList il = new InstructionList();");
        _out.println("    MethodGen method = new MethodGen(" + printFlags(method.getAccessFlags(), FLAGS.METHOD) + ", " + printType(mg.getReturnType()) + ", " + printArgumentTypes(mg.getArgumentTypes()) + ", " + "new String[] { " + Utility.printArray(mg.getArgumentNames(), false, true) + " }, \"" + method.getName() + "\", \"" + _clazz.getClassName() + "\", il, _cp);");
        _out.println();
        final BCELFactory factory = new BCELFactory(mg, _out);
        try {
            factory.start();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _out.println("    method.setMaxStack();");
        _out.println("    method.setMaxLocals();");
        _out.println("    _cg.addMethod(method.getMethod());");
        _out.println("    il.dispose();");
    }

    // Needs to be accessible from unit test code
    static JavaClass getJavaClass(final String name) throws ClassNotFoundException, IOException {
        JavaClass java_class;
        if ((java_class = Repository.lookupClass(name)) == null) {
            java_class = new ClassParser(name).parse(); // May throw IOException
        }
        return java_class;
    }

    public static void main(final String[] argv) throws Throwable {
        if (argv.length != 1) {
            System.out.println("Usage: BCELifier classname");
            System.out.println("\tThe class must exist on the classpath");
            return;
        }
        final JavaClass java_class = getJavaClass(argv[0]);
        final BCELifier bcelifier = new BCELifier(java_class, System.out);
        bcelifier.start();
    }

    static String printArgumentTypes(final Type[] arg_types) {
        if (arg_types.length == 0) {
            return "Type.NO_ARGS";
        }
        final StringBuilder args = new StringBuilder();
        for (int i = 0; i < arg_types.length; i++) {
            args.append(printType(arg_types[i]));
            if (i < arg_types.length - 1) {
                args.append(", ");
            }
        }
        return "new Type[] { " + args.toString() + " }";
    }

    static String printFlags(final int flags) {
        return printFlags(flags, FLAGS.UNKNOWN);
    }

    public static String printFlags(final int flags, final FLAGS location) {
        if (flags == 0) {
            return "0";
        }
        final StringBuilder buf = new StringBuilder();
        for (int i = 0, pow = 1; pow <= Const.MAX_ACC_FLAG_I; i++) {
            if ((flags & pow) != 0) {
                if ((pow == ClassAccessFlags.ACC_SYNCHRONIZED.getFlag()) && (location == FLAGS.CLASS)) {
                    buf.append(CONSTANT_PREFIX + "ACC_SUPER | ");
                } else if ((pow == ClassAccessFlags.ACC_VOLATILE.getFlag()) && (location == FLAGS.METHOD)) {
                    buf.append(CONSTANT_PREFIX + "ACC_BRIDGE | ");
                } else if ((pow == ClassAccessFlags.ACC_TRANSIENT.getFlag()) && (location == FLAGS.METHOD)) {
                    buf.append(CONSTANT_PREFIX + "ACC_VARARGS | ");
                } else {
                    if (i < Const.ACCESS_NAMES_LENGTH) {
                        buf.append(CONSTANT_PREFIX + "ACC_").append(Const.getAccessName(i).toUpperCase(Locale.ENGLISH)).append(" | ");
                    } else {
                        buf.append(String.format(CONSTANT_PREFIX + "ACC_BIT %x | ", pow));
                    }
                }
            }
            pow <<= 1;
        }
        final String str = buf.toString();
        return str.substring(0, str.length() - 3);
    }

    static String printType(final String signature) {
        final Type type = Type.getType(signature);
        final byte t = type.getType();
        if (t <= Const.T_VOID) {
            return "Type." + Const.getTypeName(t).toUpperCase(Locale.ENGLISH);
        } else if (type.toString().equals("java.lang.String")) {
            return "Type.STRING";
        } else if (type.toString().equals("java.lang.Object")) {
            return "Type.OBJECT";
        } else if (type.toString().equals("java.lang.StringBuffer")) {
            return "Type.STRINGBUFFER";
        } else if (type instanceof ArrayType) {
            final ArrayType at = (ArrayType) type;
            return "new ArrayType(" + printType(at.getBasicType()) + ", " + at.getDimensions() + ")";
        } else {
            return "new ObjectType(\"" + Utility.signatureToString(signature, false) + "\")";
        }
    }

    static String printType(final Type type) {
        return printType(type.getSignature());
    }
}
