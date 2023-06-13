package ru.alphalab.alpha.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alphalab.alpha.db.model.Document;
import ru.alphalab.alpha.dto.DocumentDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target = "human", ignore = true)
    @Mapping(target = "documentId", source = "documentId")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "statusActive", source = "statusActive")
    Document toEntityDocument(DocumentDto dto);

    @Mapping(target = "human", ignore = true)
    @Mapping(target = "documentId", source = "documentId")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "statusActive", source = "statusActive")
    DocumentDto toDocumentDto(Document entity);

    List<Document> toEntityList(List<DocumentDto> dtoList);

    List<DocumentDto> toDtoList(List<Document> entityList);
}
