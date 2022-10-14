package com.hardcoder.meterreader.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(	name = "meter")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String username;
    @NotBlank
    private String EMSN;
    @NotBlank
    private String EMName;

    private String StartTimeStamp;

    private int eMInterval;


//mappedBy = "meter", cascade = CascadeType.ALL,
   // @OneToMany(cascade={CascadeType.REMOVE, CascadeType.MERGE},fetch = FetchType.LAZY)
    //@JoinColumn(name = "reading_id", nullable = false)
  //  private List<Reading> readingsList;

    public Meter(String username, String EMSN, String EMName, String startTimeStamp, int eMInterval) {
        this.username = username;
        this.EMSN = EMSN;
        this.EMName = EMName;
        StartTimeStamp = startTimeStamp;
        this.eMInterval = eMInterval;
       // this.readingsList = readingsList;
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

    public String getStartTimeStamp() {
        return StartTimeStamp;
    }

    public void setStartTimeStamp(String startTimeStamp) {
        StartTimeStamp = startTimeStamp;
    }

    public int geteMInterval() {
        return eMInterval;
    }

    public void seteMInterval(int eMInterval) {
        this.eMInterval = eMInterval;
    }

//        public List<Reading> getReadingsList() {
//        return readingsList;
//    }
//
//    public void setReadingsList(List<Reading> readingsList) {
//        this.readingsList = readingsList;
//    }
}
