package com.community.farm.service;

import com.community.farm.model.SensorData;
import com.community.farm.repository.SensorDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public SensorData postSensorData(SensorData sensorData){
        log.info("Posting Sensor Data with sensorIdentifier=[{}]", sensorData.getSensorIdentifier());
        return sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getAllSensorData() {
        log.info("Getting all Sensor Data");
        return sensorDataRepository.findAll();
    }

//    public UserResponse getUser(String userId) {
//        log.info("Getting User with id=[{}]", userId);
//        try{
//            SensorData sensorData = sensorDataRepository.findByUserId(UUID.fromString(userId)).orElseThrow();
//            return UserResponse.builder()
//                    .userId(sensorData.getUserId())
//                    .email(sensorData.getEmail())
//                    .name(sensorData.getName())
//                    .phoneNumber(sensorData.getPhoneNumber())
//                    .build();
//        }catch (NoSuchElementException ex){
//            throw new CommunityFarmException(
//                    Messages.USER_NOT_FOUND,
//                    new Throwable("Usuário não encontrado."));
//        }
//    }
}
