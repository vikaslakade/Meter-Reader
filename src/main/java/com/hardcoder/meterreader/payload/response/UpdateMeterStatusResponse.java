package com.hardcoder.meterreader.payload.response;

public class UpdateMeterStatusResponse {
    private String newMeterStatus;


    public UpdateMeterStatusResponse(String newMeterStatus) {
        this.newMeterStatus = newMeterStatus;

    }

    public String getNewMeterStatus() {
        return newMeterStatus;
    }

    public void setNewMeterStatus(String newMeterStatus) {
        this.newMeterStatus = newMeterStatus;
    }


}
