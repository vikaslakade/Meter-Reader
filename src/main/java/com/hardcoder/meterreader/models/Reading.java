package com.hardcoder.meterreader.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "reading")
public class Reading {
   // @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private    Long TS;

    private   Long Pulses;

    private String StartTimeStamp;

    private int eMInterval;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emsn", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MeterStatus meterStatus;

    public Reading(){

    }

    public Reading(Long TS, Long pulses, String startTimeStamp, int eMInterval) {
        this.TS = TS;
        Pulses = pulses;
        StartTimeStamp = startTimeStamp;
        this.eMInterval = eMInterval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTS() {
        return TS;
    }

    public void setTS(Long TS) {
        this.TS = TS;
    }

    public Long getPulses() {
        return Pulses;
    }

    public void setPulses(Long Pulses) {
        this.Pulses = Pulses;
    }

    public MeterStatus getMeterStatus() {
        return meterStatus;
    }

    public void setMeterStatus(MeterStatus meterStatus) {
        this.meterStatus = meterStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reading reading = (Reading) o;
        return eMInterval == reading.eMInterval && Objects.equals(id, reading.id) && Objects.equals(TS, reading.TS) && Objects.equals(Pulses, reading.Pulses) && Objects.equals(StartTimeStamp, reading.StartTimeStamp) && Objects.equals(meterStatus, reading.meterStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, TS, Pulses, StartTimeStamp, eMInterval, meterStatus);
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id=" + id +
                ", TS=" + TS +
                ", Pulses=" + Pulses +
                ", StartTimeStamp='" + StartTimeStamp + '\'' +
                ", eMInterval=" + eMInterval +
                ", meterStatus=" + meterStatus +
                '}';
    }
}
