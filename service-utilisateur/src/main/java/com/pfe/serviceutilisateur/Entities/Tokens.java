package com.pfe.serviceutilisateur.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter @Setter
@ToString
public class Tokens  {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;



}
