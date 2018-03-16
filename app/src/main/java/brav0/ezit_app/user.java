package brav0.ezit_app;

import android.provider.ContactsContract;

import com.google.firebase.database.Exclude;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 02-Jun-17.
 */

public class user {
    public String request;
    public String price;
    public String tip;
    public String address;
    Calendar cal;
    public String city;
    public String makeid;
    public String reqid;
    public String accid;
    public int status;

    public user(){}

    public user(String request, String price, String tip, String address, Calendar cal, String city, String makeid, String reqid, String accid,int status) {
        this.request = request;
        this.price = price;
        this.tip = tip;
        this.address = address;
        this.cal = cal;
        this.city = city;
        this.makeid = makeid;
        this.reqid=reqid;
        this.accid = accid;
        this.status = status;
    }

    public String getRequest() {
        return request;
    }

    public String getPrice() {
        return price;
    }

    public String getTip() {
        return tip;
    }

    public String getAddress() {
        return address;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public String getCity() {
        return city;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }
}
