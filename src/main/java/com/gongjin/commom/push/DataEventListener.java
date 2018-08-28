package com.gongjin.commom.push;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;

/**
 * 数据事件监听基类
 * 
 * @title
 * @author 龚进
 * @date 2017年11月22日
 * @version 1.0
 */
public abstract class DataEventListener {

	/**
	 * 拉取数据事件
	 */
	protected static final String PULL_EVENT = "pull_event";

	/**
	 * 缓存客户端数据
	 */
	protected static Map<String, Map<String, Set<String>>> socketClients = Maps.newConcurrentMap();

	/**
	 * 缓存要拉取数据的客户端
	 * 
	 * @param event
	 * @param clientId
	 */
	protected void putClient(String event, String[] dataIdArr, String clientId) {
		Map<String, Set<String>> cache = socketClients.get(event);
		// 如果没有具体数据
		if (null == dataIdArr || dataIdArr.length == 0) {
			cache = Maps.newConcurrentMap();
		} else {
			// 如果事件对应的数据没有,就创建一个map存储数据
			if (null == cache || cache.size() == 0) {
				cache = Maps.newConcurrentMap();
				// 给每个数据id存放需要推送的客户端id集合
				for (String dataId : dataIdArr) {
					Set<String> clientIds = new HashSet<>();
					clientIds.add(clientId);
					cache.put(dataId, clientIds);
				}
			} else {
				// 如果事件存在数据,就添加新的数据
				for (String dataId : dataIdArr) {
					Set<String> clientIds = null;
					// 数据id存在,就把新的客户端id放入集合
					if (cache.containsKey(dataId)) {
						clientIds = cache.get(dataId);
						clientIds.add(clientId);
					} else {
						// 数据id不存在,就创建新的集合并放入客户端id
						clientIds = new HashSet<>();
						clientIds.add(clientId);
						cache.put(dataId, clientIds);
					}
				}
			}
		}
		socketClients.put(event, cache);
	}

	/**
	 * 获取需要推送数据的客户端
	 * 
	 * @param event
	 * @return
	 */
	protected Set<String> getClient(String event, String dataId) {
		Set<String> clientIds = new HashSet<>();
		Map<String, Set<String>> cache = socketClients.get(event);
		if (cache != null) {
			Set<String> mapClientIds = cache.get(dataId);
			if (null != mapClientIds) {
				for (String clientId : mapClientIds) {
					clientIds.add(clientId);
				}
			}
		}
		return clientIds;
	}

	/**
	 * 移除客户端
	 * 
	 * @param clientId
	 */
	protected void removeClient(String clientId) {
		// 删除客户端
		Set<Entry<String, Map<String, Set<String>>>> set = socketClients.entrySet();
		for (Entry<String, Map<String, Set<String>>> entry : set) {
			Map<String, Set<String>> cache = entry.getValue();
			Set<Entry<String, Set<String>>> set2 = cache.entrySet();
			for (Entry<String, Set<String>> entry2 : set2) {
				Set<String> clientIds = entry2.getValue();
				for (Iterator<String> iterator = clientIds.iterator(); iterator.hasNext();) {
					String id = iterator.next();
					if (clientId.equals(id)) {
						iterator.remove();
						break;
					}
				}
			}
		}
	}
}
