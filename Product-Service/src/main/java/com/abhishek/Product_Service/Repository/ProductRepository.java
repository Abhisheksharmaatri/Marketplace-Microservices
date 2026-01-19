package com.abhishek.Product_Service.Repository;

import com.abhishek.Product_Service.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
