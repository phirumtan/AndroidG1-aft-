package com.gsmarena.firstsample.retrofit2.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterItem implements Serializable {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    private final static long serialVersionUID = 1034368479456029015L;

    /**
     * No args constructor for use in serialization
     */
    public RegisterItem() {
    }

    /**
     * @param password
     * @param email
     */
    public RegisterItem(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}