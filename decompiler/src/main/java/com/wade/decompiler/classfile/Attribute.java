package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.Const;

public abstract class Attribute implements Cloneable, Node {
    private static final boolean debug = Boolean.getBoolean(Attribute.class.getCanonicalName() + ".debug"); // Debugging on/off
    private static final Map<String, Object> readers = new HashMap<>();
    @java.lang.Deprecated
    protected int name_index; // Points to attribute name in constant pool TODO make private (has getter &
                              // setter)
    @java.lang.Deprecated
    protected int length; // Content length of attribute field TODO make private (has getter & setter)
    @java.lang.Deprecated
    protected byte tag; // Tag to distinguish subclasses TODO make private & final; supposed to be
                        // immutable
    @java.lang.Deprecated
    protected ConstantPool constant_pool; // TODO make private (has getter & setter)

    protected Attribute(final byte tag, final int name_index, final int length, final ConstantPool constant_pool) {
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
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
        return attr;
    }

    public abstract Attribute copy(ConstantPool _constant_pool);

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(name_index);
        file.writeInt(length);
    }

    public final ConstantPool getConstantPool() {
        return constant_pool;
    }

    public final int getLength() {
        return length;
    }

    public String getName() {
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

    @Override
    public String toString() {
        return Const.getAttributeName(tag);
    }

    @java.lang.Deprecated
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

    public static Attribute readAttribute(final DataInput file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        byte tag = Const.ATTR_UNKNOWN; // Unknown attribute
        // Get class name from constant pool via `name_index' indirection
        final int name_index = file.readUnsignedShort();
        final ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, Const.CONSTANT_Utf8);
        final String name = c.getBytes();
        // Length of data in bytes
        final int length = file.readInt();
        // Compare strings to find known attribute
        for (byte i = 0; i < Const.KNOWN_ATTRIBUTES; i++) {
            if (name.equals(Const.getAttributeName(i))) {
                tag = i; // found!
                break;
            }
        }
        // Call proper constructor, depending on `tag'
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
                return new Deprecated(name_index, length, file, constant_pool);
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
                return new Module(name_index, length, file, constant_pool);
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

    public static Attribute readAttribute(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        return readAttribute((DataInput) file, constant_pool);
    }

    public static void removeAttributeReader(final String name) {
        readers.remove(name);
    }
}
