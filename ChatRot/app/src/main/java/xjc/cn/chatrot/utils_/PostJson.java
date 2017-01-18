package xjc.cn.chatrot.utils_;

import com.google.gson.JsonObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 徐嘉诚 on 2016/12/25.
 * 与图灵机器人进行消息的交互
 */

public class PostJson {
    public static void main(String args[]) {
        try {
//            String requestString = "key=" + "e1d44c5ee149d0e85be954c1129e043b" + "&info=" + "你叫什么名字" + "&userid=123abc456ABC";
            String requestString = "{'key':e1d44c5ee149d0e85be954c1129e043b,"+"'info':你爸爸是谁,'userid':"+"123abc456ABC}";
            JsonObject obj=new JsonObject();
            obj.addProperty("key", "e1d44c5ee149d0e85be954c1129e043b");
            obj.addProperty("info", "你爸爸是谁");
            obj.addProperty("userid", "123abc456ABC");
            System.out.println(obj);
            System.out.println("*********************************");
            // 创建url资源
            URL url = new URL("http://www.tuling123.com/openapi/api");
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
//            byte[] data = (obj.toString()).getBytes();
            byte[] data = (requestString.getBytes());
            // 长设置文件度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));

            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");


            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
//            // 写入请求的字符串
//            out.write((obj.toString()).getBytes());
            out.write(requestString.getBytes());
            out.flush();
            out.close();

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                System.out.println("连接成功");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                String a = null;
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);
                    System.out.println(a);
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
    }
}
