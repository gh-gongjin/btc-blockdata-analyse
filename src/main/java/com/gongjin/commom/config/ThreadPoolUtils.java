package com.gongjin.commom.config;

/**
 * 线程池操作工具类
 * 
 * @title
 * @author 龚进
 * @date 2017年11月30日
 * @version 1.0
 */
public class ThreadPoolUtils {
	
	private ThreadPoolUtils() throws Exception {
		throw new Exception("线程池工具类不能被实例化");
	}

	/**
	 * 初始化线程池
	 */
	private static CustomThreadPool threadPool = new CustomThreadPool();
	static {
		threadPool.init();
	}

	/**
	 * 把任务放入线程池并执行
	 * @param command
	 */
	public static void execute(Runnable command) {
		threadPool.execute(command);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			ThreadPoolUtils.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
