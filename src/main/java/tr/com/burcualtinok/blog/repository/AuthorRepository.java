package tr.com.burcualtinok.blog.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.burcualtinok.blog.model.Author;
import tr.com.burcualtinok.blog.model.Entry;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public interface AuthorRepository extends CrudRepository<Author, Integer>{


    @Query("SELECT a FROM Author a")
    List<Author> findAllAuthors();
}
