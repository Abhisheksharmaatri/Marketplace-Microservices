package com.abhishek.Product_Service.Controller;


import com.abhishek.Product_Service.Dto.ProductRequest;
import com.abhishek.Product_Service.Dto.ProductResponse;
import com.abhishek.Product_Service.Model.Product;
import com.abhishek.Product_Service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

//    public ProductController(ProductService productService){
//        this.productService=productService;
//    }
//    Not Required Any more as required args constructor annotation used

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductRequest productRequest){
        productService.create(productRequest);
    }

//    @GetMapping("/item")
//    @ResponseStatus(HttpStatus.OK)
//    public ProductResponse get(@PathVariable("id") String id){
//        return productService.get(id);
//    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAll(){
        return productService.getAll();
    }
}
