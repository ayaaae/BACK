package com.pfe.servicetache.Web;

import com.pfe.servicetache.Entities.Comment;
import com.pfe.servicetache.Service.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/commentaire")
public class CommentaireController {
    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }
    @GetMapping("/AllCommentaires")
    public ResponseEntity<List<Comment>> getAllCommentaires(){
        List<Comment> comments =commentaireService.findAllCommentaires();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/commentaire/{idCommentaire}")
    public ResponseEntity<Comment> getCommentaire(@PathVariable Long idCommentaire){
        Comment comment =commentaireService.findCommentaireById(idCommentaire);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/createCommentaire")
    public ResponseEntity<Comment> addCommentaire(@RequestBody Comment comment){
        Comment newComment =commentaireService.addCommentaire(comment);
        return new ResponseEntity<>(newComment,HttpStatus.CREATED);
    }
    @PutMapping("/update/{idCommentaire}")
    public ResponseEntity<Comment> updateCommentaire(@PathVariable Long idCommentaire, @RequestBody Comment comment){
        Comment existingComment = commentaireService.findCommentaireById(idCommentaire);

        if (existingComment !=null) {
            comment.setId(idCommentaire);
            Comment updatedComment = commentaireService.updateCommentaire(comment);
            return ResponseEntity.ok(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{idCommentaire}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Long idCommentaire){
        commentaireService.deleteCommentaireById(idCommentaire);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
