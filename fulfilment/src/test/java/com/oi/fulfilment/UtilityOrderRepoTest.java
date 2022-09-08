package com.oi.fulfilment;


import com.oi.fulfilment.model.UtilityOrder;
import com.oi.fulfilment.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UtilityOrderRepoTest {

    @MockBean
    private OrderRepository orderRepository;


    @Test
    void testOrderRegister() {

        UtilityOrder utilityOrder = new UtilityOrder();
        utilityOrder.setInstallationDate("2022-09-12");
        utilityOrder.setAddress("");
        utilityOrder.setName("James Brown");
        utilityOrder.setProductDetails("Internet 250Mbps or 1Gbps");
        utilityOrder.setId(1l);

        when(orderRepository.save(any(UtilityOrder.class))).thenReturn(utilityOrder);
    }

}
