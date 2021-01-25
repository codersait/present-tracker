package com.northpole.present_tracker.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.northpole.present_tracker.domain.Present;
import com.northpole.present_tracker.model.PresentDTO;
import com.northpole.present_tracker.repos.PresentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PresentService {

    private final PresentRepository presentRepository;

    public PresentService(final PresentRepository presentRepository) {
        this.presentRepository = presentRepository;
    }

    public List<PresentDTO> findAll() {
        return presentRepository.findAll()
                .stream()
                .map(present -> mapToDTO(present, new PresentDTO()))
                .collect(Collectors.toList());
    }

    public PresentDTO get(final Long id) {
        return presentRepository.findById(id)
                .map(present -> mapToDTO(present, new PresentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PresentDTO presentDTO) {
        final Present present = new Present();
        mapToEntity(presentDTO, present);
        return presentRepository.save(present).getId();
    }

    public void update(final Long id, final PresentDTO presentDTO) {
        final Present present = presentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(presentDTO, present);
        presentRepository.save(present);
    }

    public void delete(final Long id) {
        presentRepository.deleteById(id);
    }

    private PresentDTO mapToDTO(final Present present, final PresentDTO presentDTO) {
        presentDTO.setId(present.getId());
        presentDTO.setMinAge(present.getMinAge());
        presentDTO.setMaxAge(present.getMaxAge());
        presentDTO.setDescription(present.getDescription());
        return presentDTO;
    }

    private Present mapToEntity(final PresentDTO presentDTO, final Present present) {
        present.setMinAge(presentDTO.getMinAge());
        present.setMaxAge(presentDTO.getMaxAge());
        present.setDescription(presentDTO.getDescription());
        return present;
    }

}
