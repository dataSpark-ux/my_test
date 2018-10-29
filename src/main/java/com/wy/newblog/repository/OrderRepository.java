package com.wy.newblog.repository;

import com.wy.newblog.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author
 * @Date 2018/10/29 10:29
 * @Description TODO
 * @Version 1.0
 */
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
