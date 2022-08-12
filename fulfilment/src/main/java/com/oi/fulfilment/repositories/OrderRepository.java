package com.oi.fulfilment.repositories;

import com.oi.fulfilment.model.UtilityOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<UtilityOrder, Long> {
}
