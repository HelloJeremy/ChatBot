package xjc.cn.chatrot.utils_;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import xjc.cn.chatrot.RequestMessage;

/**
 * 与图灵机器人交互的工具类
 * Created by 徐嘉诚 on 2016/12/24.
 */

public class SendrequestUtil {
    public static final String ADD_URL = "http://www.tuling123.com/openapi/api";
    public  static void doPost(String toInfo) {
        String result="";
        RequestMessage requestMessage = new RequestMessage(toInfo,"123456");
        PrintWriter out = null;
        try {
            //创建连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
            connection.connect();

//            //POST请求
//            DataOutputStream out = new DataOutputStream(
//                    connection.getOutputStream());
//            String jsonStr = new Gson().toJson(requestMessage);
//            System.out.println("$$$$$$$$$$$$$$$$$"+jsonStr);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.write(new Gson().toJson(requestMessage));
            System.out.println(new Gson().toJson(requestMessage));
//            out.write(jsonStr);
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        doPost("你好");
        doPost("你妈妈是谁");
    }
}
