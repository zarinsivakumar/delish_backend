package ch.bbcag.fourchefs_backend.repositories;

import ch.bbcag.fourchefs_backend.models.Favorite;
import ch.bbcag.fourchefs_backend.models.Ingredient;
import ch.bbcag.fourchefs_backend.models.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {

    @Query("select i from Recipe i" +
            " join i.favorites ri" +
            " where ri.login.id = :id")
    Iterable<Recipe> findFavoriteRecipesByUserId(@Param("id") Integer id);

    @Transactional
    void deleteFavoriteByLoginIdAndRecipeId(Integer userId, Integer recipeId);

}
