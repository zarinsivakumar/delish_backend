package ch.bbcag.fourchefs_backend.repositories;

import ch.bbcag.fourchefs_backend.models.Ingredient;
import ch.bbcag.fourchefs_backend.models.Quantity;
import ch.bbcag.fourchefs_backend.models.Recipe_Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    @Query("select i from Ingredient i" +
            " join i.ingredients ri" +
            " where ri.recipe.id = :id")
    Iterable<Ingredient> findIngredientByRecipeId(@Param("id") Integer id);

    @Query("select i from Recipe_Ingredient i" +
            " where i.recipe.id = :recipe_id" +
            " and i.ingredient.id = :ingredient_id")
    Recipe_Ingredient findRecipe_IngredientByIngredientIdAndRecipeId(@Param("recipe_id") Integer recipe_id, @Param("ingredient_id") Integer ingredient_id);

}
