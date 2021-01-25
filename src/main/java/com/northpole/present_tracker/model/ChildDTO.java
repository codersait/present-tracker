package com.northpole.present_tracker.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class ChildDTO {

    private Long id;

    @NotNull
    @Size(max = 1000)
    private String name;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Boolean didBehave;

}
