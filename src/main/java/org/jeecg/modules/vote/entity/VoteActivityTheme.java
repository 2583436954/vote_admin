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
 * @Description: 活动主题
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Data
@TableName("vote_activity_theme")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="vote_activity_theme对象", description="活动主题")
public class VoteActivityTheme implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**activityname*/
	@Excel(name = "activityName", width = 15)
    @ApiModelProperty(value = "activityName")
    private String activityName;
	/**是否开启活动 0开启 1关闭*/
	@Excel(name = "是否开启活动 0开启 1关闭", width = 15)
    @ApiModelProperty(value = "是否开启活动 0开启 1关闭")
    private String isStart;
	/**isDeleted*/
    @TableLogic
	@Excel(name = "isDeleted", width = 15)
    @ApiModelProperty(value = "isDeleted")
    private String isDeleted;
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
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
    private Date updateTime;
	/**comment*/
	@Excel(name = "comment", width = 15)
    @ApiModelProperty(value = "comment")
    private String comment;
}
