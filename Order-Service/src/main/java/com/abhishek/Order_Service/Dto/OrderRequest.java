package com.abhishek.Order_Service.Dto;

import com.abhishek.Order_Service.Dto.OrderLineItemsDto;
//import com.abhishek.Order_Service.Model.OrderLineItemsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtos;
}
