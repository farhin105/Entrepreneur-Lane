package com.ELane;

import com.sun.org.apache.xml.internal.utils.BoolStack;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * Created by User on 5/22/2017.
 */
public class ProducerChart {
    private Integer productid;
    private String productname;
    private Date orderdate;
    private String deliveryplace;
    private Boolean ispaydue;
    private Integer amount;
    private Date paydate;
    private String paytype;
    private String consumername;
    public ProducerChart(){}
    public ProducerChart(Integer productid, String productname, Date orderdate, String deliveryplace, Boolean ispaydue, Integer amount, Date paydate, String paytype,String consumername) {
        this.productid = productid;
        this.productname = productname;
        this.orderdate = orderdate;
        this.deliveryplace = deliveryplace;
        this.ispaydue = ispaydue;
        this.amount = amount;
        this.paydate = paydate;
        this.paytype = paytype;
        this.consumername = consumername;
    }

    public String getConsumername() {return consumername;}

    public void setConsumername(String consumername) {
        this.consumername = consumername;
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

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getDeliveryplace() {
        return deliveryplace;
    }

    public void setDeliveryplace(String deliveryplace) {
        this.deliveryplace = deliveryplace;
    }

    public Boolean getIspaydue() {
        return ispaydue;
    }

    public void setIspaydue(Boolean ispaydue) {
        this.ispaydue = ispaydue;
    }
    public String Isdue(){
        if(getIspaydue()==true)return "due";
        else return "clear";
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public void Print(){
        System.out.println(productname+" , "+deliveryplace+" , "+orderdate+" , "+consumername+" , "+paytype+" , "+ paydate);
    }
    public void setProfile(Product product,CartItem cartItem,Consumer consumer,Payment payment,CustomerOrder customerOrder,ConsumerAddress consumerAddress){
        this.setProductid(product.getProductid());
        this.setProductname(product.getProductname());
        this.setAmount(cartItem.getAmount());
        this.setOrderdate(customerOrder.getMydate());
        this.setIspaydue(payment.getIsdue());
        this.setPaydate(payment.getPaydate());
        this.setPaytype(payment.getpaymenttype());
        this.setConsumername(consumer.getUsername());
        this.setDeliveryplace(consumerAddress.getStreetaddress() + " , " + consumerAddress.getCity() + " , " + consumerAddress.getPostalcode() + " , " + consumerAddress.getDistrict() + " , " + consumerAddress.getCountry());

    }
}
