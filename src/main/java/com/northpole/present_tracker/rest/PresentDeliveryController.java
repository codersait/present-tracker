package com.northpole.present_tracker.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.northpole.present_tracker.model.PresentDeliveryDTO;
import com.northpole.present_tracker.service.PresentDeliveryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/presentDeliverys", produces = MediaType.APPLICATION_JSON_VALUE)
public class PresentDeliveryController {

    private final PresentDeliveryService presentDeliveryService;

    public PresentDeliveryController(final PresentDeliveryService presentDeliveryService) {
        this.presentDeliveryService = presentDeliveryService;
    }

    @GetMapping
    public List<PresentDeliveryDTO> getAllPresentDeliverys() {
        return presentDeliveryService.findAll();
    }

    @GetMapping("/{id}")
    public PresentDeliveryDTO getPresentDelivery(@PathVariable final Long id) {
        return presentDeliveryService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPresentDelivery(@RequestBody @Valid final PresentDeliveryDTO presentDeliveryDTO) {
        return presentDeliveryService.create(presentDeliveryDTO);
    }

    @PutMapping("/{id}")
    public void updatePresentDelivery(@PathVariable final Long id, @RequestBody @Valid final PresentDeliveryDTO presentDeliveryDTO) {
        presentDeliveryService.update(id, presentDeliveryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePresentDelivery(@PathVariable final Long id) {
        presentDeliveryService.delete(id);
    }

}
