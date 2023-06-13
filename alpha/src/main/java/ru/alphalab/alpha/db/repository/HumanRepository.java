package ru.alphalab.alpha.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alphalab.alpha.db.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {

    @Query(
            value = "select h from Human as h left join fetch h.documents as d where d.documentNumber like %:searchNumber% and d.statusActive=true"
    )
    List<Human> getHumansByDocumentNumberContains(@Param("searchNumber") String searchNumber);
}
