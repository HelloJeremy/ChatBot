package xjc.cn.chatrot;


import org.junit.Test;

import xjc.cn.chatrot.utils.HttpClientUtils;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//        Log.v("hello","world");
//        System.out.println(  HttpClientUtils.doPostByHttpClient("你爸爸叫什么？"));
        System.out.println(HttpClientUtils.httpPostWithJSON("你爸爸叫什么？"));
        System.out.println(HttpClientUtils.httpPostWithJSON("你叫什么？"));
        System.out.println(HttpClientUtils.httpPostWithJSON("你妈妈叫什么？"));
//        System.out.println(HttpUtils.sendMessage("你的妈妈是谁？"));


    }
}