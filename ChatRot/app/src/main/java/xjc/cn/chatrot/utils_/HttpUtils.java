package xjc.cn.chatrot.utils_;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

public class HttpUtils
{
	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String API_KEY = "e1d44c5ee149d0e85be954c1129e043b";



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
					+ URLEncoder.encode(msg, "UTF-8")+"&userid=123456";
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return url;
	}
	public static void main(String[] args) {
		System.out.println(doGet("你好"));
		System.out.println(doGet("你爸爸是谁"));
		System.out.println(doGet("你妈妈是谁"));
		System.out.println(doGet("你叫什么名字"));
	}

}
