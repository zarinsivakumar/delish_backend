package ch.bbcag.fourchefs_backend.repositories;

import ch.bbcag.fourchefs_backend.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
