package ch.bbcag.fourchefs_backend.repositories;

import ch.bbcag.fourchefs_backend.models.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Integer> {

    Login findByUsername(String username);
}
