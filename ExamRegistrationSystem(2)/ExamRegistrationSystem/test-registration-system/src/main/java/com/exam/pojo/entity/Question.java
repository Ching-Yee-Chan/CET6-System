package com.exam.pojo.entity;

import java.io.Serializable;

/**
 * (Question)实体类
 *
 * @author makejava
 * @since 2023-06-26 17:33:14
 */
public class Question implements Serializable {
    private static final long serialVersionUID = -71247253858023953L;

    private Integer questionid;

    private String material;

    private String question;

    private Integer type;

    private String answer;

    private String score;

    private String itema;

    private String itemb;

    private String itemc;

    private String itemd;

    private String paperid;


    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getItema() {
        return itema;
    }

    public void setItema(String itema) {
        this.itema = itema;
    }

    public String getItemb() {
        return itemb;
    }

    public void setItemb(String itemb) {
        this.itemb = itemb;
    }

    public String getItemc() {
        return itemc;
    }

    public void setItemc(String itemc) {
        this.itemc = itemc;
    }

    public String getItemd() {
        return itemd;
    }

    public void setItemd(String itemd) {
        this.itemd = itemd;
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

}

