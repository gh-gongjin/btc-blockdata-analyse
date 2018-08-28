package com.gongjin.modules.system.service;

import org.springframework.data.domain.Page;

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
	Account queryById(Long id) throws Exception;

	/**
	 * 分页查询账户信息
	 * @param account
	 * @return
	 */
	Page<Account> queryPage(Account account) throws Exception;

	/**
	 * 验证账户信息
	 * @param account
	 * @return
	 */
	boolean validateAccount(Account account) throws Exception;
	
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 * @throws Exception
	 */
	Account queryByUsername(String username) throws Exception;
}
