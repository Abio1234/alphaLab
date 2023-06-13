package ru.alphalab.alpha.db.repository;

import org.springframework.stereotype.Repository;
import ru.alphalab.alpha.db.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
