package xjc.cn.chatrot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import xjc.cn.chatrot.entity.ResponseMessage;

/**
 * Created by 徐嘉诚 on 2016/12/30.
 */

public class ChatAdapterright extends ArrayAdapter<ResponseMessage> {
    public ChatAdapterright(Context context, int resource, List<ResponseMessage> list) {
        super(context, resource, list);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取聊天的数据
        ResponseMessage responseMessage = getItem(position);
        // 创建布局_左边
        View oneRightView = LayoutInflater.from(getContext()).inflate(R.layout.content_right, parent, false);
        // 获取TextView
        TextView textView = (TextView) oneRightView.findViewById(R.id.id_word);
        // 根据机器人回复内容设置TextView的展现
        if (responseMessage == null) {
            System.out.println("responseMessage==null*********************************");
        }else{
            if(responseMessage.getText()!=null || responseMessage.getUrl()!=null) {
                if(responseMessage.getUrl() == null) {
                    textView.setText(responseMessage.getText());
                } else {
                    textView.setText(responseMessage.getText()+responseMessage.getUrl());
                }
            }
        }
        TextView textView2 = (TextView) oneRightView.findViewById(R.id.id_time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        textView2.setText(df.format(responseMessage.getDate()));
        return oneRightView;
    }
}
