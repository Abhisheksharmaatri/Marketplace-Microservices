package com.abhishek.Inventory_Service.Controller;


import com.abhishek.Inventory_Service.Dto.InventoryResponse;
import com.abhishek.Inventory_Service.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

//    @GetMapping("/item")
//    @ResponseStatus(HttpStatus.OK)
//    public Boolean Get(@RequestParam("sku-code") String skuCode){
//        return inventoryService.Get(skuCode);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> Get(@RequestParam List<String> skuCode){
        return inventoryService.Get(skuCode);
    }
}
