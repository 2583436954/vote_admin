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
import org.jeecg.modules.vote.entity.VoteImage;
import org.jeecg.modules.vote.service.IVoteImageService;

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
 * @Description: 图片表
 * @Author: jeecg-boot
 * @Date:   2022-07-11
 * @Version: V1.0
 */
@Api(tags="图片表")
@RestController
@RequestMapping("/vote/voteImage")
@Slf4j
public class VoteImageController extends JeecgController<VoteImage, IVoteImageService> {
	@Autowired
	private IVoteImageService voteImageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param voteImage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "图片表-分页列表查询")
	@ApiOperation(value="图片表-分页列表查询", notes="图片表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VoteImage>> queryPageList(VoteImage voteImage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VoteImage> queryWrapper = QueryGenerator.initQueryWrapper(voteImage, req.getParameterMap());
		Page<VoteImage> page = new Page<VoteImage>(pageNo, pageSize);
		IPage<VoteImage> pageList = voteImageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param voteImage
	 * @return
	 */
	@AutoLog(value = "图片表-添加")
	@ApiOperation(value="图片表-添加", notes="图片表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody VoteImage voteImage) {
		voteImageService.save(voteImage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param voteImage
	 * @return
	 */
	@AutoLog(value = "图片表-编辑")
	@ApiOperation(value="图片表-编辑", notes="图片表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody VoteImage voteImage) {
		voteImageService.updateById(voteImage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "图片表-通过id删除")
	@ApiOperation(value="图片表-通过id删除", notes="图片表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		voteImageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "图片表-批量删除")
	@ApiOperation(value="图片表-批量删除", notes="图片表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.voteImageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "图片表-通过id查询")
	@ApiOperation(value="图片表-通过id查询", notes="图片表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VoteImage> queryById(@RequestParam(name="id",required=true) String id) {
		VoteImage voteImage = voteImageService.getById(id);
		if(voteImage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(voteImage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param voteImage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VoteImage voteImage) {
        return super.exportXls(request, voteImage, VoteImage.class, "图片表");
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
        return super.importExcel(request, response, VoteImage.class);
    }

}
