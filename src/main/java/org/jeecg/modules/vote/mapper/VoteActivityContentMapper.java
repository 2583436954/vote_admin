package org.jeecg.modules.vote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.vote.vo.VoteActivityContentVo;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;

/**
 * @Description: 活动内容
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
public interface VoteActivityContentMapper extends BaseMapper<VoteActivityContent> {


    VoteActivityContentVo getActivityContentById(String id);
}
