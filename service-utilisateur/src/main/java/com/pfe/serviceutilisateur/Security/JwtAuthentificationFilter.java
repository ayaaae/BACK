package com.pfe.serviceutilisateur.Security;


import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Service.AdminServiceImplementation;
import com.pfe.serviceutilisateur.Service.EmployeeServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EmployeeServiceImplementation employee;
    private final AdminServiceImplementation admin;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }


        jwt = authHeader.split(" ")[1].trim();
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null) {
            UserDetails userDetails=null;

            if(employee.findByEmail(userEmail).get().getEmail()!=null){
                System.out.println("token for employeee");
                userDetails = employee.findByEmail(userEmail).get();
            } else if (admin.findByEmail(userEmail).get().getEmail()!=null) {
                System.out.println("token for admin");
                userDetails = admin.findByEmail(userEmail).get();
            }else{
                System.out.println("unreal token");
                return ;
            }




            if(jwtService.isTokenValid(jwt,userDetails)){
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                       userDetails,
                       null,
                       userDetails.getAuthorities()
               );
               authToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request)
               );
               SecurityContextHolder.getContext().setAuthentication(authToken);

           }

        }

        filterChain.doFilter(request, response);
    }
}
