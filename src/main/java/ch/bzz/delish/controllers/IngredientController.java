package ch.bzz.delish.controllers;


import ch.bzz.delish.models.Ingredient;
import ch.bzz.delish.models.Recipe_Ingredient;
import ch.bzz.delish.repositories.IngredientRepository;
import ch.bzz.delish.repositories.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("{id}")
    public Ingredient findById(@PathVariable Integer id) {
        try {
            return ingredientRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient could not be found");
        }
    }

    @GetMapping("recipe/{id}")
    public Iterable<Ingredient> findIngredientByRecipeId(@PathVariable Integer id) {
        try {
            return ingredientRepository.findIngredientByRecipeId(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredients could not be found");
        }
    }

    @GetMapping("quantity/{recipyId}/{ingredientId}")
    public Recipe_Ingredient findIngredientByRecipeId(@PathVariable Integer recipyId, @PathVariable Integer ingredientId) {
        try {
            return recipeIngredientRepository.findByRevepyIdandIngredientId(recipyId, ingredientId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredients could not be found");
        }
    }


}
