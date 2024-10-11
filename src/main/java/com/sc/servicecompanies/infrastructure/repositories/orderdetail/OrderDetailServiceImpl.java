package com.sc.servicecompanies.infrastructure.repositories.orderdetail;

import com.sc.servicecompanies.application.services.OrderDetailService;
import com.sc.servicecompanies.domain.entities.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    @Transactional
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
        if(orderDetailOld.isPresent()) {
            OrderDetail orderDetailDb = orderDetailOld.orElseThrow();

            orderDetailDb.setService(orderDetail.getService());
            orderDetailDb.setServiceOrders(orderDetail.getServiceOrders());
            orderDetailDb.setServiceValue(orderDetail.getServiceValue());
            return Optional.of(orderDetailRepository.save(orderDetailDb));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<OrderDetail> delete(Long id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        orderDetail.ifPresent(orderDetailDb -> {
            orderDetailRepository.delete(orderDetailDb);
        });
        return orderDetail;
    }
}