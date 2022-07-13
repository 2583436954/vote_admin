package org.jeecg.modules.vote.service.impl;

import org.jeecg.modules.vote.entity.VoteActivityTheme;
import org.jeecg.modules.vote.mapper.VoteActivityThemeMapper;
import org.jeecg.modules.vote.service.IVoteActivityThemeService;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 活动主题
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Service
public class VoteActivityThemeServiceImpl extends ServiceImpl<VoteActivityThemeMapper, VoteActivityTheme> implements IVoteActivityThemeService {

    @Override
    public VoteActivityThemeVo getActivityThemeVo() {
        return this.baseMapper.getActivityThemeVo();
    }
}
