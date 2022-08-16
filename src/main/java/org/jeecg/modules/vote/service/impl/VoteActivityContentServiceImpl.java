package org.jeecg.modules.vote.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import org.jeecg.modules.vote.entity.VoteCustomer;
import org.jeecg.modules.vote.mapper.VoteActivityContentMapper;
import org.jeecg.modules.vote.mapper.VoteCustomerMapper;
import org.jeecg.modules.vote.service.IVoteActivityContentService;
import org.jeecg.modules.vote.service.IVoteCustomerService;
import org.jeecg.modules.vote.vo.VoteActivityContentVo;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 活动内容
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Service
public class VoteActivityContentServiceImpl extends ServiceImpl<VoteActivityContentMapper, VoteActivityContent> implements IVoteActivityContentService {
    @Autowired
    private VoteCustomerMapper iVoteCustomerMapper;
    @Autowired
    private VoteActivityContentMapper iVoteActivityContentMapper;

    @Override
    public VoteActivityContentVo getActivityContentById(String id) {
        return this.baseMapper.getActivityContentById(id);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean goVote(VoteCustomer voteCustomer, VoteActivityContent voteActivityContent) {
        //先去减 再去增
        VoteCustomer voteCustomerUpdate = new VoteCustomer();
        voteCustomerUpdate.setId(voteCustomer.getId());
        voteCustomerUpdate.setPoll(voteCustomer.getPoll() - 1);
        VoteActivityContent voteActivityContentUpdate = new VoteActivityContent();
        voteActivityContentUpdate.setId(voteActivityContent.getId());
        voteActivityContentUpdate.setSumPoll(voteActivityContent.getSumPoll() + 1);
        iVoteCustomerMapper.updateById(voteCustomerUpdate);
        iVoteActivityContentMapper.updateById(voteActivityContentUpdate);
        return true;
    }

    @Override
    public String getNumber() {
        return this.baseMapper.getNumber();
    }

    @Override
    public IPage<VoteActivityContentVo> getPage(Page<VoteActivityContentVo> page, VoteActivityContent voteActivityContent) {
        return this.baseMapper.getPage(page,voteActivityContent);
    }
}
