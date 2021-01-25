package com.northpole.present_tracker.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.northpole.present_tracker.model.ChildDTO;
import com.northpole.present_tracker.service.ChildService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/childs", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChildController {

    private final ChildService childService;

    public ChildController(final ChildService childService) {
        this.childService = childService;
    }

    @GetMapping
    public List<ChildDTO> getAllChilds() {
        return childService.findAll();
    }

    @GetMapping("/{id}")
    public ChildDTO getChild(@PathVariable final Long id) {
        return childService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createChild(@RequestBody @Valid final ChildDTO childDTO) {
        return childService.create(childDTO);
    }

    @PutMapping("/{id}")
    public void updateChild(@PathVariable final Long id, @RequestBody @Valid final ChildDTO childDTO) {
        childService.update(id, childDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChild(@PathVariable final Long id) {
        childService.delete(id);
    }

}
