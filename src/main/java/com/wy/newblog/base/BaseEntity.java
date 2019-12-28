package com.wy.newblog.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wy.newblog.common.utils.SnowFlakeUtil;
import com.wy.newblog.entity.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author wy
 * @Date 2018/9/10 14:38
 * @Description
 * @Version 1.0
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "唯一标识")
    private Long id = SnowFlakeUtil.getFlowIdInstance().nextId();

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(value = "状态")
    private Status status;
}
