package com.hardcoder.meterreader.controllers;

import com.hardcoder.meterreader.models.Meter;
import com.hardcoder.meterreader.models.MeterStatus;
import com.hardcoder.meterreader.models.Reading;
import com.hardcoder.meterreader.payload.request.DataPushRequest;
import com.hardcoder.meterreader.payload.request.UpdateMeterStatusRequest;
import com.hardcoder.meterreader.payload.response.DataPushResponse;
import com.hardcoder.meterreader.payload.response.MessageResponse;
import com.hardcoder.meterreader.payload.response.UpdateMeterStatusResponse;
import com.hardcoder.meterreader.repository.MeterReadingRepository;
import com.hardcoder.meterreader.repository.MeterStatusRepository;
import com.hardcoder.meterreader.repository.ReadingRepository;
import com.hardcoder.meterreader.repository.RegisterEMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ABCD/EM/api")
public class MeterReaderController {

    private static final String METER_STATUS="Disconnect";

    @Autowired
    private RegisterEMRepository registerEMRepository;
    @Autowired
    private MeterStatusRepository meterStatusRepository;

    @Autowired
    private MeterReadingRepository meterReadingRepository;
    @Autowired
    private ReadingRepository readingRepository;

    @PostMapping("/meter-status")

    public ResponseEntity<?> getUpdateMeterStatus(@Valid @RequestBody UpdateMeterStatusRequest updateMeterStatusRequest) {

        if ( !registerEMRepository.existsByEMSN(updateMeterStatusRequest.getEMSN())) {
            return ResponseEntity.badRequest().body(new MessageResponse(Instant.now().getEpochSecond(),"Error: EMSN is not valid"));
        }

        //create meter status object
        MeterStatus meterStatus=new MeterStatus(updateMeterStatusRequest.getUsername(), updateMeterStatusRequest.getEMSN(), updateMeterStatusRequest.getEMName());
        meterStatus.setMeterStatus(updateMeterStatusRequest.getMeterStatus());
        meterStatus.setLastUpdate(LocalDateTime.now());
        meterStatusRepository.save(meterStatus);


     return  ResponseEntity.ok(new UpdateMeterStatusResponse(updateMeterStatusRequest.getMeterStatus()));
    }


    @PostMapping("/data-push")
    public ResponseEntity<?> getDataPush(@Valid @RequestBody DataPushRequest dataPushRequest) {
        if ( !meterStatusRepository.existsByEMSN(dataPushRequest.getEMSN())) {
            return ResponseEntity.badRequest().body(new MessageResponse(Instant.now().getEpochSecond(),"EMSN is not valid"));
        }
        //MeterStatus meter = new MeterStatus(dataPushRequest.getUsername(), dataPushRequest.getEMSN(), dataPushRequest.getEMName());
        MeterStatus meter=meterStatusRepository.findByEMSN(dataPushRequest.getEMSN());
        if(METER_STATUS.equals(meter.getMeterStatus())){
            return ResponseEntity.badRequest().body(new MessageResponse(Instant.now().getEpochSecond(),"Meter is Disconnect"));

        }

        List<Reading> readingList=dataPushRequest.getReadingsList().stream().map(reading ->{
         Reading listReading=   new Reading(reading.getTS(),reading.getPulses(),dataPushRequest.getStartTimeStamp(),dataPushRequest.getInterval());
         listReading.setMeterStatus(meter);
         return listReading;

        }).collect(Collectors.toList());


        readingRepository.saveAll(readingList);
        return ResponseEntity.ok(new DataPushResponse(300L, 1L,meter.getMeterStatus()));
     }
    }
