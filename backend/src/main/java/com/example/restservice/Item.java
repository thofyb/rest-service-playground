package com.example.restservice;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    @NonNull
    private Integer id;

    @NonNull
    private String desc;

    @NonNull
    private Double price;

    public Item(@NonNull Item item) {
        id = item.getId();
        desc = item.getDesc();
        price = item.getPrice();
    }
}
