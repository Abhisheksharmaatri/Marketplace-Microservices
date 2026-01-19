package com.abhishek.Order_Service.Controller;

import com.abhishek.Order_Service.Dto.OrderRequest;
import com.abhishek.Order_Service.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody OrderRequest orderRequest){
        orderService.Create(orderRequest);
        return "order placed successfully";
    }
}
