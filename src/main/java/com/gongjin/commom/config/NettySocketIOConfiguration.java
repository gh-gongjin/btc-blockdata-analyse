package com.gongjin.commom.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

@org.springframework.context.annotation.Configuration
@ConditionalOnClass({ NettySocketIOProperties.class })
@EnableConfigurationProperties(NettySocketIOProperties.class)
public class NettySocketIOConfiguration {

	@Bean
	public SocketIOServer socketIOServer(NettySocketIOProperties properties) {

		Configuration config = new Configuration();
		config.setHostname(properties.getHost());
		config.setPort(properties.getPort());
		config.setSocketConfig(new SocketConfig());
		// config.setOrigin("http://im.ukewo.com");
		config.setBossThreads(properties.getThreadsNum());
		config.setWorkerThreads(properties.getThreadsNum());
		// config.setStoreFactory(new HazelcastStoreFactory());
//		config.setJsonSupport(new JacksonJsonSupport());
		//该处可以用来进行身份验证  
        config.setAuthorizationListener(new AuthorizationListener() {  
            @Override  
            public boolean isAuthorized(HandshakeData data) {  
                //http://localhost:8081?username=test&password=test
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息
//              String username = data.getSingleUrlParam("username");  
//              String password = data.getSingleUrlParam("password");  
                return true;  
            }  
        });  

		SocketIOServer server = new SocketIOServer(config);
//		if (properties.getNamespaces() != null && properties.getNamespaces().size() > 0) {
//			for (String namespace : properties.getNamespaces()) {
//				server.addNamespace(namespace);
//			}
//		}
		return server;
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("50MB"); // KB,MB
		factory.setMaxRequestSize("100MB");
		return factory.createMultipartConfig();
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}
}
