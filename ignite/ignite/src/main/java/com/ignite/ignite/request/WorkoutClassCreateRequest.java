package com.ignite.ignite.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class WorkoutClassCreateRequest {
    @NotBlank(message = "Class name cannot be blank")
    private String name;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @Positive(message = "Duration must be positive")
    private int durationMinutes;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;
}
