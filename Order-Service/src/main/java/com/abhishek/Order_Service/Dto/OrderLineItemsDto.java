package com.abhishek.Order_Service.Dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_line_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}

