package com.gongjin.commom.push;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 拉取业务数据监听类
 * 
 * @title
 * @author 龚进
 * @date 2017年11月22日
 * @version 1.0
 */
@Component
public class DataPullListener extends DataEventListener {

	/**
	 * 拉取数据
	 */
	public final static String NAMESPACE = "/pull";

	private final SocketIOServer server;

	@Autowired
	public DataPullListener(SocketIOServer server) {
		this.server = server;
	}

	@OnConnect
	public void onConnect(SocketIOClient client) {

	}

	/**
	 * 监听拉取数据事件
	 * 
	 * @param client
	 * @param request
	 * @param data
	 */
	@OnEvent(PULL_EVENT)
	public void onPullBusEvent(SocketIOClient client, AckRequest request, String data) {
		if (Objects.equal(this.server.getNamespace(NAMESPACE), client.getNamespace())) {
			if (StringUtils.isNotBlank(data)) {
				Gson gson = new Gson();
				// 对于不是类的情况，用这个参数给出
				List<PullMessage> pullMessages = gson.fromJson(data, new TypeToken<List<PullMessage>>() {
				}.getType());
				for (PullMessage pullMessage : pullMessages) {
					String dataIds = pullMessage.getDataIds();
					String event = pullMessage.getType();
					String[] dataIdArr = StringUtils.isBlank(dataIds) ? null : dataIds.split(",");
					// 缓存数据
					putClient(event, dataIdArr, client.getSessionId().toString());
				}
			}
		}
	}

	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		if (Objects.equal(this.server.getNamespace(NAMESPACE), client.getNamespace())) {
			// 移除客户端
			removeClient(client.getSessionId().toString());
		}
	}
}
