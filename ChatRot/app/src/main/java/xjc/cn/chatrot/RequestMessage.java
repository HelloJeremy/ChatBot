package xjc.cn.chatrot;

/**
 * Created by 徐嘉诚 on 2016/12/25.
 */

public class RequestMessage {
    public RequestMessage(String info, String userid) {
        this.info = info;
        this.userid = userid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private String key = "e1d44c5ee149d0e85be954c1129e043b";
    private String info;
    private String userid;
}
