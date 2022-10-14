package com.hardcoder.meterreader.payload.response;

public class DataPushResponse {
    private Long nextUploadSchedule;
      private Long  Interval;
      private String ConnectionStatus;

    public DataPushResponse(Long nextUploadSchedule, Long interval, String connectionStatus) {
        this.nextUploadSchedule = nextUploadSchedule;
        Interval = interval;
        ConnectionStatus = connectionStatus;
    }

    public Long getNextUploadSchedule() {
        return nextUploadSchedule;
    }

    public void setNextUploadSchedule(Long nextUploadSchedule) {
        this.nextUploadSchedule = nextUploadSchedule;
    }

    public Long getInterval() {
        return Interval;
    }

    public void setInterval(Long interval) {
        Interval = interval;
    }

    public String getConnectionStatus() {
        return ConnectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        ConnectionStatus = connectionStatus;
    }
}
