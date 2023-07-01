package com.exam.pojo.entity;

import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author makejava
 * @since 2023-06-28 22:02:43
 */
public class Test implements Serializable {
    private static final long serialVersionUID = 772668212610604143L;

    private String aaaaa;

    private String bbbb;


    public String getAaaaa() {
        return aaaaa;
    }

    public void setAaaaa(String aaaaa) {
        this.aaaaa = aaaaa;
    }

    public String getBbbb() {
        return bbbb;
    }

    public void setBbbb(String bbbb) {
        this.bbbb = bbbb;
    }

}

