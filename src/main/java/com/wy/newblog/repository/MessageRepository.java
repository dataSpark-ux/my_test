package com.wy.newblog.repository;

import com.wy.newblog.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wy
 * @Date 2018/9/18 17:10
 * @Description 信息
 * @Version 1.0
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {


}
