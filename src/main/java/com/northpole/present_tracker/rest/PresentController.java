package com.northpole.present_tracker.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.northpole.present_tracker.model.PresentDTO;
import com.northpole.present_tracker.service.PresentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/presents", produces = MediaType.APPLICATION_JSON_VALUE)
public class PresentController {

    private final PresentService presentService;

    public PresentController(final PresentService presentService) {
        this.presentService = presentService;
    }

    @GetMapping
    public List<PresentDTO> getAllPresents() {
        return presentService.findAll();
    }

    @GetMapping("/{id}")
    public PresentDTO getPresent(@PathVariable final Long id) {
        return presentService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPresent(@RequestBody @Valid final PresentDTO presentDTO) {
        return presentService.create(presentDTO);
    }

    @PutMapping("/{id}")
    public void updatePresent(@PathVariable final Long id, @RequestBody @Valid final PresentDTO presentDTO) {
        presentService.update(id, presentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePresent(@PathVariable final Long id) {
        presentService.delete(id);
    }

}
