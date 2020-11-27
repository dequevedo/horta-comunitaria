package com.energy.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalCard {

    private String cardNumber;

    private String cpf;

    private String expiry;

    private String name;

    private String cvv;
}