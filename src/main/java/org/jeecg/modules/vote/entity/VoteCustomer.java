package org.jeecg.modules.vote.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: vote_customer
 * @Author: jeecg-boot
 * @Date:   2022-07-08
 * @Version: V1.0
 */
@Data
@TableName("vote_customer")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="vote_customer对象", description="vote_customer")
public class VoteCustomer implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**真实姓名*/
	@Excel(name = "真实姓名", width = 15)
    @ApiModelProperty(value = "真实姓名")
    private java.lang.String realName;
	/**昵称*/
	@Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private java.lang.String nickName;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private java.lang.String phone;
	/**微信号*/
	@Excel(name = "微信号", width = 15)
    @ApiModelProperty(value = "微信号")
    private java.lang.String wxNumber;
	/**age*/
	@Excel(name = "age", width = 15)
    @ApiModelProperty(value = "age")
    private java.lang.Integer age;
	/**sex*/
	@Excel(name = "sex", width = 15)
    @ApiModelProperty(value = "sex")
    private java.lang.String sex;
	/**0是启用1是禁用*/
	@Excel(name = "0是启用1是禁用", width = 15)
    @ApiModelProperty(value = "0是启用1是禁用")
    private java.lang.String isStart;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
    private java.lang.String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
    private java.lang.String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
    private java.util.Date updateTime;
	/**0是逻辑未删除 1是逻辑删除*/
    @TableLogic
	@Excel(name = "0是逻辑未删除 1是逻辑删除", width = 15)
    @ApiModelProperty(value = "0是逻辑未删除 1是逻辑删除")
    private java.lang.String isDeleted;
	/**openId*/
	@Excel(name = "openId", width = 15)
    @ApiModelProperty(value = "openId")
    private java.lang.String openId;
    @ApiModelProperty(value = "可投票票数")
    private Integer poll;
}
