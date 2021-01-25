package com.northpole.present_tracker.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.northpole.present_tracker.domain.Child;
import com.northpole.present_tracker.model.ChildDTO;
import com.northpole.present_tracker.repos.ChildRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(final ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<ChildDTO> findAll() {
        return childRepository.findAll()
                .stream()
                .map(child -> mapToDTO(child, new ChildDTO()))
                .collect(Collectors.toList());
    }

    public ChildDTO get(final Long id) {
        return childRepository.findById(id)
                .map(child -> mapToDTO(child, new ChildDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ChildDTO childDTO) {
        final Child child = new Child();
        mapToEntity(childDTO, child);
        return childRepository.save(child).getId();
    }

    public void update(final Long id, final ChildDTO childDTO) {
        final Child child = childRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(childDTO, child);
        childRepository.save(child);
    }

    public void delete(final Long id) {
        childRepository.deleteById(id);
    }

    private ChildDTO mapToDTO(final Child child, final ChildDTO childDTO) {
        childDTO.setId(child.getId());
        childDTO.setName(child.getName());
        childDTO.setBirthDate(child.getBirthDate());
        childDTO.setDidBehave(child.getDidBehave());
        return childDTO;
    }

    private Child mapToEntity(final ChildDTO childDTO, final Child child) {
        child.setName(childDTO.getName());
        child.setBirthDate(childDTO.getBirthDate());
        child.setDidBehave(childDTO.getDidBehave());
        return child;
    }

}
