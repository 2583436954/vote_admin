package org.jeecg.modules.vote.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import org.jeecg.modules.vote.entity.VoteImage;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VoteActivityContentVo extends VoteActivityContent {
    private List<VoteImage> voteImageList;
    private String activityName;
    private String fileList;
}
