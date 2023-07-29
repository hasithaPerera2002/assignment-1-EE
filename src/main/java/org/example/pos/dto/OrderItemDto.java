package org.example.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos.entity.Item;
import org.example.pos.entity.Order;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto implements SuperDto {
    private int id;
    private int qty;
    private OrderDto order;
    private ItemDto item;
}
