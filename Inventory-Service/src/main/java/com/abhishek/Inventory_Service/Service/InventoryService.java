package com.abhishek.Inventory_Service.Service;


import com.abhishek.Inventory_Service.Dto.InventoryResponse;
import com.abhishek.Inventory_Service.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public Boolean Get(String skuCode){
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Transactional
    public List<InventoryResponse> Get(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .inStock(inventory.getQuantity()>0)
                            .build()
                )
                .toList();
    }


}
