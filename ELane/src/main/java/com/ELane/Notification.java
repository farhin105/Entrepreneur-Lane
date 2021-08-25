package com.ELane;

import javax.persistence.*;

/**
 * Created by ASUS on 17-Apr-17.
 */
@Entity

public class Notification {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer id;
    private  Integer fromid;
    private  Integer toid;
    private  Integer productid;
    private  double  amount;
    private String imagename;
    private String productname;
    private String fromname;

    public Notification() {}

    public Notification(Integer id, Integer fromid, Integer toid, Integer productid, double amount) {
        this.id = id;
        this.fromid = fromid;
        this.toid = toid;
        this.productid = productid;
        this.amount = amount;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }

    public Integer getToid() {
        return toid;
    }

    public void setToid(Integer toid) {
        this.toid = toid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAll(Integer fromid , Integer toid , Integer productid ,  double amount ,String productname,String imagename,String fromname){
        this.fromid = fromid;
        this.toid = toid;
        this.productid = productid;
        this.amount = amount;
        this.imagename = imagename;
        this.productname = productname;
        this.fromname = fromname;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", fromid=" + fromid +
                ", toid=" + toid +
                ", productid=" + productid +
                ", amount=" + amount +
                ", imagename='" + imagename + '\'' +
                ", productname='" + productname + '\'' +
                ", fromname='" + fromname + '\'' +
                '}';
    }
}
