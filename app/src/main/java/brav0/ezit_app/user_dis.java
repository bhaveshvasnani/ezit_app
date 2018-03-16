package brav0.ezit_app;

import java.util.Calendar;

/**
 * Created by User on 04-Aug-17.
 */

public class user_dis {
    public String request;
    public String price;
    public String tip;
    public String address;
    public String date;
    public String time;
    public String city;
    public String makeid;
    public String reqid;
    public String accid;
    public int status;

    public user_dis(){}

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMakeid() {
        return makeid;
    }

    public void setMakeid(String makeid) {
        this.makeid = makeid;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public user_dis(String request, String price, String tip, String address, String date, String time, String city, String makeid, String reqid, String accid, int status) {
        this.request = request;
        this.price = price;
        this.tip = tip;
        this.address = address;
        this.date = date;
        this.time = time;
        this.city = city;
        this.makeid = makeid;
        this.reqid=reqid;
        this.accid = accid;
        this.status = status;
    }

}
