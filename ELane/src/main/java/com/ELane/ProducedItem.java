package com.ELane;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by User on 4/21/2017.
 */
@Entity
public class ProducedItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer seqno;
    private Integer productid;
    private Integer producerid;
    private Integer amount;
    public ProducedItem (){}
    public ProducedItem(Integer seqno, Integer productid, Integer producerid,Integer amount) {
        this.seqno = seqno;
        this.productid = productid;
        this.producerid = producerid;
        this.amount = amount;
    }

    public ProducedItem(Integer productid, Integer producerid, Integer amount) {
        this.productid = productid;
        this.producerid = producerid;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getProducerid() {
        return producerid;
    }

    public void setProducerid(Integer producerid) {
        this.producerid = producerid;
    }
}
