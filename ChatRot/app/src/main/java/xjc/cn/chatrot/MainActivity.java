package xjc.cn.chatrot;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xjc.cn.chatrot.entity.ResponseMessage;
import xjc.cn.chatrot.utils.MyHttpUtils;

public class MainActivity extends Activity
{

    private  ListView listView;
    private ChatAdapter chatAdapter;
    private List<ResponseMessage> mDatas;

    private EditText mInputMsg;
    private Button mSendMsg;

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
//            // 等待接收，子线程完成数据的返回
            ResponseMessage fromMessge = (ResponseMessage) msg.obj;
            fromMessge.setDate(new Date());
            mDatas.add(fromMessge);
            chatAdapter.notifyDataSetChanged();
            listView.setSelection(mDatas.size()-1);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
        // 初始化事件
        initListener();
        //在listView中展示聊天内容
        chatAdapter = new ChatAdapter(this, R.layout.content_left,mDatas);
       listView = (ListView) findViewById(R.id.content_list);
//        listView.setSelection(mDatas.size()-1);
        listView.setAdapter(chatAdapter);
    }

    private void initListener()
    {
        mSendMsg.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String toMsg = mInputMsg.getText().toString();
                if (TextUtils.isEmpty(toMsg))
                {
                    Toast.makeText(MainActivity.this, "发送消息不能为空！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

//                ChatMessage toMessage = new ChatMessage();
//                toMessage.setDate(new Date());
//                toMessage.setMsg(toMsg);
//                toMessage.setType(Type.OUTCOMING);
//                mDatas.add(toMessage);
//                mAdapter.notifyDataSetChanged();
//                mMsgs.setSelection(mDatas.size()-1);
                ResponseMessage toMessage = new ResponseMessage("",toMsg,"",new Date(), ResponseMessage.MyType.TO);
                mDatas.add(toMessage);
                mInputMsg.setText("");

                new Thread()
                {
                    public void run()
                    {
                        ResponseMessage fromMessage = MyHttpUtils.sendMessage(toMsg);
                        fromMessage.setType(ResponseMessage.MyType.FROM);
                        Message m = Message.obtain();
                        m.obj = fromMessage;
                        mHandler.sendMessage(m);
//                      ResponseMessage rm = HttpRequestUtils.doPost("你的名字是？");
//                      String rm = HttpClientUtils.doPostByHttpClient("你的名字是？");
                }}.start();

            }
        });
    }

    private void initDatas()
    {
        mDatas = new ArrayList<ResponseMessage>();
        mDatas.add(new ResponseMessage("","你好，我是你的Ta", "",new Date(), ResponseMessage.MyType.FROM));
//        mAdapter = new ChatMessageAdapter(this, mDatas);
//        mMsgs.setAdapter(mAdapter);
    }

    private void initView()
    {
        listView = (ListView) findViewById(R.id.content_list);
        mInputMsg = (EditText) findViewById(R.id.id_input_msg);
        mSendMsg = (Button) findViewById(R.id.id_send_msg);
    }

}
