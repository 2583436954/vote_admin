package org.jeecg.modules.vote.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.modules.vote.entity.VoteCustomer;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VoteCustomerAdd extends VoteCustomer {
    private String code;
    //头像地址
    private String avatarUrl;
    //
}
