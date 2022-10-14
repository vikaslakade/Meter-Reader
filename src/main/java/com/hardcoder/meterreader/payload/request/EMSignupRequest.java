package com.hardcoder.meterreader.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class EMSignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String EMname;
 
    @NotBlank
    @Size(max = 50)
    private String EMSN;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String customername;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEMSN() {
        return EMSN;
    }

    public void setEMSN(String EMSN) {
        this.EMSN = EMSN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEMname() {
        return EMname;
    }

    public void setEMname(String EMname) {
        this.EMname = EMname;
    }
}
