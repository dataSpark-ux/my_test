package com.wy.newblog.entity;

import com.wy.newblog.base.BaseEntity;
import com.wy.newblog.entity.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wy
 * @Date 2018/9/18 16:43
 * @Description 短信实体类
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "t_message")
@ApiModel("消息实体")
public class MessageEntity extends BaseEntity {

    @ApiModelProperty(value = "消息接收者")
    private String msgTo;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "来源")
    private String source;
    @ApiModelProperty(value = "备注")
    private String memo;

}
