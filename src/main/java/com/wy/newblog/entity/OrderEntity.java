package com.wy.newblog.entity;

import com.wy.newblog.base.BaseEntity;
import com.wy.newblog.entity.enums.IsPay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author
 * @Date 2018/10/29 9:54
 * @Description 订单
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "t_order")
@ApiModel("订单实体")
public class OrderEntity extends BaseEntity implements Serializable {


    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @Enumerated(value=EnumType.ORDINAL)
    @ApiModelProperty(value = "订单状态")
    private IsPay isPay;



}
