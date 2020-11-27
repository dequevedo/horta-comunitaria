package com.community.farm.controller;

import com.community.farm.model.SensorData;
import com.community.farm.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensor-data")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    @ResponseBody
    public SensorData postSensorData(@Valid @RequestBody SensorData sensorData) {
        return sensorDataService.postSensorData(sensorData);
    }

    @GetMapping()
    @ResponseBody
    public List<SensorData> getAllSensorData() {
        return sensorDataService.getAllSensorData();
    }
}

