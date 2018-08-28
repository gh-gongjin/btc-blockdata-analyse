package com.gongjin.commom.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 自定义线程池
 * 
 * @title
 * @author 龚进
 * @date 2017年11月30日
 * @version 1.0
 */
public class CustomThreadPool {

	/**
	 * 线程池
	 */
	private ExecutorService pool = null;

	/**
	 * 核心线程数量
	 */
	private static final int CORE_POOL_SIZE = 10;

	/**
	 * 最大线程数量
	 */
	private static final int MAXIMUN_POOL_SIZE = 100;

	/**
	 * 非核心线程空闲存活时间,单位毫秒
	 */
	private static final long KEEP_ALIVE_TIME = 5000L;

	/**
	 * 缓冲队列容量大小
	 */
	private static final int QUEUE_CAPACITY = 1024;

	/**
	 * 初始化创建线程池
	 */
	public void init() {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("custom-pool-%d").build();
		pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUN_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(QUEUE_CAPACITY), namedThreadFactory,
				new CustomRejectedExecutionHandler());
	}

	/**
	 * 执行任务
	 * 
	 * @param command
	 */
	public void execute(Runnable command) {
		if (pool != null) {
			pool.execute(command);
		}
	}

	/**
	 * 销毁线程池
	 */
	public void destory() {
		if (pool != null) {
			pool.shutdownNow();
		}
	}
}
