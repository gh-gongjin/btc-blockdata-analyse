package com.gongjin.modules.system.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public boolean update(Account account) throws Exception {
		if(StringUtils.isNotBlank(account.getPassword())) {
			account.setPassword(MD5Utils.getMD5String(account.getPassword()));
		}
		account.preUpdate();
		account = accountMapper.saveAndFlush(account);
		return account == null ? false : true;
	}

	@Override
	public Account query(Account account) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> queryPage(Account account) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
