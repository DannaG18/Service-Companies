package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {

    List<OrderDetail> findAll();
    Optional<OrderDetail> findById(Long id);
    OrderDetail save(OrderDetail orderDetail);
    Optional<OrderDetail> update(Long id, OrderDetail orderDetail);
    Optional<OrderDetail> delete(Long id);
}
