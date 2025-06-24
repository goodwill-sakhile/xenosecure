package com.securecryptor.test;

import com.securecryptor.cli.CommandLineParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineParserTest {

    @Test
    public void testParsingArguments() {
        String[] args = {"--encrypt", "file.txt", "--shred", "--hybrid", "true"};
        Map<String, String> parsed = CommandLineParser.parse(args);

        assertEquals("file.txt", parsed.get("encrypt"));
        assertEquals("true", parsed.get("shred"));
        assertEquals("true", parsed.get("hybrid"));
    }

    @Test
    public void testNoArgs() {
        Map<String, String> parsed = CommandLineParser.parse(new String[]{});
        assertTrue(parsed.isEmpty());
    }
}
