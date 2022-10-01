package com.skwarek.shop.controller;

import com.skwarek.shop.model.product.Comment;
import com.skwarek.shop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
        Comment comment = commentService.findById(commentId);

        return ResponseEntity.ok(comment);
    }

    @PostMapping(value = "/products/{productId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("productId") Long productId,
                                                 @RequestBody Comment commentRequest) {
        Comment createdComment = commentService.create(productId, commentRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/comments")
                .path("/{commentId}").buildAndExpand(createdComment.getId()).toUri();

        return ResponseEntity.created(location).body(createdComment);
    }

    @PutMapping(value = "/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("commentId") Long commentId,
                                                   @RequestBody Comment commentRequest) {
        Comment updatedComment = commentService.update(commentId, commentRequest);

        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteCommentById(@PathVariable("commentId") Long commentId) {
        commentService.deleteById(commentId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/products/{productId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfProductByProductId(@PathVariable("productId") Long productId) {
        List<Comment> comments = commentService.findByProductId(productId);

        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/accounts/{username}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsOfAccountByUsername(@PathVariable("username") String username) {
        List<Comment> comments = commentService.findByUsername(username);

        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
