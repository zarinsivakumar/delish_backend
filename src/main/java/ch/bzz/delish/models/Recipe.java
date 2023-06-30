package ch.bzz.delish.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "VARCHAR(10000)")
    private String instruction;
    private String image;
    private String time;
    private Integer rating;

    @OneToMany(mappedBy = "recipe",
            fetch = FetchType.LAZY)
    @JsonBackReference("Recipe.favorites")
    private Set<Favorite> favorites = new HashSet<>();

    @OneToMany(mappedBy = "recipe",
            fetch = FetchType.LAZY)
    @JsonBackReference("Recipe.recipes")
    private Set<Recipe_Ingredient> recipes = new HashSet<>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getRating() {

        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Set<Recipe_Ingredient> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe_Ingredient> recipes) {
        this.recipes = recipes;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(name, recipe.name) && Objects.equals(instruction, recipe.instruction) && Objects.equals(image, recipe.image) && Objects.equals(time, recipe.time) && Objects.equals(rating, recipe.rating) && Objects.equals(favorites, recipe.favorites) && Objects.equals(recipes, recipe.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instruction, image, time, rating, favorites, recipes);
    }
}
