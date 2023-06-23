package ch.bbcag.fourchefs_backend.repositories;

import ch.bbcag.fourchefs_backend.models.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    @Query("SELECT i FROM Recipe i WHERE i.name LIKE CONCAT('%', :name, '%')")
    Iterable<Recipe> findByName(@Param("name") String name);
}
