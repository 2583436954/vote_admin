package org.jeecg.modules.vote.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.vote.constant.VoteActivityThemeConstant;
import org.jeecg.modules.vote.entity.VoteActivityTheme;
import org.jeecg.modules.vote.service.IVoteActivityThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 活动主题
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Api(tags="活动主题")
@RestController
@RequestMapping("/vote/voteActivityTheme")
@Slf4j
public class VoteActivityThemeController extends JeecgController<VoteActivityTheme, IVoteActivityThemeService> {
	@Autowired
	private IVoteActivityThemeService voteActivityThemeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param voteActivityTheme
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "活动主题-分页列表查询")
	@ApiOperation(value="活动主题-分页列表查询", notes="活动主题-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VoteActivityTheme>> queryPageList(VoteActivityTheme voteActivityTheme,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VoteActivityTheme> queryWrapper = QueryGenerator.initQueryWrapper(voteActivityTheme, req.getParameterMap());
		Page<VoteActivityTheme> page = new Page<VoteActivityTheme>(pageNo, pageSize);
		IPage<VoteActivityTheme> pageList = voteActivityThemeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param voteActivityTheme
	 * @return
	 */
	@AutoLog(value = "活动主题-添加")
	@ApiOperation(value="活动主题-添加", notes="活动主题-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody VoteActivityTheme voteActivityTheme) {
		voteActivityTheme.setIsStart(VoteActivityThemeConstant.IS_START_NO);
		voteActivityTheme.setIsDeleted(VoteActivityThemeConstant.IS_DELETED_NO);
		voteActivityThemeService.save(voteActivityTheme);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param voteActivityTheme
	 * @return
	 */
	@AutoLog(value = "活动主题-编辑")
	@ApiOperation(value="活动主题-编辑", notes="活动主题-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody VoteActivityTheme voteActivityTheme) {
		voteActivityThemeService.updateById(voteActivityTheme);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "活动主题-通过id删除")
	@ApiOperation(value="活动主题-通过id删除", notes="活动主题-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		voteActivityThemeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "活动主题-批量删除")
	@ApiOperation(value="活动主题-批量删除", notes="活动主题-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.voteActivityThemeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "活动主题-通过id查询")
	@ApiOperation(value="活动主题-通过id查询", notes="活动主题-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VoteActivityTheme> queryById(@RequestParam(name="id",required=true) String id) {
		VoteActivityTheme voteActivityTheme = voteActivityThemeService.getById(id);
		if(voteActivityTheme==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(voteActivityTheme);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param voteActivityTheme
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VoteActivityTheme voteActivityTheme) {
        return super.exportXls(request, voteActivityTheme, VoteActivityTheme.class, "活动主题");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, VoteActivityTheme.class);
    }
	 /**
	  * 分页列表查询
	  *
	  * @param voteActivityTheme
	  * @param req
	  * @return
	  */
	 //@AutoLog(value = "活动主题-分页列表查询")
	 @ApiOperation(value="活动主题-分页列表查询", notes="活动主题-分页列表查询")
	 @GetMapping(value = "/getList")
	 public Result<?> getList(VoteActivityTheme voteActivityTheme,
														   HttpServletRequest req) {
		 List<VoteActivityTheme> pageList = voteActivityThemeService.list(new QueryWrapper<VoteActivityTheme>().eq("is_start", VoteActivityThemeConstant.IS_START_YES));
		 return Result.OK(pageList);
	 }
	 @ApiOperation(value="活动主题-分页列表查询", notes="活动主题-分页列表查询")
	 @GetMapping(value = "/getVoteActivityThemeList")
	 public Result<?> getVoteActivityThemeList(VoteActivityTheme voteActivityTheme,
							  HttpServletRequest req) {
		 List<VoteActivityTheme> pageList = voteActivityThemeService.list();
		 return Result.ok(pageList);
	 }
	 @ApiOperation(value="活动主题-开启关闭活动", notes="活动主题-开启关闭活动")
	 @GetMapping(value = "/editIsStart")
	 public Result<?> editIsStart(String id, HttpServletRequest req) {
		 log.info("editIsStart");
		 VoteActivityTheme voteActivityTheme = voteActivityThemeService.getById(id);
		 //此时是关闭，去开启
		 if(VoteActivityThemeConstant.IS_START_NO.equals(voteActivityTheme.getIsStart())){
			List<VoteActivityTheme> voteActivityThemeListUpdate = voteActivityThemeService.list(new QueryWrapper<VoteActivityTheme>().eq("is_start",VoteActivityThemeConstant.IS_START_YES));
			if(voteActivityThemeListUpdate.size() > 0){
				for(VoteActivityTheme voteActivityThemeOne : voteActivityThemeListUpdate){
					voteActivityThemeOne.setIsStart(VoteActivityThemeConstant.IS_START_NO);
				}
			}
			 VoteActivityTheme voteActivityThemeUpdate = new VoteActivityTheme();
			 voteActivityThemeUpdate.setId(voteActivityTheme.getId());
			 voteActivityThemeUpdate.setIsStart(VoteActivityThemeConstant.IS_START_YES);
			 voteActivityThemeListUpdate.add(voteActivityThemeUpdate);
			 voteActivityThemeService.updateBatchById(voteActivityThemeListUpdate);
		 }
		 //此时是开启，去关闭
		 if(VoteActivityThemeConstant.IS_START_YES.equals(voteActivityTheme.getIsStart())){
			 VoteActivityTheme voteActivityThemeUpdate = new VoteActivityTheme();
			 voteActivityThemeUpdate.setId(voteActivityTheme.getId());
			 voteActivityThemeUpdate.setIsStart(VoteActivityThemeConstant.IS_START_NO);
			 voteActivityThemeService.updateById(voteActivityThemeUpdate);
		 }
		 return Result.ok("操作成功");
	 }

}
