package ch.bzz.delish.repositories;

import ch.bzz.delish.models.Recipe_Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeIngredientRepository extends CrudRepository<Recipe_Ingredient, Integer> {
    @Query("select i from Recipe_Ingredient i" +
            " where i.recipe.id = :recipe_id" +
            " and i.ingredient.id = :ingredient_id")
    Recipe_Ingredient findByRevepyIdandIngredientId(@Param("recipe_id") Integer recipe_id, @Param("ingredient_id") Integer ingredient_id);
}
