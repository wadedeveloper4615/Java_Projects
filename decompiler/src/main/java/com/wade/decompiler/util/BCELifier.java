package com.wade.decompiler.util;

public class BCELifier {// extends EmptyVisitor {
//    // The base package name for imports; assumes Const is at the top level
//    // N.B we use the class so renames will be detected by the compiler/IDE
//    private static String BASE_PACKAGE = Const.class.getPackage().getName();
//
//    private static String CONSTANT_PREFIX = Const.class.getSimpleName() + ".";
//
//    public enum FLAGS {
//        UNKNOWN, CLASS, METHOD,
//    }
//
//    private JavaClass _clazz;
//    private PrintWriter _out;
//    private ConstantPoolGen _cp;
//
//    public BCELifier(JavaClass clazz, OutputStream out) {
//        _clazz = clazz;
//        _out = new PrintWriter(out);
//        _cp = new ConstantPoolGen(_clazz.getConstantPool());
//    }
//
//    private void printCreate() {
//        _out.println("  public void create(OutputStream out) throws IOException {");
//        Field[] fields = _clazz.getFields();
//        if (fields.length > 0) {
//            _out.println("    createFields();");
//        }
//        Method[] methods = _clazz.getMethods();
//        for (int i = 0; i < methods.length; i++) {
//            _out.println("    createMethod_" + i + "();");
//        }
//        _out.println("    _cg.getJavaClass().dump(out);");
//        _out.println("  }");
//        _out.println();
//    }
//
//    private void printMain() {
//        String class_name = _clazz.getClassName();
//        _out.println("  public static void main(String[] args) throws Exception {");
//        _out.println("    " + class_name + "Creator creator = new " + class_name + "Creator();");
//        _out.println("    creator.create(new FileOutputStream(\"" + class_name + ".class\"));");
//        _out.println("  }");
//    }
//
//    public void start() {
//        visitJavaClass(_clazz);
//        _out.flush();
//    }
//
//    @Override
//    public void visitField(Field field) {
//        _out.println();
//        _out.println("    field = new FieldGen(" + printFlags(field.getFlags()) + ", " + printType(field.getSignature()) + ", \"" + field.getName() + "\", _cp);");
//        ConstantValue cv = field.getConstantValue();
//        if (cv != null) {
//            String value = cv.toString();
//            _out.println("    field.setInitValue(" + value + ")");
//        }
//        _out.println("    _cg.addField(field.getField());");
//    }
//
//    @Override
//    public void visitJavaClass(JavaClass clazz) {
//        String class_name = clazz.getClassName();
//        String super_name = clazz.getSuperclassName();
//        String package_name = clazz.getPackageName();
//        String inter = Utility.printArray(clazz.getInterfaceNames(), false, true);
//        if (!"".equals(package_name)) {
//            class_name = class_name.substring(package_name.length() + 1);
//            _out.println("package " + package_name + ";");
//            _out.println();
//        }
//        _out.println("import " + BASE_PACKAGE + ".generic.*;");
//        _out.println("import " + BASE_PACKAGE + ".classfile.*;");
//        _out.println("import " + BASE_PACKAGE + ".*;");
//        _out.println("import java.io.*;");
//        _out.println();
//        _out.println("public class " + class_name + "Creator {");
//        _out.println("  private InstructionFactory _factory;");
//        _out.println("  private ConstantPoolGen    _cp;");
//        _out.println("  private ClassGen           _cg;");
//        _out.println();
//        _out.println("  public " + class_name + "Creator() {");
//        _out.println("    _cg = new ClassGen(\"" + (("".equals(package_name)) ? class_name : package_name + "." + class_name) + "\", \"" + super_name + "\", " + "\"" + clazz.getSourceFileName() + "\", " + printFlags(clazz.getFlags(), FLAGS.CLASS) + ", " + "new String[] { " + inter + " });");
//        _out.println("    _cg.setMajor(" + clazz.getVersion().getMajor() + ");");
//        _out.println("    _cg.setMinor(" + clazz.getVersion().getMinor() + ");");
//        _out.println();
//        _out.println("    _cp = _cg.getConstantPool();");
//        _out.println("    _factory = new InstructionFactory(_cg, _cp);");
//        _out.println("  }");
//        _out.println();
//        printCreate();
//        Field[] fields = clazz.getFields();
//        if (fields.length > 0) {
//            _out.println("  private void createFields() {");
//            _out.println("    FieldGen field;");
//            for (Field field : fields) {
//                field.accept(this);
//            }
//            _out.println("  }");
//            _out.println();
//        }
//        Method[] methods = clazz.getMethods();
//        for (int i = 0; i < methods.length; i++) {
//            _out.println("  private void createMethod_" + i + "() {");
//            methods[i].accept(this);
//            _out.println("  }");
//            _out.println();
//        }
//        printMain();
//        _out.println("}");
//    }
//
//    @Override
//    public void visitMethod(Method method) {
//        MethodGen mg = new MethodGen(method, _clazz.getClassName(), _cp);
//        _out.println("    InstructionList il = new InstructionList();");
//        _out.println("    MethodGen method = new MethodGen(" + printFlags(method.getFlags(), FLAGS.METHOD) + ", " + printType(mg.getReturnType()) + ", " + printArgumentTypes(mg.getArgumentTypes()) + ", " + "new String[] { " + Utility.printArray(mg.getArgumentNames(), false, true) + " }, \"" + method.getName() + "\", \"" + _clazz.getClassName() + "\", il, _cp);");
//        _out.println();
//        BCELFactory factory = new BCELFactory(mg, _out);
//        factory.start();
//        _out.println("    method.setMaxStack();");
//        _out.println("    method.setMaxLocals();");
//        _out.println("    _cg.addMethod(method.getMethod());");
//        _out.println("    il.dispose();");
//    }
//
//    // Needs to be accessible from unit test code
//    static JavaClass getJavaClass(String name) throws ClassNotFoundException, IOException {
//        JavaClass java_class;
//        if ((java_class = Repository.lookupClass(name)) == null) {
//            java_class = new ClassParser(name).parse(); // May throw IOException
//        }
//        return java_class;
//    }
//
//    public static void main(String[] argv) throws Exception {
//        if (argv.length != 1) {
//            System.out.println("Usage: BCELifier classname");
//            System.out.println("\tThe class must exist on the classpath");
//            return;
//        }
//        JavaClass java_class = getJavaClass(argv[0]);
//        BCELifier bcelifier = new BCELifier(java_class, System.out);
//        bcelifier.start();
//    }
//
//    static String printArgumentTypes(Type[] arg_types) {
//        if (arg_types.length == 0) {
//            return "Type.NO_ARGS";
//        }
//        StringBuilder args = new StringBuilder();
//        for (int i = 0; i < arg_types.length; i++) {
//            args.append(printType(arg_types[i]));
//            if (i < arg_types.length - 1) {
//                args.append(", ");
//            }
//        }
//        return "new Type[] { " + args.toString() + " }";
//    }
//
//    static String printFlags(int flags) {
//        return printFlags(flags, FLAGS.UNKNOWN);
//    }
//
//    public static String printFlags(int flags, FLAGS location) {
//        if (flags == 0) {
//            return "0";
//        }
//        StringBuilder buf = new StringBuilder();
//        for (int i = 0, pow = 1; pow <= Const.MAX_ACC_FLAG_I; i++) {
//            if ((flags & pow) != 0) {
//                if ((pow == ClassAccessFlags.ACC_SYNCHRONIZED.getFlag()) && (location == FLAGS.CLASS)) {
//                    buf.append(CONSTANT_PREFIX + "ACC_SUPER | ");
//                } else if ((pow == ClassAccessFlags.ACC_VOLATILE.getFlag()) && (location == FLAGS.METHOD)) {
//                    buf.append(CONSTANT_PREFIX + "ACC_BRIDGE | ");
//                } else if ((pow == ClassAccessFlags.ACC_TRANSIENT.getFlag()) && (location == FLAGS.METHOD)) {
//                    buf.append(CONSTANT_PREFIX + "ACC_VARARGS | ");
//                } else if (i < Const.ACCESS_NAMES_LENGTH) {
//                    buf.append(CONSTANT_PREFIX + "ACC_").append(Const.getAccessName(i).toUpperCase(Locale.ENGLISH)).append(" | ");
//                } else {
//                    buf.append(String.format(CONSTANT_PREFIX + "ACC_BIT %x | ", pow));
//                }
//            }
//            pow <<= 1;
//        }
//        String str = buf.toString();
//        return str.substring(0, str.length() - 3);
//    }
//
//    static String printType(String signature) {
//        Type type = Type.getType(signature);
//        byte t = type.getType();
//        if (t <= Const.T_VOID) {
//            return "Type." + Const.getTypeName(t).toUpperCase(Locale.ENGLISH);
//        } else if (type.toString().equals("java.lang.String")) {
//            return "Type.STRING";
//        } else if (type.toString().equals("java.lang.Object")) {
//            return "Type.OBJECT";
//        } else if (type.toString().equals("java.lang.StringBuffer")) {
//            return "Type.STRINGBUFFER";
//        } else if (type instanceof ArrayType) {
//            ArrayType at = (ArrayType) type;
//            return "new ArrayType(" + printType(at.getBasicType()) + ", " + at.getDimensions() + ")";
//        } else {
//            return "new ObjectType(\"" + Utility.signatureToString(signature, false) + "\")";
//        }
//    }
//
//    static String printType(Type type) {
//        return printType(type.getSignature());
//    }
}
