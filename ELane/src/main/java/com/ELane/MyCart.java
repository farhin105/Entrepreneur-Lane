package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 4/21/2017.
 */
public class MyCart {
    private  Integer productid;
    private  String  productname;
    private  String  price;
    private  Integer amount;
    private  String  imagename;
    private  Integer priceINT;
    public MyCart() {
    }

    public MyCart(Integer productid, String productname, String price) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
    }

    public MyCart(Integer productid, String productname, String price, String imagename) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
        this.imagename = imagename;
    }

    public MyCart(Integer productid, String productname, String price, Integer amount, String imagename) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
        this.amount = amount;
        this.imagename = imagename;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
