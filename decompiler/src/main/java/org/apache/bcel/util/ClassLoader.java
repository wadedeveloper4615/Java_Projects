package org.apache.bcel.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.constant.ConstantClass;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileConstants;

@Deprecated
public class ClassLoader extends java.lang.ClassLoader {
    private static final String BCEL_TOKEN = "$$BCEL$$";
    public static final String[] DEFAULT_IGNORED_PACKAGES = { "java.", "javax.", "sun." };
    private final Hashtable<String, Class<?>> classes = new Hashtable<>();
    private final String[] ignored_packages;
    private Repository repository = SyntheticRepository.getInstance();

    public ClassLoader() {
        this(DEFAULT_IGNORED_PACKAGES);
    }

    public ClassLoader(final java.lang.ClassLoader deferTo) {
        super(deferTo);
        this.ignored_packages = DEFAULT_IGNORED_PACKAGES;
        this.repository = new ClassLoaderRepository(deferTo);
    }

    public ClassLoader(final java.lang.ClassLoader deferTo, final String[] ignored_packages) {
        this(ignored_packages);
        this.repository = new ClassLoaderRepository(deferTo);
    }

    public ClassLoader(final String[] ignored_packages) {
        this.ignored_packages = ignored_packages;
    }

    protected JavaClass createClass(final String class_name) {
        final int index = class_name.indexOf(BCEL_TOKEN);
        final String real_name = class_name.substring(index + BCEL_TOKEN.length());
        JavaClass clazz = null;
        try {
            final byte[] bytes = Utility.decode(real_name, true);
            final ClassParser parser = new ClassParser(new ByteArrayInputStream(bytes), "foo");
            clazz = parser.parse();
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
        final ConstantPool cp = clazz.getConstantPool();
        final ConstantClass cl = (ConstantClass) cp.getConstant(clazz.getClassName().getNameIndex(), ClassFileConstants.CONSTANT_Class);
        final ConstantUtf8 name = (ConstantUtf8) cp.getConstant(cl.getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
        name.setBytes(class_name.replace('.', '/'));
        return clazz;
    }

    @Override
    protected Class<?> loadClass(final String class_name, final boolean resolve) throws ClassNotFoundException {
        Class<?> cl = null;
        if ((cl = classes.get(class_name)) == null) {
            for (final String ignored_package : ignored_packages) {
                if (class_name.startsWith(ignored_package)) {
                    cl = getParent().loadClass(class_name);
                    break;
                }
            }
            if (cl == null) {
                JavaClass clazz = null;
                if (class_name.contains(BCEL_TOKEN)) {
                    clazz = createClass(class_name);
                } else {
                    if ((clazz = repository.loadClass(class_name)) != null) {
                        clazz = modifyClass(clazz);
                    } else {
                        throw new ClassNotFoundException(class_name);
                    }
                }
                if (clazz != null) {
                    final byte[] bytes = clazz.getBytes();
                    cl = defineClass(class_name, bytes, 0, bytes.length);
                } else {
                    cl = Class.forName(class_name);
                }
            }
            if (resolve) {
                resolveClass(cl);
            }
        }
        classes.put(class_name, cl);
        return cl;
    }

    protected JavaClass modifyClass(final JavaClass clazz) {
        return clazz;
    }
}
