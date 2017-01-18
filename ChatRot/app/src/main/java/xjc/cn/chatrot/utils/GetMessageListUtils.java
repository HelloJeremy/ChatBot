package xjc.cn.chatrot.utils;

import java.util.ArrayList;
import java.util.List;

import xjc.cn.chatrot.entity.ResponseMessage;

/**
 * Created by 徐嘉诚 on 2016/12/30.
 * 获得聊天信息的工具类
 */

public class GetMessageListUtils {
    private static List<ResponseMessage> messageList = new ArrayList<ResponseMessage>();
    public static void putMessage(ResponseMessage responseMessage) {
        messageList.add(responseMessage);
    }
    public static List<ResponseMessage> getMessageList() {
        return messageList;
    }
}
