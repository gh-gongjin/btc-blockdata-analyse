package com.gongjin.commom.push;

import lombok.Data;

/**
 * 拉取消息格式类
 * @title 
 * @author 龚进
 * @date 2017年11月22日
 * @version 1.0
 */
@Data
public class PullMessage {

	/**
	 * 拉取数据类型 比如 设备:device 线路:line 月计划:month_plan 月计划:day_plan
	 */
	private String type;
	
	/**
	 * 具体需要拉取的数据值 比如 设备类型就是设备id串
	 */
	private String dataIds;
}
