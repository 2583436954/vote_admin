package org.jeecg.modules.vote.service;

import org.jeecg.modules.vote.entity.VoteActivityTheme;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;

/**
 * @Description: 活动主题
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
public interface IVoteActivityThemeService extends IService<VoteActivityTheme> {

    VoteActivityThemeVo getActivityThemeVo();
}
