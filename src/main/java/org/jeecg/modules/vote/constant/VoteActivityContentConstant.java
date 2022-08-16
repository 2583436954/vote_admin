package org.jeecg.modules.vote.constant;

public class VoteActivityContentConstant {
    public static final String SUM_POLL_INIT = "0";
    //审核状态 0 是未审核  1是 审核通过  2是审核失败 重新发起就是从新审核
    public static final String AUDIT_STATUS_WAIT = "0";
    public static final String AUDIT_STATUS_PASS = "1";
    public static final String AUDIT_STATUS_REFUSE = "2";
    // 删除  0是未删除  1是已经删除
    public static final String IS_DELETED_NO = "0";
    public static final String IS_DELETED_YES = "1";
}
