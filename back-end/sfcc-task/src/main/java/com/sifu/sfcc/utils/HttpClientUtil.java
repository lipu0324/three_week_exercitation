package com.sifu.sfcc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ContentType;
import cn.hutool.core.util.ObjectUtil;

public class HttpClientUtil {
	
	static CloseableHttpClient client = null;
	static {
		client = HttpClients.createDefault();
	}
	

	public static String get(String url,HashMap<String, Object> params){
		try {
			HttpGet httpGet = new HttpGet();
			Set<String> keySet = params.keySet();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());
			for (String key : keySet) {
				stringBuffer.append("&").append(key).append("=").append(params.get(key));
			}
			httpGet.setURI(new URI(stringBuffer.toString()));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse execute = client.execute(httpGet);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			return null;
		}
	}
	
	public static String post(String url,HashMap<String, Object> params) {
		try {
			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(url));
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				if(ObjectUtil.isNotNull(params.get(key))) {
					NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
					parameters.add(e);
				}
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
			httpPost.setEntity(entity );
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			
			CloseableHttpResponse execute = client.execute(httpPost);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String postX(String url,String body) {
		try {
			HttpPost httpPost = new HttpPost();
			httpPost.setURI(new URI(url));
			httpPost.setHeader("Content-Type", "application/json");
			HttpEntity entity = new StringEntity(body, "utf-8");
			httpPost.setEntity(entity );
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			
			CloseableHttpResponse execute = client.execute(httpPost);
			int statusCode = execute.getStatusLine().getStatusCode();
			if (200 != statusCode) {
				return "";
			}
			return EntityUtils.toString(execute.getEntity(), "utf-8");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String httpPostHeadWithJSON(String url, String json, Map<String, String> headMap) {
	    HttpClient httpClient = HttpClientBuilder.create().build();
	    HttpPost httpPost = new HttpPost(url);
	    httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
	    StringEntity se = new StringEntity(json, "UTF-8");
	    httpPost.setEntity(se);
	    if (headMap != null) {
	        for (Iterator<String> it = headMap.keySet().iterator(); it.hasNext(); ) {
	            String key = it.next();
	            String value = headMap.get(key);
	            httpPost.addHeader(key, value);
	        }
	    }
	    String result = null;
	    HttpResponse response = null;
	    try {
	        response = httpClient.execute(httpPost);
	        boolean success = response.getStatusLine().getStatusCode() == 200 ||
	                response.getStatusLine().getStatusCode() == 201;
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
	            InputStream inputStream = entity.getContent();
	            result = convertStreamToString(inputStream);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        httpPost.releaseConnection();
	    }

	    return result;
	}
	
	
	public static String convertStreamToString(InputStream is) throws IOException {
	    StringBuilder stringBuilder = new StringBuilder();
	    String line = null;

	    try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        while ((line = br.readLine()) != null) {
	            if (stringBuilder.length() == 0) {
	                stringBuilder.append(line);
	            } else {
	                stringBuilder.append("\n" + line);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        is.close();
	    }
	    return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("city", "北京");
		String string = HttpClientUtil.get("https://www.sojson.com/open/api/weather/json.shtml", params);
		System.out.println(string);
	}
}
