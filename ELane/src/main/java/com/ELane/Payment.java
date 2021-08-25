package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ASUS on 17-Apr-17.
 */
@Entity

public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer id;
    private  Integer customerid;
    private  String  paymenttype;
    private  Boolean isdue;
    private Date paydate;


    public Payment(){}
    public Payment(Integer ID, Integer customerid, String paymenttype, Boolean isdue) {
        this.id = ID;
        this.customerid = customerid;
        this.paymenttype = paymenttype;
        this.isdue = isdue;
    }

    public Payment(Integer ID, Integer customerid, String paymenttype, Boolean isdue, Date paydate) {
        this.id = ID;
        this.customerid = customerid;
        this.paymenttype = paymenttype;
        this.isdue = isdue;
        this.paydate = paydate;
    }

    public Payment(Integer ID, String paymenttype) {
        this.id = ID;
        this.paymenttype = paymenttype;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer ID) {
        this.id = ID;
    }

    public Integer getcustomerid() {
        return customerid;
    }

    public void setcustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getpaymenttype() {
        return paymenttype;
    }

    public void setpaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Boolean getIsdue() {
        return isdue;
    }

    public void setIsdue(Boolean isdue) {
        this.isdue = isdue;
    }
    public String ShippingStatus(){
        if(isdue==true)return "not shipped";
        else return "shipped";
    }
}
