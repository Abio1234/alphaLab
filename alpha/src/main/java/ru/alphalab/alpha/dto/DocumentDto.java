package ru.alphalab.alpha.dto;

import lombok.Data;
import ru.alphalab.alpha.db.model.DocumentType;

@Data
public class DocumentDto {

    private Long documentId;
    private HumanDto human;
    private String documentNumber;
    private DocumentType documentType;
    private Boolean statusActive;

}
