package ru.alphalab.alpha.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alphalab.alpha.db.model.Human;
import ru.alphalab.alpha.dto.HumanDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = DocumentMapper.class)
public interface HumanMapper {

    @Mapping(target = "humanId", source = "humanId")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "documents", source = "documents")
    Human toEntity(HumanDto dto);

    @Mapping(target = "humanId", source = "humanId")
    @Mapping(target = "fio", source = "fio")
    @Mapping(target = "documents", source = "documents")
    HumanDto toDto(Human entity);

    List<Human> toEntityList(List<HumanDto> dtoList);

    List<HumanDto> toDtoList(List<Human> entityList);
}
