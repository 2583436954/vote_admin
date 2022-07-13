package org.jeecg.modules.vote.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import org.jeecg.modules.vote.entity.VoteActivityTheme;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VoteActivityThemeVo extends VoteActivityTheme {
    private List<VoteActivityContentVo> voteActivityContentList;
}
