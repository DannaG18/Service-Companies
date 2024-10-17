package com.sc.servicecompanies.infrastructure.repositories.orderdetail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sc.servicecompanies.domain.entities.OrderDetail;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>{

}
