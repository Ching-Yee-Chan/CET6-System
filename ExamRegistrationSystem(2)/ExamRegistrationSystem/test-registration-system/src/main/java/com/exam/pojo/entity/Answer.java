package com.exam.pojo.entity;

import java.io.Serializable;

/**
 * (Answer)实体类
 *
 * @author makejava
 * @since 2023-06-26 22:47:24
 */
public class Answer implements Serializable {
    private static final long serialVersionUID = 255927394756491303L;

    private String answerexam;

    private Integer answerquestion;

    private String answeruser;

    private Integer answertype;

    private Integer answeroption;

    private String answercontent;


    public String getAnswerexam() {
        return answerexam;
    }

    public void setAnswerexam(String answerexam) {
        this.answerexam = answerexam;
    }

    public Integer getAnswerquestion() {
        return answerquestion;
    }

    public void setAnswerquestion(Integer answerquestion) {
        this.answerquestion = answerquestion;
    }

    public String getAnsweruser() {
        return answeruser;
    }

    public void setAnsweruser(String answeruser) {
        this.answeruser = answeruser;
    }

    public Integer getAnswertype() {
        return answertype;
    }

    public void setAnswertype(Integer answertype) {
        this.answertype = answertype;
    }

    public Integer getAnsweroption() {
        return answeroption;
    }

    public void setAnsweroption(Integer answeroption) {
        this.answeroption = answeroption;
    }

    public String getAnswercontent() {
        return answercontent;
    }

    public void setAnswercontent(String answercontent) {
        this.answercontent = answercontent;
    }

}

