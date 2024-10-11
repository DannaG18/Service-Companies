package com.sc.servicecompanies.infrastructure.repositories.orderstatus;

import com.sc.servicecompanies.domain.entities.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
}
