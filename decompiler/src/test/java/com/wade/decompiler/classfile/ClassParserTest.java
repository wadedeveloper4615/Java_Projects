package com.wade.decompiler.classfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.Version;

@ExtendWith(MockitoExtension.class)
class ClassParserTest {
    @Mock
    private InputStream inputStream;

    @Test
    void testReadId1() throws IOException {
        //@formatter:off
        when(inputStream.read())
        .thenReturn(0xCA)
        .thenReturn(0xFE)
        .thenReturn(0xBA)
        .thenReturn(0xBF)
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        parser.setInputStream(inputStream);
        try {
            parser.parse();
        } catch (ClassFormatException e) {
        }
    }

    @Test
    void testReadId2() throws Exception {
        //@formatter:off
        when(inputStream.read())
        .thenReturn(0xCA)
        .thenReturn(0xFE)
        .thenReturn(0xBA)
        .thenReturn(0xBE)
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            parser.readID(dataInputStream);
        }
    }

    @Test
    void testReadVersion() throws Exception {
        //@formatter:off
        when(inputStream.read())
        .thenReturn(0)
        .thenReturn(Version.Version_14.getMinor())
        .thenReturn(0)
        .thenReturn(Version.Version_14.getMajor())
        ;
        //@formatter:on
        ClassParser parser = new ClassParser();
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            parser.readVersion(dataInputStream);
            assertEquals(Version.Version_14, parser.getVersion());
        }
    }
}
