package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.Const;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

@SuppressWarnings("deprecation")
public abstract class Attribute implements Cloneable, Node {
    private static boolean debug = Boolean.getBoolean(Attribute.class.getCanonicalName() + ".debug"); // Debugging on/off
    private static Map<String, Object> readers = new HashMap<>();
    protected int name_index;
    protected int length;
    protected ClassFileAttributes tag;
    protected ConstantPool constant_pool;

    protected Attribute(ClassFileAttributes tag, int name_index, int length, ConstantPool constant_pool) {
        this.tag = tag;
        this.name_index = name_index;
        this.length = length;
        this.constant_pool = constant_pool;
    }

    @Override
    public abstract void accept(Visitor v);

    @Override
    public Object clone() {
        Attribute attr = null;
        try {
            attr = (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Clone Not Supported");
        }
        return attr;
    }

    public abstract Attribute copy(ConstantPool _constant_pool);

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(name_index);
        file.writeInt(length);
    }

    public ConstantPool getConstantPool() {
        return constant_pool;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getNameIndex() {
        return name_index;
    }

    public ClassFileAttributes getTag() {
        return tag;
    }

    public void setConstantPool(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setNameIndex(int name_index) {
        this.name_index = name_index;
    }

    @Override
    public String toString() {
        return Const.getAttributeName(tag.getTag());
    }

    public static void addAttributeReader(String name, AttributeReader r) {
        readers.put(name, r);
    }

    public static void addAttributeReader(String name, UnknownAttributeReader r) {
        readers.put(name, r);
    }

    protected static void println(String msg) {
        if (debug) {
            System.err.println(msg);
        }
    }

    public static Attribute readAttribute(DataInput file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        ClassFileAttributes tag = ClassFileAttributes.ATTR_UNKNOWN;
        int name_index = file.readUnsignedShort();
        String name = ((ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        int length = file.readInt();

        for (byte i = 0; i < Const.KNOWN_ATTRIBUTES; i++) {
            if (name.equals(Const.getAttributeName(i))) {
                tag = ClassFileAttributes.read(i);
                break;
            }
        }
        switch (tag) {
            case ATTR_UNKNOWN:
                Object r = readers.get(name);
                if (r instanceof UnknownAttributeReader) {
                    return ((UnknownAttributeReader) r).createAttribute(name_index, length, file, constant_pool);
                }
                return new Unknown(name_index, length, file, constant_pool);
            case ATTR_CONSTANT_VALUE:
                return new ConstantValue(name_index, length, file, constant_pool);
            case ATTR_SOURCE_FILE:
                return new SourceFile(name_index, length, file, constant_pool);
            case ATTR_CODE:
                return new Code(name_index, length, file, constant_pool);
            case ATTR_EXCEPTIONS:
                return new ExceptionTable(name_index, length, file, constant_pool);
            case ATTR_LINE_NUMBER_TABLE:
                return new LineNumberTable(name_index, length, file, constant_pool);
            case ATTR_LOCAL_VARIABLE_TABLE:
                return new LocalVariableTable(name_index, length, file, constant_pool);
            case ATTR_INNER_CLASSES:
                return new InnerClasses(name_index, length, file, constant_pool);
            case ATTR_SYNTHETIC:
                return new Synthetic(name_index, length, file, constant_pool);
            case ATTR_DEPRECATED:
                return new Deprecated(name_index, length, file, constant_pool);
            case ATTR_PMG:
                return new PMGClass(name_index, length, file, constant_pool);
            case ATTR_SIGNATURE:
                return new Signature(name_index, length, file, constant_pool);
            case ATTR_STACK_MAP:
                // old style stack map: unneeded for JDK5 and below;
                // illegal(?) for JDK6 and above. So just delete with a warning.
                println("Warning: Obsolete StackMap attribute ignored.");
                return new Unknown(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
                return new RuntimeVisibleAnnotations(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
                return new RuntimeInvisibleAnnotations(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeVisibleParameterAnnotations(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeInvisibleParameterAnnotations(name_index, length, file, constant_pool);
            case ATTR_ANNOTATION_DEFAULT:
                return new AnnotationDefault(name_index, length, file, constant_pool);
            case ATTR_LOCAL_VARIABLE_TYPE_TABLE:
                return new LocalVariableTypeTable(name_index, length, file, constant_pool);
            case ATTR_ENCLOSING_METHOD:
                return new EnclosingMethod(name_index, length, file, constant_pool);
            case ATTR_STACK_MAP_TABLE:
                // read new style stack map: StackMapTable. The rest of the code
                // calls this a StackMap for historical reasons.
                return new StackMap(name_index, length, file, constant_pool);
            case ATTR_BOOTSTRAP_METHODS:
                return new BootstrapMethods(name_index, length, file, constant_pool);
            case ATTR_METHOD_PARAMETERS:
                return new MethodParameters(name_index, length, file, constant_pool);
            case ATTR_MODULE:
                return new Module(name_index, length, file, constant_pool);
            case ATTR_MODULE_PACKAGES:
                return new ModulePackages(name_index, length, file, constant_pool);
            case ATTR_MODULE_MAIN_CLASS:
                return new ModuleMainClass(name_index, length, file, constant_pool);
            case ATTR_NEST_HOST:
                return new NestHost(name_index, length, file, constant_pool);
            case ATTR_NEST_MEMBERS:
                return new NestMembers(name_index, length, file, constant_pool);
            default:
                // Never reached
                throw new IllegalStateException("Unrecognized attribute type tag parsed: " + tag);
        }
    }

    public static Attribute readAttribute(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        return readAttribute((DataInput) file, constant_pool);
    }

    public static void removeAttributeReader(String name) {
        readers.remove(name);
    }
}
