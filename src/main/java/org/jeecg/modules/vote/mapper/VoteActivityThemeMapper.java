package org.jeecg.modules.vote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.vote.entity.VoteActivityTheme;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;

/**
 * @Description: 活动主题
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
public interface VoteActivityThemeMapper extends BaseMapper<VoteActivityTheme> {

    VoteActivityThemeVo getActivityThemeVo();
}
