package ch.bzz.delish.controllers;

import ch.bzz.delish.models.Recipe;
import ch.bzz.delish.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping
    public Iterable<Recipe> findByName(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            return recipeRepository.findAll();
        }
        return recipeRepository.findByName(name);
    }


    @GetMapping(path = "{id}")
    public Recipe findById(@PathVariable Integer id) {
        try {
            return recipeRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe could not be found");
        }
    }

}
