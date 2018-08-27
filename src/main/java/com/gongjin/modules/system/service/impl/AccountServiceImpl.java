package com.gongjin.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gongjin.commom.util.MD5Utils;
import com.gongjin.modules.system.domain.Account;
import com.gongjin.modules.system.mapper.AccountMapper;
import com.gongjin.modules.system.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public boolean save(Account account) throws Exception {
		account.setPassword(MD5Utils.getMD5String(account.getPassword()));
		account.preInsert();
		account = accountMapper.save(account);
		return account == null ? false : true;
	}
}
