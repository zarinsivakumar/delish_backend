package ch.bzz.delish.repositories;

import ch.bzz.delish.models.Quantity;
import org.springframework.data.repository.CrudRepository;

public interface QuantityRepository extends CrudRepository<Quantity, Integer> {
}
