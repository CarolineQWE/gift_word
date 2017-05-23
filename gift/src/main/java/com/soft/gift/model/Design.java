package com.soft.gift.model;

import javax.persistence.*;

/**
 * Created by fyq on 2017/5/17.
 */
@Table(name = "t_design")
public class Design {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer gift_id;
    private String design_img;
    private String account;
    private Integer if_achieve;
    private Integer if_exam;

    public Design() {
    }

    public Design( Integer gift_id, String design_img, String account,Integer if_achieve,Integer if_exam) {
        this.gift_id = gift_id;
        this.design_img = design_img;
        this.account = account;
        this.if_achieve = if_achieve;
        this.if_exam = if_exam;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="gift_id", length=30)
    public Integer getGift_id() {
        return gift_id;
    }

    public void setGift_id(Integer gift_id) {
        this.gift_id = gift_id;
    }

    @Column(name="design_img", length=200)
    public String getDesign_img() {
        return design_img;
    }

    public void setDesign_img(String design_img) {
        this.design_img = design_img;
    }

    @Column(name="account", length=30)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name="if_achieve", length=30)
    public Integer getIf_achieve() {
        return if_achieve;
    }

    public void setIf_achieve(Integer if_achieve) {
        this.if_achieve = if_achieve;
    }

    @Column(name="if_exam", length=30)
    public Integer getIf_exam() {
        return if_exam;
    }

    public void setIf_exam(Integer if_exam) {
        this.if_exam = if_exam;
    }

    @Override
    public String toString() {
        return "Design{" +
                "id=" + id +
                ", gift_id=" + gift_id +
                ", design_img='" + design_img + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
