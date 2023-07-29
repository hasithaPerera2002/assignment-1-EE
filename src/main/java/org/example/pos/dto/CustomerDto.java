package org.example.pos.dto;

import lombok.*;
import org.example.pos.entity.SuperEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto implements SuperEntity, SuperDto {
    private int id;
    private String name;
    private String address;
    private double salary;
}
