package ch.bzz.delish.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "quantity",
            fetch = FetchType.LAZY)
    @JsonBackReference("Recipe_Ingredient.quantity")
    private Set<Recipe_Ingredient> quantities = new HashSet<>();

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

    public Set<Recipe_Ingredient> getQuantities() {
        return quantities;
    }

    public void setQuantities(Set<Recipe_Ingredient> quantities) {
        this.quantities = quantities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return Objects.equals(id, quantity.id) && Objects.equals(name, quantity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
