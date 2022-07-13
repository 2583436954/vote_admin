package org.jeecg.modules.vote.controller.applet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.api.vo.Result;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import org.jeecg.modules.vote.entity.VoteActivityTheme;
import org.jeecg.modules.vote.entity.VoteCustomer;
import org.jeecg.modules.vote.service.IVoteActivityContentService;
import org.jeecg.modules.vote.service.IVoteActivityThemeService;
import org.jeecg.modules.vote.service.IVoteCustomerService;
import org.jeecg.modules.vote.vo.VoteActivityContentVo;
import org.jeecg.modules.vote.vo.VoteActivityThemeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/applet/activity")
public class AppletActivityController {
    @Autowired
    private IVoteCustomerService iVoteCustomerService;
    @Autowired
    private IVoteActivityContentService iVoteActivityContentService;
    @Autowired
    private IVoteActivityThemeService iVoteActivityThemeService;
    @GetMapping("/getMessageByOpenId")
    public Result<?> getMessageByOpenId(String openId){
        //先检查openId
        if(null == openId || "".equals(openId)){
            return Result.error("请重新登录");
        }
        VoteCustomer voteCustomerOne = new VoteCustomer();
        voteCustomerOne.setOpenId(openId);
        QueryWrapper<VoteCustomer> voteCustomerQueryWrapper = new QueryWrapper<VoteCustomer>(voteCustomerOne);
        VoteCustomer voteCustomer = iVoteCustomerService.getOne(voteCustomerQueryWrapper);
        if(null == voteCustomer){
            return Result.error("请重新登录");
        }

        //数据用map 发送
        Map<String,Object> map = new HashMap<String,Object>();
        //先去得到活动的信息 和 图片
        VoteActivityThemeVo voteActivityThemeVo = iVoteActivityThemeService.getActivityThemeVo();
        map.put("voteActivityThemeVo",voteActivityThemeVo);
        map.put("voteCustomer",voteCustomer);
        return Result.ok(map);
    }
    @GetMapping("/getActivityContentById")
    public Result<?> getActivityContentById(String id){
        VoteActivityContentVo voteActivityThemeVo = iVoteActivityContentService.getActivityContentById(id);
        return Result.ok(voteActivityThemeVo);
    }
    /**
     * 去校验登录  根据openId
     */
    @GetMapping("isLoginByOpenId")
    public Result<?> isLoginByOpenId(String openId){
        //先检查openId
        if(null == openId || "".equals(openId)){
            return Result.ok(false);
        }
        VoteCustomer voteCustomerOne = new VoteCustomer();
        voteCustomerOne.setOpenId(openId);
        QueryWrapper<VoteCustomer> voteCustomerQueryWrapper = new QueryWrapper<VoteCustomer>(voteCustomerOne);
        VoteCustomer voteCustomer = iVoteCustomerService.getOne(voteCustomerQueryWrapper);
        if(null == voteCustomer){
            return Result.ok(false);
        }
        return Result.ok(true);
    }
    /**
     * 去投票
     */
    @GetMapping("goVote")
    public  Result<?> goVote(String openId,String activityContentId){
        //先检查openId
        if(null == openId || "".equals(openId)){
            return Result.error("请重新登录");
        }
        VoteCustomer voteCustomerOne = new VoteCustomer();
        voteCustomerOne.setOpenId(openId);
        QueryWrapper<VoteCustomer> voteCustomerQueryWrapper = new QueryWrapper<VoteCustomer>(voteCustomerOne);
        VoteCustomer voteCustomer = iVoteCustomerService.getOne(voteCustomerQueryWrapper);
        if(null == voteCustomer){
            return Result.error("请重新登录");
        }
        if(voteCustomer.getPoll() <= 0){
            return Result.error("票数不足");
        }
        boolean isSuccess;
        synchronized(String.class){
            VoteActivityContent voteActivityContent = iVoteActivityContentService.getById(activityContentId);
            isSuccess = iVoteActivityContentService.goVote(voteCustomer,voteActivityContent);
        }
        if(!isSuccess){
            return Result.ok("投票失败");
        }
        return Result.ok("投票成功");
    }
    @GetMapping("/getVoteCustomerByOpenId")
    public Result<?> getVoteCustomerByOpenId(String openId){
        //先检查openId
        if(null == openId || "".equals(openId)){
            return Result.error("请重新登录");
        }
        VoteCustomer voteCustomerOne = new VoteCustomer();
        voteCustomerOne.setOpenId(openId);
        QueryWrapper<VoteCustomer> voteCustomerQueryWrapper = new QueryWrapper<VoteCustomer>(voteCustomerOne);
        VoteCustomer voteCustomer = iVoteCustomerService.getOne(voteCustomerQueryWrapper);
        if(null == voteCustomer){
            return Result.error("请重新登录");
        }

        return Result.ok(voteCustomer);
    }

}
