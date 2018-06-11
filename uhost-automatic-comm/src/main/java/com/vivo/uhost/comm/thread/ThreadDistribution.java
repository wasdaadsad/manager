package com.vivo.uhost.comm.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程分发器
 * 
 */
public class ThreadDistribution {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static final Integer DEFAULT_CORE_SIZE = 10;
	private static final Integer MAX_SIZE = 100;
	private static final Integer DEFAULT_QUEUE_SIZE = 1000000;

	private static final ThreadDistribution instance =  new ThreadDistribution(DEFAULT_CORE_SIZE);

	private final ThreadPoolExecutor executor;

	private final AtomicBoolean closed = new AtomicBoolean(false);

	private ThreadDistribution(int initCount) {
		executor = new ThreadPoolExecutor(initCount, MAX_SIZE, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(DEFAULT_QUEUE_SIZE),
				new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public static ThreadDistribution getInstance() {
		return instance;
	}

	public void execute(Runnable work) {
		if (closed.get())
			return;

		if (work == null) {
			logger.warn("work is empty!");
			throw new NullPointerException();
		}
		try {
			executor.execute(work);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> void submit(Callable<T> task) {
		if (closed.get())
			return;

		if (task == null) {
			logger.warn("task is empty!");
			throw new NullPointerException();
		}
		try {
			executor.submit(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Future<String> submit4Futrue(Callable<String> task) {
		Future<String> future = null;
		if (closed.get())
			return null;

		if (task == null) {
			logger.warn("task is empty!");
			throw new NullPointerException();
		}
		try {
			future = executor.submit(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return future;
	}

	public Map<String, Object> getStatus(){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("corePoolSize", executor.getCorePoolSize());
		statusMap.put("maxPoolSize", executor.getMaximumPoolSize());
		statusMap.put("poolSize", executor.getPoolSize());
		statusMap.put("activeCount", executor.getActiveCount());
		statusMap.put("taskCount", executor.getTaskCount());
		statusMap.put("completedTaskCount", executor.getCompletedTaskCount());

		return statusMap;
	}

}
