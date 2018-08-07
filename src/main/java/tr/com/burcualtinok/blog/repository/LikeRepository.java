package tr.com.burcualtinok.blog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.burcualtinok.blog.model.Like;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {
    @Query("SELECT l FROM Like l")
    List<Like> findAllLikes();


}
