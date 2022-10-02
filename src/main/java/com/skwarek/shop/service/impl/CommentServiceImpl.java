package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.AccountNotFoundException;
import com.skwarek.shop.exception.CommentNotFoundException;
import com.skwarek.shop.exception.ProductNotFoundException;
import com.skwarek.shop.model.product.Comment;
import com.skwarek.shop.model.product.Product;
import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.repository.AccountRepository;
import com.skwarek.shop.repository.CommentRepository;
import com.skwarek.shop.repository.ProductRepository;
import com.skwarek.shop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Not found comment with id: " + commentId));
    }

    @Override
    public Comment create(Long productId, Comment commentRequest) {
        Optional<Product> productDb = productRepository.findById(productId);

        if (productDb.isPresent()) {
            String username = commentRequest.getCreatedBy().getUsername();
            Optional<Account> accountDb = accountRepository.findOptionalByUsername(username);

            if (accountDb.isPresent()) {
                Comment newComment = new Comment();
                newComment.setRating(commentRequest.getRating());
                newComment.setCreatedAt(LocalDateTime.now());
                newComment.setUpdatedAt(null);
                newComment.setContent(commentRequest.getContent());
                newComment.setNumberOfLikes(0);
                newComment.setNumberOfDislikes(0);
                newComment.setProduct(productDb.get());
                newComment.setCreatedBy(accountDb.get());

                return commentRepository.save(newComment);
            } else {
                throw new AccountNotFoundException("Not found account with username: " + username);
            }
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

    @Override
    public Comment update(Long commentId, Comment commentRequest) {
        Optional<Comment> commentDb = commentRepository.findById(commentId);

        if (commentDb.isPresent()) {
            Comment oldComment = commentDb.get();
            oldComment.setRating(commentRequest.getRating());
            oldComment.setUpdatedAt(LocalDateTime.now());
            oldComment.setContent(commentRequest.getContent());

            return commentRepository.save(oldComment);
        } else {
            throw new CommentNotFoundException("Not found comment with id: " + commentId);
        }
    }

    @Override
    public void deleteById(Long commentId) {
        boolean isCommentExists = commentRepository.existsById(commentId);

        if (isCommentExists) {
            commentRepository.deleteById(commentId);
        } else {
            throw new CommentNotFoundException("Not found comment with id: " + commentId);
        }
    }

    @Override
    public List<Comment> findByProductId(Long productId) {
        boolean isProductExists = productRepository.existsById(productId);

        if (isProductExists) {
            return commentRepository.findByProductId(productId);
        } else {
            throw new ProductNotFoundException("Not found product with id: " + productId);
        }
    }

    @Override
    public List<Comment> findByUsername(String username) {
        boolean isAccountExists = accountRepository.existsByUsername(username);

        if (isAccountExists) {
            return commentRepository.findByCreatedByUsername(username);
        } else {
            throw new AccountNotFoundException("Not found account with username: " + username);
        }
    }

}
