package com.sc.servicecompanies.infrastructure.repositories.orderdetail;

import org.springframework.data.repository.CrudRepository;

import com.sc.servicecompanies.domain.entities.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>{

}
