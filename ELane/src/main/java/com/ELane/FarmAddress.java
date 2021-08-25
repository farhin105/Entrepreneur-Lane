package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 4/21/2017.
 */
@Entity
public class FarmAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer farmid;
    private  String  farmstreetaddress;
    private  String  farmcity;
    private  String  farmpostalcode;
    private  String  farmdistrict;
    private  String farmcountry;
    private  Integer producerid;
    public FarmAddress() {
    }

    public FarmAddress(Integer farmid, String farmstreetaddress, String farmcity, String farmpostalcode, String farmdistrict, String farmcountry, Integer producerid) {
        this.farmid = farmid;
        this.farmstreetaddress = farmstreetaddress;
        this.farmcity = farmcity;
        this.farmpostalcode = farmpostalcode;
        this.farmdistrict = farmdistrict;
        this.farmcountry = farmcountry;
        this.producerid = producerid;
    }

    public Integer getFarmid() {
        return farmid;
    }

    public void setFarmid(Integer farmid) {
        this.farmid = farmid;
    }

    public String getFarmstreetaddress() {
        return farmstreetaddress;
    }

    public void setFarmstreetaddress(String farmstreetaddress) {
        this.farmstreetaddress = farmstreetaddress;
    }

    public String getFarmcity() {
        return farmcity;
    }

    public void setFarmcity(String farmcity) {
        this.farmcity = farmcity;
    }

    public String getFarmpostalcode() {
        return farmpostalcode;
    }

    public void setFarmpostalcode(String farmpostalcode) {
        this.farmpostalcode = farmpostalcode;
    }

    public String getFarmdistrict() {
        return farmdistrict;
    }

    public void setFarmdistrict(String farmdistrict) {
        this.farmdistrict = farmdistrict;
    }

    public String getFarmcountry() {
        return farmcountry;
    }

    public void setFarmcountry(String farmcountry) {
        this.farmcountry = farmcountry;
    }

    public Integer getProducerid() {
        return producerid;
    }

    public void setProducerid(Integer producerid) {
        this.producerid = producerid;
    }

    public void setFromP_address (ProducerAddress p_address){
        this.farmstreetaddress=p_address.getStreetaddress();
        this.farmcity=p_address.getCity();
        this.farmdistrict=p_address.getDistrict();
        this.farmpostalcode=p_address.getPostalcode();
        this.farmcountry=p_address.getCountry();
    }
    public void Edit (FarmAddress farmAddress){
        this.farmstreetaddress=farmAddress.getFarmstreetaddress();
        this.farmcity=farmAddress.getFarmcity();
        this.farmdistrict=farmAddress.getFarmdistrict();
        this.farmpostalcode=farmAddress.getFarmpostalcode();
        this.farmcountry=farmAddress.getFarmcountry();
    }
}
