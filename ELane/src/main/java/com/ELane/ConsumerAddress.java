package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 4/21/2017.
 */
@Entity
public class ConsumerAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer CustomerAddressID;
    private  String  streetaddress;
    private  String  city;
    private  String  postalcode;
    private  String  district;
    private  String country;
    private  Integer consumerid;
    public ConsumerAddress() {
    }

    public ConsumerAddress(Integer customerAddressID, String streetaddress, String city, String postalcode, String district, String country,Integer consumerid) {
        CustomerAddressID = customerAddressID;
        this.streetaddress = streetaddress;
        this.city = city;
        this.postalcode = postalcode;
        this.district = district;
        this.country = country;
        this.consumerid=consumerid;
    }

    public Integer getconsumerid() {
        return consumerid;
    }

    public void setconsumerid(Integer consumerid) {
        this.consumerid = consumerid;
    }

    public Integer getCustomerAddressID() {
        return CustomerAddressID;
    }

    public void setCustomerAddressID(Integer customerAddressID) {
        CustomerAddressID = customerAddressID;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getConsumerid() {
        return consumerid;
    }

    public void setConsumerid(Integer consumerid) {
        this.consumerid = consumerid;
    }

    public void Edit (ConsumerAddress p_address){
        this.streetaddress=p_address.getStreetaddress();
        this.city=p_address.getCity();
        this.district=p_address.getDistrict();
        this.postalcode=p_address.getPostalcode();
        this.country=p_address.getCountry();
    }
}
