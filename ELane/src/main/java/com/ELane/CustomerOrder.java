package com.ELane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Id;
//import java.util.String;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by ASUS on 17-Apr-17.
 */
@Entity

public class CustomerOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer id;
    private Integer paymentid;
    //private Integer kid;
    public double totalprice;
    public Date mydate;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer paymentid, double totalprice) {
        this.paymentid = paymentid;
        this.totalprice = totalprice;
    }

    public CustomerOrder(Integer id, Integer paymentid, double totalprice, Date mydate) {
        this.id = id;
        this.paymentid = paymentid;
        this.totalprice = totalprice;
        this.mydate = mydate;
    }

    public Integer getid() {

        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public Integer getpaymentid() {
        return paymentid;
    }

    public void setpaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Date getMydate() {
        return mydate;
    }

    public void setMydate(Date mydate) {
        this.mydate = mydate;
    }


}
