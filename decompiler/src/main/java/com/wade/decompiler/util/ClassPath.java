package com.wade.decompiler.util;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ClassPath implements Closeable {
    private static FilenameFilter ARCHIVE_FILTER = (dir, name) -> {
        name = name.toLowerCase(Locale.ENGLISH);
        return name.endsWith(".zip") || name.endsWith(".jar");
    };

    private static FilenameFilter MODULES_FILTER = (dir, name) -> {
        name = name.toLowerCase(Locale.ENGLISH);
        return name.endsWith(".jmod");
    };

    public static ClassPath SYSTEM_CLASS_PATH = new ClassPath(getClassPath());

    private abstract static class AbstractPathEntry implements Closeable {
        abstract ClassFile getClassFile(String name, String suffix) throws IOException;

        abstract URL getResource(String name);

        abstract InputStream getResourceAsStream(String name);
    }

    private abstract static class AbstractZip extends AbstractPathEntry {
        private ZipFile zipFile;

        AbstractZip(ZipFile zipFile) {
            this.zipFile = Objects.requireNonNull(zipFile, "zipFile");
        }

        @Override
        public void close() throws IOException {
            if (zipFile != null) {
                zipFile.close();
            }
        }

        @Override
        ClassFile getClassFile(String name, String suffix) throws IOException {
            ZipEntry entry = zipFile.getEntry(toEntryName(name, suffix));
            if (entry == null) {
                return null;
            }
            return new ClassFile() {
                @Override
                public String getBase() {
                    return zipFile.getName();
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return zipFile.getInputStream(entry);
                }

                @Override
                public String getPath() {
                    return entry.toString();
                }

                @Override
                public long getSize() {
                    return entry.getSize();
                }

                @Override
                public long getTime() {
                    return entry.getTime();
                }
            };
        }

        @Override
        URL getResource(String name) {
            ZipEntry entry = zipFile.getEntry(name);
            try {
                return entry != null ? new URL("jar:file:" + zipFile.getName() + "!/" + name) : null;
            } catch (MalformedURLException e) {
                return null;
            }
        }

        @Override
        InputStream getResourceAsStream(String name) {
            ZipEntry entry = zipFile.getEntry(name);
            try {
                return entry != null ? zipFile.getInputStream(entry) : null;
            } catch (IOException e) {
                return null;
            }
        }

        protected abstract String toEntryName(String name, String suffix);

        @Override
        public String toString() {
            return zipFile.getName();
        }
    }

    public interface ClassFile {
        String getBase();

        InputStream getInputStream() throws IOException;

        String getPath();

        long getSize();

        long getTime();
    }

    private static class Dir extends AbstractPathEntry {
        private String dir;

        Dir(String d) {
            dir = d;
        }

        @Override
        public void close() throws IOException {
            // Nothing to do
        }

        @Override
        ClassFile getClassFile(String name, String suffix) throws IOException {
            File file = new File(dir + File.separatorChar + name.replace('.', File.separatorChar) + suffix);
            return file.exists() ? new ClassFile() {
                @Override
                public String getBase() {
                    return dir;
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return new FileInputStream(file);
                }

                @Override
                public String getPath() {
                    try {
                        return file.getCanonicalPath();
                    } catch (IOException e) {
                        return null;
                    }
                }

                @Override
                public long getSize() {
                    return file.length();
                }

                @Override
                public long getTime() {
                    return file.lastModified();
                }
            } : null;
        }

        @Override
        URL getResource(String name) {
            // Resource specification uses '/' whatever the platform
            File file = toFile(name);
            try {
                return file.exists() ? file.toURI().toURL() : null;
            } catch (MalformedURLException e) {
                return null;
            }
        }

        @Override
        InputStream getResourceAsStream(String name) {
            // Resource specification uses '/' whatever the platform
            File file = toFile(name);
            try {
                return file.exists() ? new FileInputStream(file) : null;
            } catch (IOException e) {
                return null;
            }
        }

        private File toFile(String name) {
            return new File(dir + File.separatorChar + name.replace('/', File.separatorChar));
        }

        @Override
        public String toString() {
            return dir;
        }
    }

    private static class Jar extends AbstractZip {
        Jar(ZipFile zip) {
            super(zip);
        }

        @Override
        protected String toEntryName(String name, String suffix) {
            return packageToFolder(name) + suffix;
        }
    }

    private static class JrtModule extends AbstractPathEntry {
        private Path modulePath;

        public JrtModule(Path modulePath) {
            this.modulePath = Objects.requireNonNull(modulePath, "modulePath");
        }

        @Override
        public void close() throws IOException {
            // Nothing to do.
        }

        @Override
        ClassFile getClassFile(String name, String suffix) throws IOException {
            Path resolved = modulePath.resolve(packageToFolder(name) + suffix);
            if (Files.exists(resolved)) {
                return new ClassFile() {
                    @Override
                    public String getBase() {
                        return resolved.getFileName().toString();
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return Files.newInputStream(resolved);
                    }

                    @Override
                    public String getPath() {
                        return resolved.toString();
                    }

                    @Override
                    public long getSize() {
                        try {
                            return Files.size(resolved);
                        } catch (IOException e) {
                            return 0;
                        }
                    }

                    @Override
                    public long getTime() {
                        try {
                            return Files.getLastModifiedTime(resolved).toMillis();
                        } catch (IOException e) {
                            return 0;
                        }
                    }
                };
            }
            return null;
        }

        @Override
        URL getResource(String name) {
            Path resovled = modulePath.resolve(name);
            try {
                return Files.exists(resovled) ? new URL("jrt:" + modulePath + "/" + name) : null;
            } catch (MalformedURLException e) {
                return null;
            }
        }

        @Override
        InputStream getResourceAsStream(String name) {
            try {
                return Files.newInputStream(modulePath.resolve(name));
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public String toString() {
            return modulePath.toString();
        }
    }

    private static class JrtModules extends AbstractPathEntry {
        private ModularRuntimeImage modularRuntimeImage;
        private JrtModule[] modules;

        public JrtModules(String path) throws IOException {
            this.modularRuntimeImage = new ModularRuntimeImage();
            List<Path> list = modularRuntimeImage.list(path);
            this.modules = new JrtModule[list.size()];
            for (int i = 0; i < modules.length; i++) {
                modules[i] = new JrtModule(list.get(i));
            }
        }

        @Override
        public void close() throws IOException {
            if (modules != null) {
                // don't use a for each loop to avoid creating an iterator for the GC to
                // collect.
                for (JrtModule module : modules) {
                    module.close();
                }
            }
            if (modularRuntimeImage != null) {
                modularRuntimeImage.close();
            }
        }

        @Override
        ClassFile getClassFile(String name, String suffix) throws IOException {
            // don't use a for each loop to avoid creating an iterator for the GC to
            // collect.
            for (JrtModule module : modules) {
                ClassFile classFile = module.getClassFile(name, suffix);
                if (classFile != null) {
                    return classFile;
                }
            }
            return null;
        }

        @Override
        URL getResource(String name) {
            // don't use a for each loop to avoid creating an iterator for the GC to
            // collect.
            for (JrtModule module : modules) {
                URL url = module.getResource(name);
                if (url != null) {
                    return url;
                }
            }
            return null;
        }

        @Override
        InputStream getResourceAsStream(String name) {
            // don't use a for each loop to avoid creating an iterator for the GC to
            // collect.
            for (JrtModule module : modules) {
                InputStream inputStream = module.getResourceAsStream(name);
                if (inputStream != null) {
                    return inputStream;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return Arrays.toString(modules);
        }
    }

    private static class Module extends AbstractZip {
        Module(ZipFile zip) {
            super(zip);
        }

        @Override
        protected String toEntryName(String name, String suffix) {
            return "classes/" + packageToFolder(name) + suffix;
        }
    }

    private String classPath;
    private ClassPath parent;
    private AbstractPathEntry[] paths;

    @Deprecated
    public ClassPath() {
        this(getClassPath());
    }

    public ClassPath(ClassPath parent, String classPath) {
        this(classPath);
        this.parent = parent;
    }

    public ClassPath(String classPath) {
        this.classPath = classPath;
        List<AbstractPathEntry> list = new ArrayList<>();
        for (StringTokenizer tokenizer = new StringTokenizer(classPath, File.pathSeparator); tokenizer.hasMoreTokens();) {
            String path = tokenizer.nextToken();
            if (!path.isEmpty()) {
                File file = new File(path);
                try {
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            list.add(new Dir(path));
                        } else if (path.endsWith(".jmod")) {
                            list.add(new Module(new ZipFile(file)));
                        } else if (path.endsWith(ModularRuntimeImage.MODULES_PATH)) {
                            list.add(new JrtModules(ModularRuntimeImage.MODULES_PATH));
                        } else {
                            list.add(new Jar(new ZipFile(file)));
                        }
                    }
                } catch (IOException e) {
                    if (path.endsWith(".zip") || path.endsWith(".jar")) {
                        System.err.println("CLASSPATH component " + file + ": " + e);
                    }
                }
            }
        }
        paths = new AbstractPathEntry[list.size()];
        list.toArray(paths);
    }

    @Override
    public void close() throws IOException {
        if (paths != null) {
            for (AbstractPathEntry path : paths) {
                path.close();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ClassPath) {
            ClassPath cp = (ClassPath) o;
            return classPath.equals(cp.toString());
        }
        return false;
    }

    public byte[] getBytes(String name) throws IOException {
        return getBytes(name, ".class");
    }

    public byte[] getBytes(String name, String suffix) throws IOException {
        DataInputStream dis = null;
        try (InputStream inputStream = getInputStream(name, suffix)) {
            if (inputStream == null) {
                throw new IOException("Couldn't find: " + name + suffix);
            }
            dis = new DataInputStream(inputStream);
            byte[] bytes = new byte[inputStream.available()];
            dis.readFully(bytes);
            return bytes;
        } finally {
            if (dis != null) {
                dis.close();
            }
        }
    }

    public ClassFile getClassFile(String name) throws IOException {
        return getClassFile(name, ".class");
    }

    public ClassFile getClassFile(String name, String suffix) throws IOException {
        ClassFile cf = null;
        if (parent != null) {
            cf = parent.getClassFileInternal(name, suffix);
        }
        if (cf == null) {
            cf = getClassFileInternal(name, suffix);
        }
        if (cf != null) {
            return cf;
        }
        throw new IOException("Couldn't find: " + name + suffix);
    }

    private ClassFile getClassFileInternal(String name, String suffix) throws IOException {
        for (AbstractPathEntry path : paths) {
            ClassFile cf = path.getClassFile(name, suffix);
            if (cf != null) {
                return cf;
            }
        }
        return null;
    }

    public InputStream getInputStream(String name) throws IOException {
        return getInputStream(packageToFolder(name), ".class");
    }

    public InputStream getInputStream(String name, String suffix) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(name + suffix); // may return null
        } catch (Exception e) {
            // ignored
        }
        if (inputStream != null) {
            return inputStream;
        }
        return getClassFile(name, suffix).getInputStream();
    }

    public String getPath(String name) throws IOException {
        int index = name.lastIndexOf('.');
        String suffix = "";
        if (index > 0) {
            suffix = name.substring(index);
            name = name.substring(0, index);
        }
        return getPath(name, suffix);
    }

    public String getPath(String name, String suffix) throws IOException {
        return getClassFile(name, suffix).getPath();
    }

    public URL getResource(String name) {
        for (AbstractPathEntry path : paths) {
            URL url;
            if ((url = path.getResource(name)) != null) {
                return url;
            }
        }
        return null;
    }

    public InputStream getResourceAsStream(String name) {
        for (AbstractPathEntry path : paths) {
            InputStream is;
            if ((is = path.getResourceAsStream(name)) != null) {
                return is;
            }
        }
        return null;
    }

    public Enumeration<URL> getResources(String name) {
        Vector<URL> results = new Vector<>();
        for (AbstractPathEntry path : paths) {
            URL url;
            if ((url = path.getResource(name)) != null) {
                results.add(url);
            }
        }
        return results.elements();
    }

    @Override
    public int hashCode() {
        if (parent != null) {
            return classPath.hashCode() + parent.hashCode();
        }
        return classPath.hashCode();
    }

    @Override
    public String toString() {
        if (parent != null) {
            return parent + File.pathSeparator + classPath;
        }
        return classPath;
    }

    private static void addJdkModules(String javaHome, List<String> list) {
        String modulesPath = System.getProperty("java.modules.path");
        if (modulesPath == null || modulesPath.trim().isEmpty()) {
            // Default to looking in JAVA_HOME/jmods
            modulesPath = javaHome + File.separator + "jmods";
        }
        File modulesDir = new File(modulesPath);
        if (modulesDir.exists()) {
            String[] modules = modulesDir.list(MODULES_FILTER);
            for (String module : modules) {
                list.add(modulesDir.getPath() + File.separatorChar + module);
            }
        }
    }

    // @since 6.0 no longer
    public static String getClassPath() {
        String classPathProp = System.getProperty("java.class.path");
        String bootClassPathProp = System.getProperty("sun.boot.class.path");
        String extDirs = System.getProperty("java.ext.dirs");
        // System.out.println("java.version = " + System.getProperty("java.version"));
        // System.out.println("java.class.path = " + classPathProp);
        // System.out.println("sun.boot.class.path=" + bootClassPathProp);
        // System.out.println("java.ext.dirs=" + extDirs);
        String javaHome = System.getProperty("java.home");
        List<String> list = new ArrayList<>();
        // Starting in JRE 9, .class files are in the modules directory. Add them to the
        // path.
        Path modulesPath = Paths.get(javaHome).resolve("lib/modules");
        if (Files.exists(modulesPath) && Files.isRegularFile(modulesPath)) {
            list.add(modulesPath.toAbsolutePath().toString());
        }
        // Starting in JDK 9, .class files are in the jmods directory. Add them to the
        // path.
        addJdkModules(javaHome, list);
        getPathComponents(classPathProp, list);
        getPathComponents(bootClassPathProp, list);
        List<String> dirs = new ArrayList<>();
        getPathComponents(extDirs, dirs);
        for (String d : dirs) {
            File ext_dir = new File(d);
            String[] extensions = ext_dir.list(ARCHIVE_FILTER);
            if (extensions != null) {
                for (String extension : extensions) {
                    list.add(ext_dir.getPath() + File.separatorChar + extension);
                }
            }
        }
        StringBuilder buf = new StringBuilder();
        String separator = "";
        for (String path : list) {
            buf.append(separator);
            separator = File.pathSeparator;
            buf.append(path);
        }
        return buf.toString().intern();
    }

    private static void getPathComponents(String path, List<String> list) {
        if (path != null) {
            StringTokenizer tokenizer = new StringTokenizer(path, File.pathSeparator);
            while (tokenizer.hasMoreTokens()) {
                String name = tokenizer.nextToken();
                File file = new File(name);
                if (file.exists()) {
                    list.add(name);
                }
            }
        }
    }

    static String packageToFolder(String name) {
        return name.replace('.', '/');
    }
}
