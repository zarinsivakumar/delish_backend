package ch.bzz.delish.repositories;

import ch.bzz.delish.models.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Integer> {

    Login findByUsername(String username);
}
