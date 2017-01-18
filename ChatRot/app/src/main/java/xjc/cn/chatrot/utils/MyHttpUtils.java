package xjc.cn.chatrot.utils;

import com.google.gson.Gson;
import com.imooc.mooo.bean.ChatMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import xjc.cn.chatrot.entity.ResponseMessage;

public class MyHttpUtils
{
	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String API_KEY = "7f2fdebd917048bc9f9d5246fae10a06";

	/**
	 * 发送一个消息，得到返回的消息
	 * @param msg
	 * @return
	 */
	public static ResponseMessage sendMessage(String msg)
	{
		ChatMessage chatMessage = new ChatMessage();
		String jsonRes = doGet(msg);
		Gson gson = new Gson();
		ResponseMessage result = null;
		try
		{
			result = gson.fromJson(jsonRes, ResponseMessage.class);
//			chatMessage.setMsg(result.getText());
		} catch (Exception e)
		{
			chatMessage.setMsg("服务器繁忙，请稍候再试");
		}
//		chatMessage.setDate(new Date());
//		chatMessage.setType(Type.INCOMING);
//		return chatMessage;
		return result;
	}

	public static String doGet(String msg)
	{
		String result = "";
		String url = setParams(msg);
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try
		{
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet
					.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			is = conn.getInputStream();
			int len = -1;
			byte[] buf = new byte[128];
			baos = new ByteArrayOutputStream();

			while ((len = is.read(buf)) != -1)
			{
				baos.write(buf, 0, len);
			}
			baos.flush();
			result = new String(baos.toByteArray());
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (baos != null)
					baos.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			try
			{
				if (is != null)
				{
					is.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	private static String setParams(String msg)
	{
		String url = "";
		try
		{
			url = URL + "?key=" + API_KEY + "&info="
					+ URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return url;
	}

}
