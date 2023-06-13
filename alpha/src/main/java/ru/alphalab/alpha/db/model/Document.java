package ru.alphalab.alpha.db.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DOCUMENT")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "DOCUMENT_ID", unique = true)
    private Long documentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "HUMAN_ID")
    @NotNull
    private Human human;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "DOCUMENT_TYPE", length = 64)
    private DocumentType documentType;

    @Column(name = "IS_STATUS_ACTIVE")
    private boolean statusActive;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public boolean isStatusActive() {
        return statusActive;
    }

    public void setStatusActive(boolean statusActive) {
        this.statusActive = statusActive;
    }
}
