package com.pfe.serviceutilisateur;

import com.pfe.serviceutilisateur.Web.UtilisateurController;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Logger;

@SpringBootTest
public class ServiceUtilisateurApplicationTestsAdmin {

    @Test
    void contextLoads() {

    }
    @Test
    public void testcase1() {
        System.out.println("in test case 1 of junit");
    }
    public static void main(String[] args) {
        SpringApplication.run(ServiceUtilisateurApplication.class, args);
        System.out.println("Service Utilisateur test :");
    }

}

