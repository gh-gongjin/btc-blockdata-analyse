package com.gongjin.commom;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * 基础实体类，封装一些公用字段
 * 
 * @title
 * @author 龚进
 * @date 2017年11月2日
 * @version 1.0
 */
@MappedSuperclass
@Data
public class BaseDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	@Column(name="status")
	protected byte status;

	@Column(name="creator")
	protected Long creator;

	@Column(name="creat_time")
	protected Date creatTime;

	@Column(name="modifier")
	protected Long modifier;

	@Column(name="modify_time")
	protected Date modifyTime;
	
	/**
	 * 在保存前设置当前登录用户为创建人,设置当前时间为创建时间
	 */
	public void preInsert() {
//		creator = UserUtils.getUserId();
		status = 0;
		creatTime = new Date();
	}
	
	/**
	 * 在更新前设置当前登录用户为修改人,设置当前时间为修改时间
	 */
	public void preUpdate() {
//		modifier = UserUtils.getUserId();
		modifyTime = new Date();
	}
	
	/**
	 * 在删除前设置当前登录用户为修改人,设置当前时间为修改时间
	 */
	public void preDelete() {
//		modifier = UserUtils.getUserId();
		status = -1;
		modifyTime = new Date();
	}
}
