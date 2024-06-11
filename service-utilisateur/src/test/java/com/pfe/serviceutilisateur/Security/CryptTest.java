package com.pfe.serviceutilisateur.Security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptTest {

    @Test
    void encrypt() throws Exception {
        System.out.println("test if encrypting success");
        String test ="im a string to test encryption";
        String key="bXVzdGJlMTZieXRlc2tleQ==";
        assertNotEquals(Crypt.encrypt(test,key),test);

    }

    @Test
    void decrypt() throws Exception {
        System.out.println("test if decryption success");
        String test ="im a string to test decryption";
        String key="bXVzdGJlMTZieXRlc2tleQ==";
        assertEquals(Crypt.decrypt(Crypt.encrypt(test,key),key),test);


    }


}