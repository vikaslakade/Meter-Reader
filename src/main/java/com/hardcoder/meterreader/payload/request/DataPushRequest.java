package com.hardcoder.meterreader.payload.request;

import com.hardcoder.meterreader.models.Readings;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class DataPushRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String EMSN;
    @NotBlank
    private String EMName;
    @NotBlank
    private String StartTimeStamp;

    private int interval;


    private List<Readings> readingsList;

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

    public String getStartTimeStamp() {
        return StartTimeStamp;
    }

    public void setStartTimeStamp(String startTimeStamp) {
        StartTimeStamp = startTimeStamp;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<Readings> getReadingsList() {
        return readingsList;
    }

    public void setReadingsList(List<Readings> readingsList) {
        this.readingsList = readingsList;
    }
}
