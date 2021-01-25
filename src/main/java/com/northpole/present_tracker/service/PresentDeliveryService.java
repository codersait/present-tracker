package com.northpole.present_tracker.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.northpole.present_tracker.domain.Child;
import com.northpole.present_tracker.domain.Present;
import com.northpole.present_tracker.domain.PresentDelivery;
import com.northpole.present_tracker.model.PresentDeliveryDTO;
import com.northpole.present_tracker.repos.ChildRepository;
import com.northpole.present_tracker.repos.PresentDeliveryRepository;
import com.northpole.present_tracker.repos.PresentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PresentDeliveryService {

    private final PresentDeliveryRepository presentDeliveryRepository;
    private final ChildRepository childRepository;
    private final PresentRepository presentRepository;

    public PresentDeliveryService(final PresentDeliveryRepository presentDeliveryRepository,
                                  final ChildRepository childRepository,
                                  final PresentRepository presentRepository) {
        this.presentDeliveryRepository = presentDeliveryRepository;
        this.childRepository = childRepository;
        this.presentRepository = presentRepository;
    }

    public List<PresentDeliveryDTO> findAll() {
        return presentDeliveryRepository.findAll()
                .stream()
                .map(presentDelivery -> mapToDTO(presentDelivery, new PresentDeliveryDTO()))
                .collect(Collectors.toList());
    }

    public PresentDeliveryDTO get(final Long id) {
        return presentDeliveryRepository.findById(id)
                .map(presentDelivery -> mapToDTO(presentDelivery, new PresentDeliveryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PresentDeliveryDTO presentDeliveryDTO) {
        final PresentDelivery presentDelivery = new PresentDelivery();
        mapToEntity(presentDeliveryDTO, presentDelivery);
        return presentDeliveryRepository.save(presentDelivery).getId();
    }

    public void update(final Long id, final PresentDeliveryDTO presentDeliveryDTO) {
        final PresentDelivery presentDelivery = presentDeliveryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(presentDeliveryDTO, presentDelivery);
        presentDeliveryRepository.save(presentDelivery);
    }

    public void delete(final Long id) {
        presentDeliveryRepository.deleteById(id);
    }

    private PresentDeliveryDTO mapToDTO(final PresentDelivery presentDelivery, final PresentDeliveryDTO presentDeliveryDTO) {
        presentDeliveryDTO.setId(presentDelivery.getId());
        presentDeliveryDTO.setSungSong(presentDelivery.getSungSong());
        presentDeliveryDTO.setChild(presentDelivery.getChild() == null ? null : presentDelivery.getChild().getId());
        presentDeliveryDTO.setPresent(presentDelivery.getPresent() == null ? null : presentDelivery.getPresent().getId());
        return presentDeliveryDTO;
    }

    private PresentDelivery mapToEntity(final PresentDeliveryDTO presentDeliveryDTO, final PresentDelivery presentDelivery) {
        presentDelivery.setSungSong(presentDeliveryDTO.getSungSong());
        if (presentDeliveryDTO.getChild() != null &&
                (presentDelivery.getChild() == null || !presentDelivery.getChild().getId().equals(presentDeliveryDTO.getChild()))) {
            final Child child = childRepository.findById(presentDeliveryDTO.getChild())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            presentDelivery.setChild(child);
        }
        if (presentDeliveryDTO.getPresent() != null &&
                (presentDelivery.getPresent() == null || !presentDelivery.getPresent().getId().equals(presentDeliveryDTO.getPresent()))) {
            final Present present = presentRepository.findById(presentDeliveryDTO.getPresent())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            presentDelivery.setPresent(present);
        }
        return presentDelivery;
    }

}
