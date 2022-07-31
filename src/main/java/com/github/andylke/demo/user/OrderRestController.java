package com.github.andylke.demo.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orders")
public class OrderRestController {

  @Autowired private OrderRepository repository;

  @GetMapping
  public List<Order> findOrders(@RequestParam Long customerId) {
    return repository.findAllByCustomerId(customerId);
  }

  @PutMapping(path = "/{customerId}")
  @Transactional
  public List<Order> deleteAndRecreate(@PathVariable Long customerId) {
    List<Order> existingOrders = repository.findAllByCustomerId(customerId);

    for (Order existingOrder : existingOrders) {
      repository.delete(existingOrder.getCustomerId(), existingOrder.getOrderId());
    }

    List<Order> newOrders = new ArrayList<>();
    for (Order existingOrder : existingOrders) {

      Order newOrder = new Order();
      newOrder.setCustomerId(customerId);
      newOrder.setOrderId(existingOrder.getOrderId());
      // add original deleted record id - StaleStateException
      // newOrder.setOrderId(10L);
      newOrder.setOrderAmount(existingOrder.getOrderAmount().add(new BigDecimal(100)));

      newOrders.add(newOrder);
    }

    repository.saveAll(newOrders);
    return newOrders;
  }
}
