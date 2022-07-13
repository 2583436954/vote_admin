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
import org.jeecg.modules.api.vo.Result;
import org.jeecg.modules.vote.entity.VoteCustomer;
import org.jeecg.modules.vote.service.IVoteCustomerService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: vote_customer
 * @Author: jeecg-boot
 * @Date:   2022-07-08
 * @Version: V1.0
 */
@Api(tags="vote_customer")
@RestController
@RequestMapping("/vote/voteCustomer")
@Slf4j
public class VoteCustomerController {
	@Autowired
	private IVoteCustomerService voteCustomerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param voteCustomer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "vote_customer-分页列表查询")
	@ApiOperation(value="vote_customer-分页列表查询", notes="vote_customer-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VoteCustomer voteCustomer,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													 HttpServletRequest req) {

		return Result.ok();
	}
	
	/**
	 *   添加
	 *
	 * @param voteCustomer
	 * @return
	 */
	@ApiOperation(value="vote_customer-添加", notes="vote_customer-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VoteCustomer voteCustomer) {
		voteCustomerService.save(voteCustomer);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param voteCustomer
	 * @return
	 */
	@ApiOperation(value="vote_customer-编辑", notes="vote_customer-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody VoteCustomer voteCustomer) {
		voteCustomerService.updateById(voteCustomer);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="vote_customer-通过id删除", notes="vote_customer-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		voteCustomerService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="vote_customer-批量删除", notes="vote_customer-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.voteCustomerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "vote_customer-通过id查询")
	@ApiOperation(value="vote_customer-通过id查询", notes="vote_customer-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VoteCustomer voteCustomer = voteCustomerService.getById(id);
		if(voteCustomer==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(voteCustomer);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param voteCustomer
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VoteCustomer voteCustomer) {
        return super.exportXls(request, voteCustomer, VoteCustomer.class, "vote_customer");
    }*/

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, VoteCustomer.class);
    }*/

}
