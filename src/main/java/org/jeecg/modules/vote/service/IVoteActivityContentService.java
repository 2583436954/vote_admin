package org.jeecg.modules.vote.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.vote.entity.VoteCustomer;
import org.jeecg.modules.vote.vo.VoteActivityContentVo;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;

/**
 * @Description: 活动内容
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
public interface IVoteActivityContentService extends IService<VoteActivityContent> {


    VoteActivityContentVo getActivityContentById(String id);

    boolean goVote(VoteCustomer voteCustomer, VoteActivityContent voteActivityContent);

    String getNumber();

    IPage<VoteActivityContentVo> getPage(Page<VoteActivityContentVo> page, VoteActivityContent voteActivityContent);
}
