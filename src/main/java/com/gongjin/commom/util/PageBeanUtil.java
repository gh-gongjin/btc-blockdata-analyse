package com.gongjin.commom.util;

import org.springframework.data.domain.Page;

import com.gongjin.commom.PageBean;

/**
 * 
 * @title
 * @author 龚进
 * @date 2017年9月8日
 * @version 1.0
 */
public class PageBeanUtil {

	/**
	 * 根据page构建pagebean对象
	 * 
	 * @param page
	 * @return
	 */
	public static <E> PageBean createPageBean(Page<E> page) {
		PageBean pageBean = new PageBean();
		pageBean.setPageCount(page.getTotalPages());
		pageBean.setPageDataCount(page.getTotalElements());
		pageBean.setPageNo(page.getNumber() + 1);
		pageBean.setPageSize(page.getSize());
		return pageBean;
	}
}
