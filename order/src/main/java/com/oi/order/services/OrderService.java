package com.oi.order.services;


import com.oi.order.model.UtilityOrder;
import com.oi.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements CrudService<UtilityOrder, Long> {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<UtilityOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public UtilityOrder findById(Long aLong) {
        return orderRepository.findById(aLong).orElse(null);
    }

    @Override
    public UtilityOrder save(UtilityOrder object) {
        return orderRepository.save(object);
    }

    @Override
    public void delete(UtilityOrder object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Page<UtilityOrder> findAllByPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
