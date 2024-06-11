package com.pfe.serviceutilisateur.Service;
import com.beust.ah.A;
import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Entities.Tokens;
import com.pfe.serviceutilisateur.Entities.Utilisateur;
import com.pfe.serviceutilisateur.Model.AuthenticationRequest;
import com.pfe.serviceutilisateur.Model.AuthenticationResponse;
import com.pfe.serviceutilisateur.Model.RegisterRequest;
import com.pfe.serviceutilisateur.Repository.TokensRepository;
import com.pfe.serviceutilisateur.Security.Crypt;
import com.pfe.serviceutilisateur.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final AdminServiceImplementation admin;
  private final EmployeeServiceImplementation employee;
  private final JwtService jwtService;
  private final TokensRepository token;



  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    UserDetails user = null;
    Utilisateur utilisateur=null;
    Tokens t = new Tokens();
    if(employee.findByEmail(request.getEmail()).get().getEmail()!=null){
      System.out.println("is employeee");
      user = employee.findByEmail(request.getEmail()).get();
      utilisateur =  employee.findByEmail(request.getEmail()).get();


    } else if (admin.findByEmail(request.getEmail()).get().getEmail()!=null) {
      System.out.println("is admin");
      user = admin.findByEmail(request.getEmail()).get();
      utilisateur = admin.findByEmail(request.getEmail()).get();



    }else{
      System.out.println("non of them");
      return null;
    }
if(user.getPassword().equals(request.getPassword())){

  var jwtToken = jwtService.generateToken(user);

  System.out.println(jwtToken);

  t.setToken(jwtToken);
  token.save(t);

  if(utilisateur instanceof Admin ){
    ((Admin) utilisateur).setToken(t);
    admin.Update(((Admin) utilisateur));
System.out.println("-------------------------admin instance :"+utilisateur.toString());
  }else{
    ((Employee) utilisateur).setToken(t);
    System.out.println("-----------------------Employee instance :"+utilisateur.toString());
    System.out.println("user :"+user.toString());

    employee.Update(((Employee) utilisateur));
  }

  ArrayList<Long> newTokens = new ArrayList<Long>();

  for (Admin a : admin.All()){
    if(a.getToken().getId()>0){
      newTokens.add(a.getToken().getId());
    }
  }

  for (Employee e : employee.All()){
    System.out.println(e.toString());
    if(e.getToken().getId()>0){
      newTokens.add(e.getToken().getId());
    }
  }


  for(Tokens tok :token.findAll()){
      if(!newTokens.contains(tok.getId())){
        token.delete(tok);
      }

  }
System.out.println("achhad lbla :"+AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build());

  return AuthenticationResponse.builder()
          .accessToken(jwtToken)
          .build();
}
return null;
  }


}