package com.securecryptor.test;

import com.securecryptor.util.Base64Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Base64UtilsTest {

    @Test
    public void testEncodeDecode() {
        String original = "SensitiveData123";
        String encoded = Base64Utils.encode(original.getBytes());
        String decoded = new String(Base64Utils.decode(encoded));

        assertEquals(original, decoded, "Decoded value should match original");
    }
}
