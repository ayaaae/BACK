package com.pfe.serviceutilisateur.Entities;

import com.pfe.serviceutilisateur.enumirator.role_utilisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter @Setter
@Entity
@ToString
public class Employee extends Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String projetids;
    private String prenom;
    private String email;
    private  String mot_de_passe;
    @Enumerated(EnumType.STRING)
    private role_utilisateur role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private Tokens token;

    private long notificationId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return mot_de_passe;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
