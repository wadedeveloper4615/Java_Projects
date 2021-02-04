package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.constantpool.Node;
import com.wade.app.constantpool.SourceFile;
import com.wade.app.exception.ClassFormatException;

public abstract class Attribute implements Node {
    private static final boolean debug = Boolean.getBoolean(Attribute.class.getCanonicalName() + ".debug");
    private static final Map<String, Object> readers = new HashMap<>();
    protected int name_index;
    protected int length;
    protected byte tag;
    protected ConstantPool constant_pool;

    protected Attribute(final byte tag, final int name_index, final int length, final ConstantPool constant_pool) {
        this.tag = tag;
        this.name_index = name_index;
        this.length = length;
        this.constant_pool = constant_pool;
    }

    public final ConstantPool getConstantPool() {
        return constant_pool;
    }

    public final int getLength() {
        return length;
    }

    public String getName() throws ClassFormatException {
        final ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public final int getNameIndex() {
        return name_index;
    }

    public final byte getTag() {
        return tag;
    }

    public final void setConstantPool(final ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    public final void setLength(final int length) {
        this.length = length;
    }

    public final void setNameIndex(final int name_index) {
        this.name_index = name_index;
    }

    public static void addAttributeReader(final String name, final AttributeReader r) {
        readers.put(name, r);
    }

    public static void addAttributeReader(final String name, final UnknownAttributeReader r) {
        readers.put(name, r);
    }

    protected static void println(final String msg) {
        if (debug) {
            System.err.println(msg);
        }
    }

    public static Attribute readAttribute(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        byte tag = Const.ATTR_UNKNOWN;
        final int name_index = file.readUnsignedShort();
        final ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, Const.CONSTANT_Utf8);
        final String name = c.getBytes();
        final int length = file.readInt();
        for (byte i = 0; i < Const.KNOWN_ATTRIBUTES; i++) {
            if (name.equals(Const.getAttributeName(i))) {
                tag = i;
                break;
            }
        }
        switch (tag) {
            case Const.ATTR_UNKNOWN:
                final Object r = readers.get(name);
                if (r instanceof UnknownAttributeReader) {
                    return ((UnknownAttributeReader) r).createAttribute(name_index, length, file, constant_pool);
                }
                return new Unknown(name_index, length, file, constant_pool);
            case Const.ATTR_CONSTANT_VALUE:
                return new ConstantValue(name_index, length, file, constant_pool);
            case Const.ATTR_SOURCE_FILE:
                return new SourceFile(name_index, length, file, constant_pool);
            case Const.ATTR_CODE:
                return new Code(name_index, length, file, constant_pool);
            case Const.ATTR_EXCEPTIONS:
                return new ExceptionTable(name_index, length, file, constant_pool);
            case Const.ATTR_LINE_NUMBER_TABLE:
                return new LineNumberTable(name_index, length, file, constant_pool);
            case Const.ATTR_LOCAL_VARIABLE_TABLE:
                return new LocalVariableTable(name_index, length, file, constant_pool);
            case Const.ATTR_INNER_CLASSES:
                return new InnerClasses(name_index, length, file, constant_pool);
            case Const.ATTR_SYNTHETIC:
                return new Synthetic(name_index, length, file, constant_pool);
            case Const.ATTR_DEPRECATED:
                return new com.wade.app.attribute.Deprecated(name_index, length, file, constant_pool);
            case Const.ATTR_PMG:
                return new PMGClass(name_index, length, file, constant_pool);
            case Const.ATTR_SIGNATURE:
                return new Signature(name_index, length, file, constant_pool);
            case Const.ATTR_STACK_MAP:
                // old style stack map: unneeded for JDK5 and below;
                // illegal(?) for JDK6 and above. So just delete with a warning.
                println("Warning: Obsolete StackMap attribute ignored.");
                return new Unknown(name_index, length, file, constant_pool);
            case Const.ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
                return new RuntimeVisibleAnnotations(name_index, length, file, constant_pool);
            case Const.ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
                return new RuntimeInvisibleAnnotations(name_index, length, file, constant_pool);
            case Const.ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeVisibleParameterAnnotations(name_index, length, file, constant_pool);
            case Const.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeInvisibleParameterAnnotations(name_index, length, file, constant_pool);
            case Const.ATTR_ANNOTATION_DEFAULT:
                return new AnnotationDefault(name_index, length, file, constant_pool);
            case Const.ATTR_LOCAL_VARIABLE_TYPE_TABLE:
                return new LocalVariableTypeTable(name_index, length, file, constant_pool);
            case Const.ATTR_ENCLOSING_METHOD:
                return new EnclosingMethod(name_index, length, file, constant_pool);
            case Const.ATTR_STACK_MAP_TABLE:
                // read new style stack map: StackMapTable. The rest of the code
                // calls this a StackMap for historical reasons.
                return new StackMap(name_index, length, file, constant_pool);
            case Const.ATTR_BOOTSTRAP_METHODS:
                return new BootstrapMethods(name_index, length, file, constant_pool);
            case Const.ATTR_METHOD_PARAMETERS:
                return new MethodParameters(name_index, length, file, constant_pool);
            case Const.ATTR_MODULE:
                return new com.wade.app.attribute.Module(name_index, length, file, constant_pool);
            case Const.ATTR_MODULE_PACKAGES:
                return new ModulePackages(name_index, length, file, constant_pool);
            case Const.ATTR_MODULE_MAIN_CLASS:
                return new ModuleMainClass(name_index, length, file, constant_pool);
            case Const.ATTR_NEST_HOST:
                return new NestHost(name_index, length, file, constant_pool);
            case Const.ATTR_NEST_MEMBERS:
                return new NestMembers(name_index, length, file, constant_pool);
            default:
                // Never reached
                throw new IllegalStateException("Unrecognized attribute type tag parsed: " + tag);
        }
    }

    public static void removeAttributeReader(final String name) {
        readers.remove(name);
    }
}
