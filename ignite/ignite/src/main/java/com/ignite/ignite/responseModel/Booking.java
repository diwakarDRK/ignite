package com.ignite.ignite.responseModel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Booking {

    private String id;
    private String memberName;
    private String className;
    private LocalDate participationDate;

    public Booking() {
        this.id = UUID.randomUUID().toString();
    }

}
