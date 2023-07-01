package com.sifu.sfcc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SendHttpNLP {

    private final static Map<String, List<Cookie>> cookieMap = new HashMap<>();

    private static OkHttpClient client;
    /**
     *  内网IP
     */
//    private static final String BASE_URL = "http://192.168.38.15:8086";
    /**
     *  外网IP
     */
    private static final String BASE_URL = "http://111.40.37.223:5100";

    private static final String QUERY_URLQuery = BASE_URL + "/core/v3/query";

    private static final String QUERY_URLStart = BASE_URL + "/core/v3/start";

    /**
     *  BOT的token
     */
//    private static final String access_token = "c04ca862-960f-468c-a4a1-d73fadf72c43";
    private static final String access_token = "ae6e1d1f-b041-4adf-9c06-80c4278b1046";


    /**
     *  Agent的token
     */
    private static final String develop_token = "bd0dba3b-1202-4f65-8876-e06caaf848d4";

    private final static MediaType contentType = MediaType.parse("application/json; charset=utf-8");

    static{
        client = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieMap.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieMap.get(url.host());
                return cookies != null ? cookies : new ArrayList<>();
            }
        })
                .connectTimeout(60 * 1000L, TimeUnit.MILLISECONDS)
                .readTimeout(5 * 60 * 1000L, TimeUnit.MILLISECONDS)
                .writeTimeout(5 * 60 * 1000L, TimeUnit.MILLISECONDS)
                .build();
    }


    /**
     * NLP的start接口
     * @param map
     * @return
     */
    public static String sendHttpStart(Map<String, String> map){
        String resultData = "";
        try{
            // 1.请求URL
            String postUrl = QUERY_URLStart;
            // 2.请求参数
            String json = JSON.toJSONString(map);
            // 3.创建连接与设置连接参数
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(postUrl);
            httpPost.setHeader("Authorization","NGD"+ " " + access_token);
            StringEntity entity = new StringEntity(json);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            // 4.发起请求与接收返回值
            HttpResponse response = httpClient.execute(httpPost);
//            if (response.getStatusLine().getStatusCode() != 200) {
//            throw new Exception("调用服务端异常.");
//            }
            HttpEntity res = response.getEntity();
            resultData = EntityUtils.toString(res);
            // 5.关闭连接
            httpClient.close();
        }catch (Exception e){
            return resultData;
        }
        return resultData;
    }

    /**
     * NLP的query接口
     * @param jsonParam 回答的问题
     * @return  下一个节点的返回结果
     */
    public static String sendHttpQuery(String jsonParam)  {
        try {
            //System.out.println("队列元素数:"+NGDQueue.queue.size());
            // NGDQueue.queue.put(System.currentTimeMillis()+ Math.random()+"");
            // q  System.out.println("进入队列开始执行:"+Thread.currentThread()+""+ DateKit.toStr(new Date(),"hh:MM:ss SS"));
            RequestBody requestBody = FormBody.create(contentType,jsonParam);
            Request request = new Request.Builder().addHeader("Authorization", "NGD"+ " " + access_token)
                    .post(requestBody)
                    .url(QUERY_URLQuery)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            return "";
        }
    }

    public static String get(String url) throws Exception {
//        NGDQueue.queue.put(System.currentTimeMillis()+ Math.random()+"");
        Request request = new Request.Builder().addHeader("Authorization", "NGD"+ " " + access_token)
                .url(QUERY_URLQuery)
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }



}
