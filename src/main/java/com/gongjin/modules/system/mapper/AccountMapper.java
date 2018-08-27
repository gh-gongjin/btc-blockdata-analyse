package com.gongjin.modules.system.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gongjin.modules.system.domain.Account;

/**
 * 账户数据库操作接口
 * @title 
 * @author 龚进
 * @date 2018年8月27日
 * @version 1.0
 */
public interface AccountMapper extends JpaRepository<Account, Long>{

}
