package com.sc.servicecompanies.infrastructure.repositories.orderdetail;

import com.sc.servicecompanies.domain.entities.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
