package xjc.cn.chatrot.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 徐嘉诚 on 2017/1/3.
 */

public class HttpClientUtils {
    /**
     * post方法(表单提交)
     */
    public static String doPostByHttpClient(String info) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.tuling123.com/openapi/api");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("key", "7f2fdebd917048bc9f9d5246fae10a06"));
        nvps.add(new BasicNameValuePair("userid", "123abc456ABC"));
        nvps.add(new BasicNameValuePair("info", info));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            return EntityUtils.toString(entity2);
        } finally {
            response2.close();
        }

    }

    public static String httpPostWithJSON(String info) throws Exception {
        String url = "http://www.tuling123.com/openapi/api";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        //json方式
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("key", "7f2fdebd917048bc9f9d5246fae10a06");
        jsonParam.put("userid", "123abc456ABC");
        jsonParam.put("info", info);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        }
        return respContent;
    }
}
