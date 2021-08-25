package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 4/21/2017.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer productid;
    private  String  productname;
    private  String  price;
    private  Integer categoryid;
    private  String  imagename;
    private  String  unit;

    public Product() {
    }

    public Product(Integer productid, String productname, String price) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
    }

    public Product(Integer productid, String productname, String price, Integer categoryid) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
        this.categoryid = categoryid;
    }

    public Product(Integer productid, String productname, String price, Integer categoryid, String imagename,String unit) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
        this.categoryid = categoryid;
        this.imagename = imagename;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }
}
