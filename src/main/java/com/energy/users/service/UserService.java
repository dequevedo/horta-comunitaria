package com.energy.users.service;

import com.energy.users.exception.EnergyUsersException;
import com.energy.users.exception.Messages;
import com.energy.users.model.*;
import com.energy.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment env;

    public UserResponse getUser(String userId) {
        log.info("Getting User with id=[{}]", userId);
        try{
            User user = userRepository.findByUserId(UUID.fromString(userId)).orElseThrow();
            return UserResponse.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
        }catch (NoSuchElementException ex){
            throw new EnergyUsersException(
                    Messages.USER_NOT_FOUND,
                    new Throwable("Usuário não encontrado."));
        }
    }

    public UserResponse postUser(User user) {
        log.info("Posting User with id=[{}]", user.getUserId());

        //TODO remover ifs e colocar annotations no objeto User
        if(user.getEmail() == null || user.getEmail().isBlank()|| user.getEmail().isEmpty()){
            throw new EnergyUsersException(
                    Messages.EMAIL_REQUIRED,
                    new Throwable("Email não informado."));
        }
        if(user.getName() == null || user.getName().isBlank()|| user.getName().isEmpty()){
            throw new EnergyUsersException(
                    Messages.NAME_REQUIRED,
                    new Throwable("Nome não informado."));
        }
        if(user.getPassword() == null || user.getPassword().isBlank()|| user.getPassword().isEmpty()){
            throw new EnergyUsersException(
                    Messages.PASSWORD_REQUIRED,
                    new Throwable("Senha não informada."));
        }
        if(user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()|| user.getPhoneNumber().isEmpty()){
            throw new EnergyUsersException(
                    Messages.PHONE_NUMBER_REQUIRED,
                    new Throwable("Telefone não informado."));
        }


        if (!userRepository.findByEmail(user.getEmail()).isEmpty())
            throw new EnergyUsersException(
                    Messages.EMAIL_ALREADY_REGISTERED,
                    new Throwable("Email já registrado."));

        User savedUser = userRepository.save(user);
        return UserResponse.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }

    public UserInfo getUserInfo(String userId) {
        log.info("Getting User Info with id=[{}]", userId);
        val user = getUser(userId);
        val userInfo = UserInfo.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .picture("pictureMock")
                .build();
        return userInfo;
    }

    public UserLoginResponse userLogin(UserLogin userLogin) {
        log.info("Logging in User with email=[{}]", userLogin.getEmail());
        //TODO mudar retorno e implementar jwt

        Optional<User> userOptional = userRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword()).stream().findFirst();

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return UserLoginResponse.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .picture(null)
                    .build();
        } else {
            throw new EnergyUsersException(
                    Messages.LOGIN_FAILED,
                    new Throwable("Falha de acesso. Por favor verifique seu email e senha e tente novamente."));
        }
    }

    public List<User> getAllUsers() {
        log.info("Getting all Users");
        return userRepository.findAll();
    }
}
