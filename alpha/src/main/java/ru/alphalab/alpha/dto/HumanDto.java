package ru.alphalab.alpha.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class HumanDto {

    private Long humanId;
    private String fio;
    private Set<DocumentDto> documents = new HashSet<>();

}
