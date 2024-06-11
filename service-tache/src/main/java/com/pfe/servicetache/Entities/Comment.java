package com.pfe.servicetache.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String texte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_backlog")
    @JsonIgnoreProperties("commentaires")
    private BackLog backlog;

    private long idemploye;
}
