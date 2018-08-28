package com.gongjin.commom.config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义任务拒绝处理器,在任务被拒绝后自定义逻辑处理异常
 * 
 * @title
 * @author 龚进
 * @date 2017年11月30日
 * @version 1.0
 */
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CustomRejectedExecutionHandler.class);

	/**
	 * 休眠时间,毫秒数
	 */
	private static final long SLEEP_MILLIS = 100L;

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		LOG.error(Thread.currentThread().getName() + ":因线程池和缓存队列已满,任务被拒绝,休眠" + SLEEP_MILLIS / 1000 + ",尝试重新加入任务队列");
		try {
			// 休眠,再加入线程池缓冲队列
			Thread.sleep(SLEEP_MILLIS);
			executor.getQueue().put(r);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
