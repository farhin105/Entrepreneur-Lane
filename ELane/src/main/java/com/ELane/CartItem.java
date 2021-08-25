package com.ELane;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
@Entity

public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer ID;
    public   Integer orderid;
    public   Integer productid;
    public   Integer amount;

    public CartItem(){}

    public CartItem(Integer orderid, Integer productid, Integer amount) {
        this.orderid = orderid;
        this.productid = productid;
        this.amount = amount;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getorderid() {
        return orderid;
    }

    public void setorderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getproductid() {
        return productid;
    }

    public void setproductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
