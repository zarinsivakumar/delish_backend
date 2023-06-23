package ch.bbcag.fourchefs_backend.repositories;

import ch.bbcag.fourchefs_backend.models.Quantity;
import org.springframework.data.repository.CrudRepository;

public interface QuantityRepository extends CrudRepository<Quantity, Integer> {
}
