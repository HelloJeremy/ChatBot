package xjc.cn.chatrot.entity;
import java.util.Date;

/**
 * Created by 徐嘉诚 on 2016/12/26.
 * 返回消息的实体类
 */

public class ResponseMessage {
    private MyType type;
    private String code="";
    private String text="";
    private String url="";
    private Date date;
    public enum MyType
    {
        FROM, TO
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public MyType getType() {
        return type;
    }

    public void setType(MyType type) {
        this.type = type;
    }

    public ResponseMessage(String code, String text, String url, Date date, MyType type) {
        this.code = code;
        this.text = text;
        this.url = url;
        this.date = date;
        this.type = type;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//   private Integer result;
//   private Integer id;
//   private String response;
//   private String msg;
//
//    public Integer getResult() {
//        return result;
//    }
//
//    public void setResult(Integer result) {
//        this.result = result;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getResponse() {
//        return response;
//    }
//
//    public void setResponse(String response) {
//        this.response = response;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public ResponseMessage(Integer result, Integer id, String response, String msg) {
//        this.result = result;
//        this.id = id;
//        this.response = response;
//        this.msg = msg;
//    }
}
