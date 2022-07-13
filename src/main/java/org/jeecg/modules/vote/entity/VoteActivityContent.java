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
 * @Description: 活动内容
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Data
@TableName("vote_activity_content")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="vote_activity_content对象", description="活动内容")
public class VoteActivityContent implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**activityThemeId*/
	@Excel(name = "activityThemeId", width = 15)
    @ApiModelProperty(value = "activityThemeId")
    private String activityThemeId;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String phone;
	/**sumpoll*/
	@Excel(name = "sumPoll", width = 15)
    @ApiModelProperty(value = "sumPoll")
    private Integer sumPoll;
	/**简介*/
	@Excel(name = "简介", width = 15)
    @ApiModelProperty(value = "简介")
    private String briefIntroduction;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String address;
	/**活动参与人姓名*/
	@Excel(name = "活动参与人姓名", width = 15)
    @ApiModelProperty(value = "活动参与人姓名")
    private String name;
	/**审核状态 0 是未审核  1是 审核通过  2是审核失败 重新发起就是从新审核*/
	@Excel(name = "审核状态 0 是未审核  1是 审核通过  2是审核失败 重新发起就是从新审核", width = 15)
    @ApiModelProperty(value = "审核状态 0 是未审核  1是 审核通过  2是审核失败 重新发起就是从新审核")
    private String auditStatus;
	/**commnet*/
	@Excel(name = "comment", width = 15)
    @ApiModelProperty(value = "comment")
    private String comment;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
    private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
    private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM- HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
    private Date updateTime;
	/**isDeleted*/
    @TableLogic
	@Excel(name = "isDeleted", width = 15)
    @ApiModelProperty(value = "isDeleted")
    private String isDeleted;
    @ApiModelProperty(value = "编号")
    private String number;
}
