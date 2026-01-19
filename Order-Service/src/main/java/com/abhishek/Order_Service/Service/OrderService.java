package com.abhishek.Order_Service.Service;

import com.abhishek.Order_Service.Dto.InventoryResponse;
import com.abhishek.Order_Service.Dto.OrderRequest;
import com.abhishek.Order_Service.Model.Order;
import com.abhishek.Order_Service.Model.OrderLineItem;
//import com.abhishek.Order_Service.Model.OrderLineItemsDto;
import com.abhishek.Order_Service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.Order_Service.Dto.OrderLineItemsDto;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void Create(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItem=orderRequest.getOrderLineItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemList(orderLineItem);

        List<String> skuCodes=order.getOrderLineItemList()
                .stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

//        By default webclient sends a asynchronous request, in order to force a synchronous request we will use block.
         InventoryResponse[] inventoryResult=webClientBuilder.build().get()
//                .uri("http://localhost:8803/api/inventory",
                 .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        Boolean result=Arrays.stream(inventoryResult)
                .allMatch(InventoryResponse::isInStock);
        if(result) {
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("The require product is not in order");
        }
    }

    public OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItem orderLineItem=new OrderLineItem();

        orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItem.setPrice(orderLineItemsDto.getPrice());
        orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItem;
    }
}


