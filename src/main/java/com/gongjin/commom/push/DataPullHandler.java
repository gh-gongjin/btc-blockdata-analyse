package com.gongjin.commom.push;

import java.util.Set;
import java.util.UUID;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.gongjin.commom.util.SpringContextUtils;

/**
 * 数据推送处理类
 * 
 * @title
 * @author 龚进
 * @date 2018年4月25日
 * @version 1.0
 */
public class DataPullHandler extends DataEventListener {

	private SocketIOServer server = (SocketIOServer) SpringContextUtils.getBean("socketIOServer");

	/**
	 * 推送数据到客户端
	 * 
	 * @param topic
	 *            消息主题
	 * @param event
	 *            事件类型
	 * @param data
	 *            推送数据
	 * @param clientIds
	 *            推送客户端
	 */
	public void pushData(String event, String dataId, String data) {
		Set<String> clientIds = getClient(event, dataId);
		if (null != clientIds && clientIds.size() > 0) {
			for (String clientId : clientIds) {
				UUID uuid = UUID.fromString(clientId);
				SocketIOClient sendClient = server.getNamespace(DataPullListener.NAMESPACE).getClient(uuid);
				sendClient.sendEvent(event, data);
			}
		}
	}
}
