package tr.com.burcualtinok.blog.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.burcualtinok.blog.model.Entry;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional

public interface EntryRepository extends CrudRepository<Entry, Integer>{
    @Query("SELECT e FROM Entry e")
    List<Entry> findAllEntries();

    @Query("SELECT e FROM Entry e WHERE id = :id")
    Entry findEntryById( @Param("id") Integer id);

    @Modifying
    @Query("DELETE FROM Entry WHERE id= :id")
    void deleteEntryById(@Param("id") Integer id);

    @Query("SELECT e FROM Entry e WHERE e.title LIKE %:title%")
    List<Entry> findByTitle(@Param("title") String title);
}
