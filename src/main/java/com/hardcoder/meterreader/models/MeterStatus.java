package com.hardcoder.meterreader.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(	name = "Meter_Status ")

//uniqueConstraints = {
//@UniqueConstraint(columnNames = "username"),
//@UniqueConstraint(columnNames = "EMSN")
//        }
public class MeterStatus {
  //  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String username;

    @Id
    @NotBlank
    private String EMSN;
    @NotBlank
    private String EMName;

    @NotBlank
    private String MeterStatus;


    private LocalDateTime lastUpdate;

    public MeterStatus(){}

    public MeterStatus(String username, String EMSN, String EMName) {
        this.username = username;
        this.EMSN = EMSN;
        this.EMName = EMName;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterStatus that = (MeterStatus) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(EMSN, that.EMSN) && Objects.equals(EMName, that.EMName) && Objects.equals(MeterStatus, that.MeterStatus) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, EMSN, EMName, MeterStatus, lastUpdate);
    }

    @Override
    public String toString() {
        return "MeterStatus{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", EMSN='" + EMSN + '\'' +
                ", EMName='" + EMName + '\'' +
                ", MeterStatus='" + MeterStatus + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
