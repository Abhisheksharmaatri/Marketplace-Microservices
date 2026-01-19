package com.abhishek.Order_Service.Repository;

import com.abhishek.Order_Service.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
