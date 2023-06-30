package ch.bzz.delish.controllers;

import ch.bzz.delish.models.Comment;
import ch.bzz.delish.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody Comment comment) {
        try {
            commentRepository.save(comment);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "conflict");
        }
    }

    @PutMapping
    public void update(@Valid @RequestBody Comment comment) {
        try {
            commentRepository.save(comment);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.CONFLICT, "conflict");
        }

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        try {
            commentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment could not be deleted");
        }
    }
}
