package com.pfe.servicetache.Repository;

import com.pfe.servicetache.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository  extends JpaRepository<Comment,Long> {
}
