package org.example.pos.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class    ItemDto implements SuperDto {
    private int id;
    private String item_name;
    private int price;
    private int quantity;
}
