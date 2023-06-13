package ru.alphalab.alpha.service;

import org.springframework.stereotype.Service;
import ru.alphalab.alpha.db.repository.HumanRepository;
import ru.alphalab.alpha.dto.HumanDto;
import ru.alphalab.alpha.mapper.HumanMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HumanService {

    private HumanRepository humanRepository;
    private HumanMapper humanMapper;

    public HumanService(HumanRepository humanRepository, HumanMapper humanMapper) {
        this.humanRepository = humanRepository;
        this.humanMapper = humanMapper;
    }

    public List<HumanDto> getHumansByDocumentNumberContains(String searchNumber) {
        var resultList = humanRepository.getHumansByDocumentNumberContains(searchNumber);
        return resultList.stream().map(humanMapper::toDto).collect(Collectors.toList());
    }
}
