
package org.apache.bcel.generic.gen;

import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.classfile.constant.Constant;
import org.apache.bcel.classfile.constant.ConstantCP;
import org.apache.bcel.classfile.constant.ConstantClass;
import org.apache.bcel.classfile.constant.ConstantDouble;
import org.apache.bcel.classfile.constant.ConstantFieldref;
import org.apache.bcel.classfile.constant.ConstantFloat;
import org.apache.bcel.classfile.constant.ConstantInteger;
import org.apache.bcel.classfile.constant.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.constant.ConstantInvokeDynamic;
import org.apache.bcel.classfile.constant.ConstantLong;
import org.apache.bcel.classfile.constant.ConstantMethodref;
import org.apache.bcel.classfile.constant.ConstantNameAndType;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantString;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.base.ArrayType;

public class ConstantPoolGen {

    private static class Index {

        final int index;

        Index(final int i) {
            index = i;
        }
    }

    private static final int DEFAULT_BUFFER_SIZE = 256;

    private static final String METHODREF_DELIM = ":";

    private static final String IMETHODREF_DELIM = "#";

    private static final String FIELDREF_DELIM = "&";
    private static final String NAT_DELIM = "%"; // Name and Type

    @Deprecated
    protected int size;

    @Deprecated
    protected Constant[] constants;

    @Deprecated
    protected int index = 1; // First entry (0) used by JVM

    private final Map<String, Index> stringTable = new HashMap<>();

    private final Map<String, Index> classTable = new HashMap<>();

    private final Map<String, Index> utf8Table = new HashMap<>();

    private final Map<String, Index> natTable = new HashMap<>();

    private final Map<String, Index> cpTable = new HashMap<>();

    public ConstantPoolGen() {
        size = DEFAULT_BUFFER_SIZE;
        constants = new Constant[size];
    }

    public ConstantPoolGen(final Constant[] cs) {
        final StringBuilder sb = new StringBuilder(DEFAULT_BUFFER_SIZE);

        size = Math.max(DEFAULT_BUFFER_SIZE, cs.length + 64);
        constants = new Constant[size];

        System.arraycopy(cs, 0, constants, 0, cs.length);
        if (cs.length > 0) {
            index = cs.length;
        }

        for (int i = 1; i < index; i++) {
            final Constant c = constants[i];
            if (c instanceof ConstantString) {
                final ConstantString s = (ConstantString) c;
                final ConstantUtf8 u8 = (ConstantUtf8) constants[s.getStringIndex()];
                final String key = u8.getBytes();
                if (!stringTable.containsKey(key)) {
                    stringTable.put(key, new Index(i));
                }
            } else if (c instanceof ConstantClass) {
                final ConstantClass s = (ConstantClass) c;
                final ConstantUtf8 u8 = (ConstantUtf8) constants[s.getNameIndex()];
                final String key = u8.getBytes();
                if (!classTable.containsKey(key)) {
                    classTable.put(key, new Index(i));
                }
            } else if (c instanceof ConstantNameAndType) {
                final ConstantNameAndType n = (ConstantNameAndType) c;
                final ConstantUtf8 u8 = (ConstantUtf8) constants[n.getNameIndex()];
                final ConstantUtf8 u8_2 = (ConstantUtf8) constants[n.getSignatureIndex()];

                sb.append(u8.getBytes());
                sb.append(NAT_DELIM);
                sb.append(u8_2.getBytes());
                final String key = sb.toString();
                sb.delete(0, sb.length());

                if (!natTable.containsKey(key)) {
                    natTable.put(key, new Index(i));
                }
            } else if (c instanceof ConstantUtf8) {
                final ConstantUtf8 u = (ConstantUtf8) c;
                final String key = u.getBytes();
                if (!utf8Table.containsKey(key)) {
                    utf8Table.put(key, new Index(i));
                }
            } else if (c instanceof ConstantCP) {
                final ConstantCP m = (ConstantCP) c;
                String class_name;
                ConstantUtf8 u8;

                if (c instanceof ConstantInvokeDynamic) {
                    class_name = Integer.toString(((ConstantInvokeDynamic) m).getBootstrapMethodAttrIndex());
                    // since name can't begin with digit, can use
                    // METHODREF_DELIM with out fear of duplicates.
                } else {
                    final ConstantClass clazz = (ConstantClass) constants[m.getClassIndex()];
                    u8 = (ConstantUtf8) constants[clazz.getNameIndex()];
                    class_name = u8.getBytes().replace('/', '.');
                }

                final ConstantNameAndType n = (ConstantNameAndType) constants[m.getNameAndTypeIndex()];
                u8 = (ConstantUtf8) constants[n.getNameIndex()];
                final String method_name = u8.getBytes();
                u8 = (ConstantUtf8) constants[n.getSignatureIndex()];
                final String signature = u8.getBytes();

                String delim = METHODREF_DELIM;
                if (c instanceof ConstantInterfaceMethodref) {
                    delim = IMETHODREF_DELIM;
                } else if (c instanceof ConstantFieldref) {
                    delim = FIELDREF_DELIM;
                }

                sb.append(class_name);
                sb.append(delim);
                sb.append(method_name);
                sb.append(delim);
                sb.append(signature);
                final String key = sb.toString();
                sb.delete(0, sb.length());

                if (!cpTable.containsKey(key)) {
                    cpTable.put(key, new Index(i));
                }
            } else if (c == null) { // entries may be null
                // nothing to do
            } else if (c instanceof ConstantInteger) {
                // nothing to do
            } else if (c instanceof ConstantLong) {
                // nothing to do
            } else if (c instanceof ConstantFloat) {
                // nothing to do
            } else if (c instanceof ConstantDouble) {
                // nothing to do
            } else if (c instanceof org.apache.bcel.classfile.constant.ConstantMethodType) {
                // TODO should this be handled somehow?
            } else if (c instanceof org.apache.bcel.classfile.constant.ConstantMethodHandle) {
                // TODO should this be handled somehow?
            } else if (c instanceof org.apache.bcel.classfile.constant.ConstantModule) {
                // TODO should this be handled somehow?
            } else if (c instanceof org.apache.bcel.classfile.constant.ConstantPackage) {
                // TODO should this be handled somehow?
            } else {
                assert false : "Unexpected constant type: " + c.getClass().getName();
            }
        }
    }

    public ConstantPoolGen(final ConstantPool cp) {
        this(cp.getConstantPool());
    }

    public int addArrayClass(final ArrayType type) {
        return addClass_(type.getSignature());
    }

    public int addClass(final ObjectType type) {
        return addClass(type.getClassName());
    }

    public int addClass(final String str) {
        return addClass_(str.replace('.', '/'));
    }

    private int addClass_(final String clazz) {
        int ret;
        if ((ret = lookupClass(clazz)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        final ConstantClass c = new ConstantClass(addUtf8(clazz));
        ret = index;
        constants[index++] = c;
        if (!classTable.containsKey(clazz)) {
            classTable.put(clazz, new Index(ret));
        }
        return ret;
    }

    public int addConstant(final Constant c, final ConstantPoolGen cp) {
        final Constant[] constants = cp.getConstantPool().getConstantPool();
        switch (c.getTag()) {
            case CONSTANT_String: {
                final ConstantString s = (ConstantString) c;
                final ConstantUtf8 u8 = (ConstantUtf8) constants[s.getStringIndex()];
                return addString(u8.getBytes());
            }
            case CONSTANT_Class: {
                final ConstantClass s = (ConstantClass) c;
                final ConstantUtf8 u8 = (ConstantUtf8) constants[s.getNameIndex()];
                return addClass(u8.getBytes());
            }
            case CONSTANT_NameAndType: {
                final ConstantNameAndType n = (ConstantNameAndType) c;
                final ConstantUtf8 u8 = (ConstantUtf8) constants[n.getNameIndex()];
                final ConstantUtf8 u8_2 = (ConstantUtf8) constants[n.getSignatureIndex()];
                return addNameAndType(u8.getBytes(), u8_2.getBytes());
            }
            case CONSTANT_Utf8:
                return addUtf8(((ConstantUtf8) c).getBytes());
            case CONSTANT_Double:
                return addDouble(((ConstantDouble) c).getBytes());
            case CONSTANT_Float:
                return addFloat(((ConstantFloat) c).getBytes());
            case CONSTANT_Long:
                return addLong(((ConstantLong) c).getBytes());
            case CONSTANT_Integer:
                return addInteger(((ConstantInteger) c).getBytes());
            case CONSTANT_InterfaceMethodref:
            case CONSTANT_Methodref:
            case CONSTANT_Fieldref: {
                final ConstantCP m = (ConstantCP) c;
                final ConstantClass clazz = (ConstantClass) constants[m.getClassIndex()];
                final ConstantNameAndType n = (ConstantNameAndType) constants[m.getNameAndTypeIndex()];
                ConstantUtf8 u8 = (ConstantUtf8) constants[clazz.getNameIndex()];
                final String class_name = u8.getBytes().replace('/', '.');
                u8 = (ConstantUtf8) constants[n.getNameIndex()];
                final String name = u8.getBytes();
                u8 = (ConstantUtf8) constants[n.getSignatureIndex()];
                final String signature = u8.getBytes();
                switch (c.getTag()) {
                    case CONSTANT_InterfaceMethodref:
                        return addInterfaceMethodref(class_name, name, signature);
                    case CONSTANT_Methodref:
                        return addMethodref(class_name, name, signature);
                    case CONSTANT_Fieldref:
                        return addFieldref(class_name, name, signature);
                    default: // Never reached
                        throw new IllegalArgumentException("Unknown constant type " + c);
                }
            }
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + c);
        }
    }

    public int addDouble(final double n) {
        int ret;
        if ((ret = lookupDouble(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index] = new ConstantDouble(n);
        index += 2; // Wastes one entry according to spec
        return ret;
    }

    public int addFieldref(final String class_name, final String field_name, final String signature) {
        int ret;
        int class_index;
        int name_and_type_index;
        if ((ret = lookupFieldref(class_name, field_name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        class_index = addClass(class_name);
        name_and_type_index = addNameAndType(field_name, signature);
        ret = index;
        constants[index++] = new ConstantFieldref(class_index, name_and_type_index);
        final String key = class_name + FIELDREF_DELIM + field_name + FIELDREF_DELIM + signature;
        if (!cpTable.containsKey(key)) {
            cpTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addFloat(final float n) {
        int ret;
        if ((ret = lookupFloat(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index++] = new ConstantFloat(n);
        return ret;
    }

    public int addInteger(final int n) {
        int ret;
        if ((ret = lookupInteger(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index++] = new ConstantInteger(n);
        return ret;
    }

    public int addInterfaceMethodref(final MethodGen method) {
        return addInterfaceMethodref(method.getClassName().getName(), method.getName(), method.getSignature());
    }

    public int addInterfaceMethodref(final String class_name, final String method_name, final String signature) {
        int ret;
        int class_index;
        int name_and_type_index;
        if ((ret = lookupInterfaceMethodref(class_name, method_name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        class_index = addClass(class_name);
        name_and_type_index = addNameAndType(method_name, signature);
        ret = index;
        constants[index++] = new ConstantInterfaceMethodref(class_index, name_and_type_index);
        final String key = class_name + IMETHODREF_DELIM + method_name + IMETHODREF_DELIM + signature;
        if (!cpTable.containsKey(key)) {
            cpTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addLong(final long n) {
        int ret;
        if ((ret = lookupLong(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index] = new ConstantLong(n);
        index += 2; // Wastes one entry according to spec
        return ret;
    }

    public int addMethodref(final MethodGen method) {
        return addMethodref(method.getClassName().getName(), method.getName(), method.getSignature());
    }

    public int addMethodref(final String class_name, final String method_name, final String signature) {
        int ret;
        int class_index;
        int name_and_type_index;
        if ((ret = lookupMethodref(class_name, method_name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        name_and_type_index = addNameAndType(method_name, signature);
        class_index = addClass(class_name);
        ret = index;
        constants[index++] = new ConstantMethodref(class_index, name_and_type_index);
        final String key = class_name + METHODREF_DELIM + method_name + METHODREF_DELIM + signature;
        if (!cpTable.containsKey(key)) {
            cpTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addNameAndType(final String name, final String signature) {
        int ret;
        int name_index;
        int signature_index;
        if ((ret = lookupNameAndType(name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        name_index = addUtf8(name);
        signature_index = addUtf8(signature);
        ret = index;
        constants[index++] = new ConstantNameAndType(name_index, signature_index);
        final String key = name + NAT_DELIM + signature;
        if (!natTable.containsKey(key)) {
            natTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addString(final String str) {
        int ret;
        if ((ret = lookupString(str)) != -1) {
            return ret; // Already in CP
        }
        final int utf8 = addUtf8(str);
        adjustSize();
        final ConstantString s = new ConstantString(utf8);
        ret = index;
        constants[index++] = s;
        if (!stringTable.containsKey(str)) {
            stringTable.put(str, new Index(ret));
        }
        return ret;
    }

    public int addUtf8(final String n) {
        int ret;
        if ((ret = lookupUtf8(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index++] = new ConstantUtf8(n);
        if (!utf8Table.containsKey(n)) {
            utf8Table.put(n, new Index(ret));
        }
        return ret;
    }

    protected void adjustSize() {
        if (index + 3 >= size) {
            final Constant[] cs = constants;
            size *= 2;
            constants = new Constant[size];
            System.arraycopy(cs, 0, constants, 0, index);
        }
    }

    public Constant getConstant(final int i) {
        return constants[i];
    }

    public ConstantPool getConstantPool() {
        return new ConstantPool(constants);
    }

    public ConstantPool getFinalConstantPool() {
        final Constant[] cs = new Constant[index];
        System.arraycopy(constants, 0, cs, 0, index);
        return new ConstantPool(cs);
    }

    public int getSize() {
        return index;
    }

    public int lookupClass(final String str) {
        final Index index = classTable.get(str.replace('.', '/'));
        return (index != null) ? index.index : -1;
    }

    public int lookupDouble(final double n) {
        final long bits = Double.doubleToLongBits(n);
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantDouble) {
                final ConstantDouble c = (ConstantDouble) constants[i];
                if (Double.doubleToLongBits(c.getBytes()) == bits) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupFieldref(final String class_name, final String field_name, final String signature) {
        final Index index = cpTable.get(class_name + FIELDREF_DELIM + field_name + FIELDREF_DELIM + signature);
        return (index != null) ? index.index : -1;
    }

    public int lookupFloat(final float n) {
        final int bits = Float.floatToIntBits(n);
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantFloat) {
                final ConstantFloat c = (ConstantFloat) constants[i];
                if (Float.floatToIntBits(c.getBytes()) == bits) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupInteger(final int n) {
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantInteger) {
                final ConstantInteger c = (ConstantInteger) constants[i];
                if (c.getBytes() == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupInterfaceMethodref(final MethodGen method) {
        return lookupInterfaceMethodref(method.getClassName().getName(), method.getName(), method.getSignature());
    }

    public int lookupInterfaceMethodref(final String class_name, final String method_name, final String signature) {
        final Index index = cpTable.get(class_name + IMETHODREF_DELIM + method_name + IMETHODREF_DELIM + signature);
        return (index != null) ? index.index : -1;
    }

    public int lookupLong(final long n) {
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantLong) {
                final ConstantLong c = (ConstantLong) constants[i];
                if (c.getBytes() == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupMethodref(final MethodGen method) {
        return lookupMethodref(method.getClassName().getName(), method.getName(), method.getSignature());
    }

    public int lookupMethodref(final String class_name, final String method_name, final String signature) {
        final Index index = cpTable.get(class_name + METHODREF_DELIM + method_name + METHODREF_DELIM + signature);
        return (index != null) ? index.index : -1;
    }

    public int lookupNameAndType(final String name, final String signature) {
        final Index _index = natTable.get(name + NAT_DELIM + signature);
        return (_index != null) ? _index.index : -1;
    }

    public int lookupString(final String str) {
        final Index index = stringTable.get(str);
        return (index != null) ? index.index : -1;
    }

    public int lookupUtf8(final String n) {
        final Index index = utf8Table.get(n);
        return (index != null) ? index.index : -1;
    }

    public void setConstant(final int i, final Constant c) {
        constants[i] = c;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        for (int i = 1; i < index; i++) {
            buf.append(i).append(")").append(constants[i]).append("\n");
        }
        return buf.toString();
    }
}
