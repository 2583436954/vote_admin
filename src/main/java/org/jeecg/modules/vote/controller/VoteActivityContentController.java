package org.jeecg.modules.vote.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.vote.entity.VoteActivityContent;
import org.jeecg.modules.vote.service.IVoteActivityContentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 活动内容
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Api(tags="活动内容")
@RestController
@RequestMapping("/vote/voteActivityContent")
@Slf4j
public class VoteActivityContentController extends JeecgController<VoteActivityContent, IVoteActivityContentService> {
	@Autowired
	private IVoteActivityContentService voteActivityContentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param voteActivityContent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "活动内容-分页列表查询")
	@ApiOperation(value="活动内容-分页列表查询", notes="活动内容-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VoteActivityContent>> queryPageList(VoteActivityContent voteActivityContent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VoteActivityContent> queryWrapper = QueryGenerator.initQueryWrapper(voteActivityContent, req.getParameterMap());
		Page<VoteActivityContent> page = new Page<VoteActivityContent>(pageNo, pageSize);
		IPage<VoteActivityContent> pageList = voteActivityContentService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param voteActivityContent
	 * @return
	 */
	@AutoLog(value = "活动内容-添加")
	@ApiOperation(value="活动内容-添加", notes="活动内容-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody VoteActivityContent voteActivityContent) {
		voteActivityContentService.save(voteActivityContent);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param voteActivityContent
	 * @return
	 */
	@AutoLog(value = "活动内容-编辑")
	@ApiOperation(value="活动内容-编辑", notes="活动内容-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody VoteActivityContent voteActivityContent) {
		voteActivityContentService.updateById(voteActivityContent);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "活动内容-通过id删除")
	@ApiOperation(value="活动内容-通过id删除", notes="活动内容-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		voteActivityContentService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "活动内容-批量删除")
	@ApiOperation(value="活动内容-批量删除", notes="活动内容-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.voteActivityContentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "活动内容-通过id查询")
	@ApiOperation(value="活动内容-通过id查询", notes="活动内容-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VoteActivityContent> queryById(@RequestParam(name="id",required=true) String id) {
		VoteActivityContent voteActivityContent = voteActivityContentService.getById(id);
		if(voteActivityContent==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(voteActivityContent);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param voteActivityContent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VoteActivityContent voteActivityContent) {
        return super.exportXls(request, voteActivityContent, VoteActivityContent.class, "活动内容");
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
        return super.importExcel(request, response, VoteActivityContent.class);
    }

}
