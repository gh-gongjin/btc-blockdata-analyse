package com.gongjin.modules.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gongjin.commom.BaseDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户表
 * 
 * @title
 * @author 龚进
 * @date 2018年8月27日
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_account_info")
public class Account extends BaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4741785716837622598L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "lately_login_time")
	private String latelyLoginTime;
}
