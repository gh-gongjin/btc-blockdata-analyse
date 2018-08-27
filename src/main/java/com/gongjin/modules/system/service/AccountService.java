package com.gongjin.modules.system.service;

import java.util.List;

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

	/**
	 * 更新账户信息
	 * @param account
	 * @return
	 */
	boolean update(Account account) throws Exception;

	/**
	 * 查询账户信息
	 * @param account
	 * @return
	 */
	Account query(Account account) throws Exception;

	/**
	 * 分页查询账户信息
	 * @param account
	 * @return
	 */
	List<Account> queryPage(Account account) throws Exception;

	/**
	 * 验证账户信息
	 * @param account
	 * @return
	 */
	boolean validateAccount(Account account) throws Exception;
}
