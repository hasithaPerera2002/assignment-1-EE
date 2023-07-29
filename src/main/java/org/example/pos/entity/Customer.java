package org.example.pos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private double salary;
    @OneToMany(mappedBy = "customer",targetEntity = Order.class)
    @ToString.Exclude
    private List <Order> orders=new ArrayList<>();
}
