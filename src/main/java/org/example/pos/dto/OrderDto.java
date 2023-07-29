package org.example.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos.entity.Customer;
import org.example.pos.entity.OrderItem;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements SuperDto{
    private int id;
    private double total;
    private int quantity;
    private Date date;
    private CustomerDto customer;

}
