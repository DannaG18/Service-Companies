package com.sc.servicecompanies.infrastructure.repositories.orderdetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sc.servicecompanies.application.services.OrderDetailService;
import com.sc.servicecompanies.domain.entities.OrderDetail;

public class OrderDetailImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    @Override
    public Optional<OrderDetail> delete(Long id) {
        Optional<OrderDetail> orderDetailOp = orderDetailRepository.findById(id);
        orderDetailOp.ifPresent(orderDetailDb -> {
            orderDetailRepository.delete(orderDetailDb);
        });
        return orderDetailOp;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Transactional
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional
    @Override
    public Optional<OrderDetail> update(Long id, OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailOld = orderDetailRepository.findById(id);
        if (orderDetailOld.isPresent()) {
            OrderDetail orderDetailDb = orderDetailOld.orElseThrow();
            orderDetailDb.setServiceOrder(orderDetail.getServiceOrder());
            orderDetailDb.setService(orderDetail.getService());
            orderDetailDb.setServiceValue(orderDetail.getServiceValue());
            return Optional.of(orderDetailRepository.save(orderDetailDb));
        }
        return Optional.empty();
    }
}
