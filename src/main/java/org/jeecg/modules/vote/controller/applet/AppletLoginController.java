package org.jeecg.modules.vote.controller.applet;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.api.vo.Result;
import org.jeecg.modules.vote.constant.VoteCustomerConstant;
import org.jeecg.modules.vote.entity.VoteCustomer;
import org.jeecg.modules.vote.util.WeChatUtil;
import org.jeecg.modules.vote.util.oConvertUtils;
import org.jeecg.modules.vote.vo.VoteCustomerAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auther: wmc
 */
@Slf4j
@RestController
@RequestMapping("/applet/login")
public class AppletLoginController {
    @Autowired
    private WeChatUtil weChatUtil;
    @Autowired
    private org.jeecg.modules.vote.service.IVoteCustomerService IVoteCustomerService;

    /**
     * 客户端微信登陆
     *
     * @param voteCustomerAdd
     * @return
     */
    @PostMapping("/customerWxLogin")
    @ApiOperation("客户端微信登陆")
    public Result<?> customerWxLogin(VoteCustomerAdd voteCustomerAdd) {
        String code = voteCustomerAdd.getCode();
        log.info("客户端微信登陆"+code);
        // 根据 code 获取 open_id
        JSONObject sessionKeyOrOpenId = weChatUtil.getCustomSessionKeyOrOpenId(code);
        String openId = sessionKeyOrOpenId.getString("openid");
        String sessionKey = sessionKeyOrOpenId.getString("session_key");
        log.info("openId====="+openId);
        log.info("sessionKey====="+sessionKey);
        //判断openId是否已经绑定手机号
        VoteCustomer voteCustomer = null;
        //陌拜登陆
        if (openId != null) {
            VoteCustomer voteCustomerOne = new VoteCustomer();
            voteCustomerOne.setOpenId(openId);
            QueryWrapper<VoteCustomer> voteCustomerQueryWrapper = new QueryWrapper<VoteCustomer>(voteCustomerOne);
            voteCustomer = IVoteCustomerService.getOne(voteCustomerQueryWrapper);
        } else {
            return Result.error("服务器异常，请稍后重试");
        }

        //第一次登录,存入数据库
        if (oConvertUtils.isEmpty(voteCustomer)) {
            try {
                VoteCustomer voteCustomerOne = new VoteCustomer();
                voteCustomerOne.setOpenId(openId);
                voteCustomerOne.setPoll(1);
                voteCustomerOne.setIsStart(VoteCustomerConstant.IS_START_YES);
                voteCustomerOne.setIsDeleted("0");
                IVoteCustomerService.save(voteCustomerOne);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //检验账号是否有效
        if(null != voteCustomer && VoteCustomerConstant.IS_START_NO.equals(voteCustomer.getIsStart())){
            return Result.error("此账号已被禁用");
        }
        Result result = new Result();
        result.setMessage("登陆成功");
        result.setResult(openId);
        return result;
    }
}
