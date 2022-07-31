package com.github.andylke.demo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {

  //  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  @Query("SELECT a FROM Order a WHERE a.customerId=?1")
  List<Order> findAllByCustomerId(Long customerId);

  @Modifying
  @Query("DELETE FROM Order a WHERE a.customerId=?1 AND a.orderId=?2")
  void delete(Long customerId, Long orderId);
}
