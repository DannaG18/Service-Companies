package com.sc.servicecompanies.application.services;

import java.util.List;
import java.util.Optional;

import com.sc.servicecompanies.domain.entities.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    Optional<OrderDetail> findById(Long id);
    OrderDetail save(OrderDetail orderDetail);
    Optional<OrderDetail> update(Long id, OrderDetail orderDetail);
    Optional<OrderDetail> delete(Long id);
}
