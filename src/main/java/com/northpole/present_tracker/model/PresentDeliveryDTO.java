package com.northpole.present_tracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class PresentDeliveryDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String sungSong;

    @NotNull
    private Long child;

    @NotNull
    private Long present;

}
