package com.abhishek.Product_Service.Service;

import com.abhishek.Product_Service.Dto.ProductRequest;
import com.abhishek.Product_Service.Dto.ProductResponse;
import com.abhishek.Product_Service.Model.Product;
import com.abhishek.Product_Service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

//    public ProductService(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }
//    Not Required Any more as required args constructor annotation used

//    Creating dependency manually is bad
//    this.productRepository = new ProductRepository(); // ❌ wrong
//
//        Why?
//        Spring loses control
//        Hard to test
//        Tight coupling

    public void create(ProductRequest productRequest){
        Product product=Product
                .builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product with Id: {} was saved.", product.getId());
    }

    public List<ProductResponse> getAll(){
        List<Product> productList=productRepository.findAll();
//        List<ProductResponse> productResponseList=productList.stream().map(product -> {
//            return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
//        }).toList();
//        return productResponseList;

//        return productList.stream().map(product -> mapToProductResponse(product)).toList();
        return productList.stream().map(this::mapToProductResponse).toList();

    }

    public ProductResponse get(String id){
        Product product=productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Product with this Id doesn't exist."));
        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product){
        ProductResponse productResponse=ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        return productResponse;
    }
}
