package com.pfe.serviceutilisateur.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  String nom;
  String prenom;
  String email;
  String mot_de_passe;
  String role;

}