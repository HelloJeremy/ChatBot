package xjc.cn.chatrot.utils;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xjc.cn.chatrot.entity.ResponseMessage;

/**
 * Created by 徐嘉诚 on 2016/12/26.
 * 与图灵机器人接口进行交互的请求
 */

public class HttpRequestUtils {
    private static String toUrl = "http://www.tuling123.com/openapi/api";

    public static ResponseMessage doPost(String info) {
        ResponseMessage result = null;
        try {
            String requestString = "key=" + "7f2fdebd917048bc9f9d5246fae10a06" + "&info=" + info + "&userid=123abc456ABC";
            // 创建url资源
            URL url = new URL(toUrl);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000000);
            conn.setConnectTimeout(500000);
            // 设置维持长连接
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            // 设置文件字符集:
//            conn.setRequestProperty("Charset", "UTF-8");
//            //转换为字节数组
//            byte[] data = (requestString.getBytes());
//            // 长设置文件度
//            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
//
//            // 设置文件类型:
//            conn.setRequestProperty("contentType", "application/json");
//
//            // 开始连接请求
//            conn.connect();
            OutputStream out = conn.getOutputStream();
           // 写入请求的字符串
            out.write(requestString.getBytes());
            out.flush();
            out.close();

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 将json格式的数据转为对象
                    Gson gson = new Gson();
                    result = gson.fromJson(new String(data1), ResponseMessage.class);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                System.out.println("no++");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
