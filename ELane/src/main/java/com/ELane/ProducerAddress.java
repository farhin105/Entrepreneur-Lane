package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 4/21/2017.
 */
@Entity
public class ProducerAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer ProducerAddressID;
    private Integer pkey;
    private  String  streetaddress;
    private  String  city;
    private  String  postalcode;
    private  String  district;
    private  String country;
    private  Integer producerid;
    public ProducerAddress() {
    }

    public ProducerAddress(Integer ProducerAddressID, String streetaddress, String city, String postalcode, String district, String country, Integer producerid) {
        ProducerAddressID = ProducerAddressID;
        pkey=ProducerAddressID;
        this.streetaddress = streetaddress;
        this.city = city;
        this.postalcode = postalcode;
        this.district = district;
        this.country = country;
        this.producerid=producerid;
    }

    public Integer getPkey() {
        return pkey;
    }

    public void setPkey(Integer pkey) {
        this.pkey = pkey;
    }

    public Integer getproducerid() {
        return producerid;
    }

    public void setproducerid(Integer producerid) {
        this.producerid = producerid;
    }

    public Integer getProducerAddressID() {
        return ProducerAddressID;
    }

    public void setProducerAddressID(Integer ProducerAddressID) {
        ProducerAddressID = ProducerAddressID;
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

    public void Edit (ProducerAddress p_address){
        this.streetaddress=p_address.getStreetaddress();
        this.city=p_address.getCity();
        this.district=p_address.getDistrict();
        this.postalcode=p_address.getPostalcode();
        this.country=p_address.getCountry();
    }
}
