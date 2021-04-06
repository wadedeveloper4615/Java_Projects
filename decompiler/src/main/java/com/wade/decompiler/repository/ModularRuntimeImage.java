package com.wade.decompiler.repository;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.*;

public class ModularRuntimeImage implements Closeable {

    static final String MODULES_PATH = File.separator + "modules";
    static final String PACKAGES_PATH = File.separator + "packages";

    private final URLClassLoader classLoader;
    private final FileSystem fileSystem;

    /**
     * Constructs a default instance.
     *
     * @throws IOException an I/O error occurs accessing the file system
     */
    public ModularRuntimeImage() throws IOException {
        this(null, FileSystems.getFileSystem(URI.create("jrt:/")));
    }

    /**
     * Constructs an instance using the JRT file system implementation from a
     * specific Java Home.
     *
     * @param javaHome Path to a Java 9 or greater home.
     * @throws IOException an I/O error occurs accessing the file system
     */
    public ModularRuntimeImage(final String javaHome) throws IOException {
        final Map<String, ?> emptyMap = Collections.emptyMap();
        final Path jrePath = Paths.get(javaHome);
        final Path jrtFsPath = jrePath.resolve("lib").resolve("jrt-fs.jar");
        this.classLoader = new URLClassLoader(new URL[]{jrtFsPath.toUri().toURL()});
        this.fileSystem = FileSystems.newFileSystem(URI.create("jrt:/"), emptyMap, classLoader);
    }

    private ModularRuntimeImage(final URLClassLoader cl, final FileSystem fs) {
        this.classLoader = cl;
        this.fileSystem = fs;
    }

    @Override
    public void close() throws IOException {
        if (classLoader != null) {
            classLoader.close();
        }
        if (fileSystem != null) {
            fileSystem.close();
        }
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    /**
     * Lists all entries in the given directory.
     *
     * @param dirPath directory path.
     * @return a list of dir entries if an I/O error occurs
     * @throws IOException an I/O error occurs accessing the file system
     */
    public List<Path> list(final Path dirPath) throws IOException {
        final List<Path> list = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath)) {
            final Iterator<Path> iterator = ds.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }
        return list;
    }

    /**
     * Lists all entries in the given directory.
     *
     * @param dirName directory path.
     * @return a list of dir entries if an I/O error occurs
     * @throws IOException an I/O error occurs accessing the file system
     */
    public List<Path> list(final String dirName) throws IOException {
        return list(fileSystem.getPath(dirName));
    }

    /**
     * Lists all modules.
     *
     * @return a list of modules
     * @throws IOException an I/O error occurs accessing the file system
     */
    public List<Path> modules() throws IOException {
        return list(MODULES_PATH);
    }

    /**
     * Lists all packages.
     *
     * @return a list of modules
     * @throws IOException an I/O error occurs accessing the file system
     */
    public List<Path> packages() throws IOException {
        return list(PACKAGES_PATH);
    }

}
