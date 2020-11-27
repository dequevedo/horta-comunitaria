package com.community.farm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sensordata")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {
    @Id
    @GeneratedValue(generator = "question_generator")
    private UUID id;
    private String sensorIdentifier;
    private String description;
    private float value;
    private String date;
}
