package com.gsmarena.firstsample.retrofit2.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseRegister implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("token")
    @Expose
    private String token;
    private final static long serialVersionUID = -5008198940219704965L;

    /**
     * No args constructor for use in serialization
     */
    public ResponseRegister() {
    }

    /**
     * @param id
     * @param token
     */
    public ResponseRegister(Integer id, String token) {
        super();
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}