package ch.bzz.delish.controllers;

import ch.bzz.delish.models.Favorite;
import ch.bzz.delish.models.Recipe;
import ch.bzz.delish.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/favorites")
public class FavoriteController {


    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("{id}")
    public Iterable<Recipe> findFavoriteRecipesByUserId(@PathVariable Integer id) {
        try {
            return favoriteRepository.findFavoriteRecipesByUserId(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorites could not be found");
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Favorite insert(@Valid @RequestBody Favorite favorite) {
        try {
            return favoriteRepository.save(favorite);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "conflict");
        }
    }

    @DeleteMapping("{loginId}/{recipeId}")
    public void delete(@PathVariable Integer loginId, @PathVariable Integer  recipeId) {
        try {
            favoriteRepository.deleteFavoriteByLoginIdAndRecipeId(loginId, recipeId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite could not be deleted");
        }
    }
}
