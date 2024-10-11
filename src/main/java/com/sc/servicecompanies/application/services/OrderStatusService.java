package com.sc.servicecompanies.application.services;

import com.sc.servicecompanies.domain.entities.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {

    List<OrderStatus> findAll();

    Optional<OrderStatus> findById(Long id);

    OrderStatus save(OrderStatus orderStatus);

    Optional<OrderStatus> update(Long id, OrderStatus orderStatus);

    Optional<OrderStatus> delete(Long id);

}
