package com.gongjin.modules.system.service;

import com.gongjin.modules.system.domain.Account;

/**
 * 账户业务接口
 * 
 * @title
 * @author 龚进
 * @date 2018年8月27日
 * @version 1.0
 */
public interface AccountService {

	/**
	 * 保存账户信息
	 * @param account
	 * @return
	 */
	boolean save(Account account) throws Exception;
}
