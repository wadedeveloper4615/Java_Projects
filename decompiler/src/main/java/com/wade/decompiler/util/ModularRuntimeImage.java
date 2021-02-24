package com.wade.decompiler.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ModularRuntimeImage implements Closeable {
    static String MODULES_PATH = File.separator + "modules";
    static String PACKAGES_PATH = File.separator + "packages";
    private URLClassLoader classLoader;
    private FileSystem fileSystem;


    public ModularRuntimeImage() throws IOException {
        this(null, FileSystems.getFileSystem(URI.create("jrt:/")));
    }

    public ModularRuntimeImage(String javaHome) throws IOException {
        Map<String, ?> emptyMap = Collections.emptyMap();
        Path jrePath = Paths.get(javaHome);
        Path jrtFsPath = jrePath.resolve("lib").resolve("jrt-fs.jar");
        this.classLoader = new URLClassLoader(new URL[] { jrtFsPath.toUri().toURL() });
        this.fileSystem = FileSystems.newFileSystem(URI.create("jrt:/"), emptyMap, classLoader);
    }

    private ModularRuntimeImage(URLClassLoader cl, FileSystem fs) {
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

    public List<Path> list(Path dirPath) throws IOException {
        List<Path> list = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath)) {
            Iterator<Path> iterator = ds.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }
        return list;
    }

    public List<Path> list(String dirName) throws IOException {
        return list(fileSystem.getPath(dirName));
    }

    public List<Path> modules() throws IOException {
        return list(MODULES_PATH);
    }

    public List<Path> packages() throws IOException {
        return list(PACKAGES_PATH);
    }
}
