package ru.job4j.mapstuct.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressEntity {
    private int houseNo;
    private String city;
    private String state;
}
