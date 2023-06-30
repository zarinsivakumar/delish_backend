package ch.bzz.delish.repositories;

import ch.bzz.delish.models.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    @Query("SELECT i FROM Recipe i WHERE i.name LIKE CONCAT('%', :name, '%')")
    Iterable<Recipe> findByName(@Param("name") String name);
}
