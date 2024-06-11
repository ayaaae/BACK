package com.pfe.servicetache.Service;

import com.pfe.servicetache.Entities.Comment;
import com.pfe.servicetache.Repository.CommentaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {
    private final CommentaireRepository commentaireRepository;

    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public Comment addCommentaire(Comment comment){
        return commentaireRepository.save(comment);
    }
    public Comment updateCommentaire(Comment comment){
        return commentaireRepository.save(comment);
    }
    public List<Comment> findAllCommentaires(){
        return commentaireRepository.findAll();
    }
    public Comment findCommentaireById(Long id){
        return commentaireRepository.findById(id).get();
    }
    public void deleteCommentaireById(Long id){
        commentaireRepository.deleteById(id);
    }
}
