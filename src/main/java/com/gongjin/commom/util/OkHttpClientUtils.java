package com.gongjin.commom.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp(高效的http请求)
 * 
 * @title
 * @author 龚进
 * @date 2018年8月13日
 * @version 1.0
 */
@Slf4j
public class OkHttpClientUtils {

	/**
	 * 连接超时 5秒
	 */
	private static final int CONN_TIMEOUT = 5;

	/**
	 * 读取超时 5秒
	 */
	private static final int READ_TIMEOUT = 5;

	/**
	 * 写入超时 5秒
	 */
	private static final int WRITE_TIMEOUT = 5;

	/**
	 * json类型
	 */
	private static final MediaType JSON = MediaType.parse("application/json");

	/**
	 * OkHttp客户端
	 */
	private static final OkHttpClient client = createOkHttpClient();

	static OkHttpClient createOkHttpClient() {
		return new Builder().connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS).readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 1080))).build();
	}

	/**
	 * okhttp get请求
	 * 
	 * @param uri
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String requestGet(String uri, Map<String, String> params) {
		if (params == null) {
			params = new HashMap<>();
		}
		try {
			Request.Builder builder = null;
			String url = uri + "?" + toQueryString(params);
			log.info(url);
			builder = new Request.Builder().url(url).get();
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			String ret = response.body().string();
			return ret;
		} catch (IOException e) {
			log.error("OkHttp--" + uri + "--get请求异常");
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * okhttp post请求
	 * 
	 * @param uri
	 *            请求地址
	 * @param object
	 *            请求参数
	 * @param headers
	 *            请求头
	 * @return
	 */
	public static String requestPost(String uri, Object object, Map<String, String> headers) {
		if (object == null) {
			object = new Object();
		}
		try {
			Request.Builder builder = null;
			System.out.println(JsonParseUtils.bean2Json(object));
			RequestBody body = RequestBody.create(JSON, JsonParseUtils.bean2Json(object));
			builder = new Request.Builder().url(uri).post(body);
			// 设置请求头
			if (null != headers) {
				for (Entry<String, String> entry : headers.entrySet()) {
					builder.addHeader(entry.getKey(), entry.getValue());
				}
			}
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			String ret = response.body().string();
			return ret;
		} catch (Exception e) {
			log.error("OkHttp--" + uri + "--post请求异常");
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 组装参数
	 * 
	 * @param params
	 * @return
	 */
	private static String toQueryString(Map<String, String> params) {
		return String.join("&", params.entrySet().stream().map((entry) -> {
			return entry.getKey() + "=" + entry.getValue();
		}).collect(Collectors.toList()));
	}

	public static void main(String[] args) throws Exception {
		
	}
}
