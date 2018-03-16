package brav0.ezit_app;

/**
 * Created by User on 30-Jun-17.
 */

public class chat_user {

    public String req;
    public String user1;
    public String user2;
    public String reqid;
    public int status;

    public chat_user(){}

    public chat_user(String req, String user1, String user2, String reqid, int status) {
        this.req = req;
        this.user1 = user1;
        this.user2 = user2;
        this.reqid = reqid;
        this.status = status;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

