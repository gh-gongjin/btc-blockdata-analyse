package com.gongjin.modules.system.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		if (StringUtils.isNotBlank(account.getPassword())) {
			account.setPassword(MD5Utils.getMD5String(account.getPassword()));
		}
		account.preUpdate();
		account = accountMapper.saveAndFlush(account);
		return account == null ? false : true;
	}

	@Override
	public Account queryById(Long id) throws Exception {
		Optional<Account> account = accountMapper.findById(id);
		return account.get();
	}

	@Override
	public Page<Account> queryPage(Account account) throws Exception {
		Example<Account> example = new Example<Account>() {
			@Override
			public ExampleMatcher getMatcher() {
				ExampleMatcher matcher = ExampleMatcher.matching();
				return matcher;
			}

			@Override
			public Account getProbe() {
				return account;
			}
		};
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
		Page<Account> page = accountMapper.findAll(example, pageable);
		return page;
	}

	@Override
	public boolean validateAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account queryByUsername(String username) throws Exception {
		Example<Account> example = new Example<Account>() {
			@Override
			public ExampleMatcher getMatcher() {
				ExampleMatcher matcher = ExampleMatcher.matching();
				return matcher;
			}

			@Override
			public Account getProbe() {
				Account account = new Account();
				account.setUsername(username);
				return account;
			}
		};
		Optional<Account> account = accountMapper.findOne(example);
		return account.get();
	}
}
