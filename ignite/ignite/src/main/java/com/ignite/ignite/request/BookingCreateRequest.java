package com.ignite.ignite.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingCreateRequest {
    @NotBlank(message = "Member name cannot be blank")
    private String memberName;

    @NotBlank(message = "Class name cannot be blank")
    private String className;

    @NotNull(message = "Participation date is required")
    @Future(message = "Participation date must be in the future")
    private LocalDate participationDate;
}
