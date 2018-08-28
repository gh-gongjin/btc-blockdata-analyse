package com.gongjin.commom.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

@Component
public class WebSocketServerRunner implements CommandLineRunner {

	private final SocketIOServer server;

	@Autowired
	private DataPullListener dataPullListener;

	@Autowired
	public WebSocketServerRunner(SocketIOServer server) {
		this.server = server;
	}

	public void run(String... args) throws Exception {
		// 监听事件
		server.addNamespace(DataPullListener.NAMESPACE).addListeners(dataPullListener);
		// 启动服务
		server.start();
	}
}