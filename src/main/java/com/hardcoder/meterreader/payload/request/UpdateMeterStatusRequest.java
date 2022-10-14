package com.hardcoder.meterreader.payload.request;

import javax.validation.constraints.NotBlank;

public class UpdateMeterStatusRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String EMSN;
    @NotBlank
    private String EMName;

    @NotBlank
    private String MeterStatus;

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

    public String getEMName() {
        return EMName;
    }

    public void setEMName(String EMName) {
        this.EMName = EMName;
    }

    public String getMeterStatus() {
        return MeterStatus;
    }

    public void setMeterStatus(String meterStatus) {
        MeterStatus = meterStatus;
    }
}
