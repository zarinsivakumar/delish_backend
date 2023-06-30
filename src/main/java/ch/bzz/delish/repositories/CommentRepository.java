package ch.bzz.delish.repositories;

import ch.bzz.delish.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
