package com.pfe.serviceutilisateur.Repository;


import com.pfe.serviceutilisateur.Entities.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends JpaRepository<Tokens,Long> {
}
