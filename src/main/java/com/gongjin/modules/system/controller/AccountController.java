package com.gongjin.modules.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gongjin.commom.MessageResp;
import com.gongjin.modules.system.domain.Account;
import com.gongjin.modules.system.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 账户接口控制类
 * 
 * @title
 * @author 龚进
 * @date 2018年8月27日
 * @version 1.0
 */
@Api(value = "账户信息管理", tags = "账户信息管理接口")
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * 添加账户
	 * 
	 * @param account
	 * @return
	 */
	@ApiOperation(value = "保存账户信息")
	@PostMapping
	public MessageResp<String> addAccount(@RequestBody Account account) {
		MessageResp<String> resp = new MessageResp<String>();
		try {
			boolean isSuccess = accountService.save(account);
			if (!isSuccess) {
				resp.setResult(Boolean.FALSE.toString());
				resp.setResultDesc("保存账户失败");
			}
		} catch (Exception e) {
			resp.setResult(Boolean.FALSE.toString());
			resp.setResultDesc("保存账户异常,请稍后重试");
			log.error("保存账户异常,请稍后重试");
			log.error(e.getMessage(), e);
		}

		return resp;
	}
	
	/**
	 * 修改账户
	 * 
	 * @param account
	 * @return
	 */
	@ApiOperation(value = "修改账户信息")
	@PutMapping
	public MessageResp<String> modifyAccount(@RequestBody Account account) {
		MessageResp<String> resp = new MessageResp<String>();
		try {
			boolean isSuccess = accountService.update(account);
			if (!isSuccess) {
				resp.setResult(Boolean.FALSE.toString());
				resp.setResultDesc("修改账户失败");
			}
		} catch (Exception e) {
			resp.setResult(Boolean.FALSE.toString());
			resp.setResultDesc("修改账户异常,请稍后重试");
			log.error("修改账户异常,请稍后重试");
			log.error(e.getMessage(), e);
		}

		return resp;
	}
}
