package com.dwp.controller;

import com.dwp.client.RestClient;
import com.dwp.exception.UserNotFoundException;
import com.dwp.model.Instruction;
import com.dwp.model.User;
import com.dwp.service.FetchUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Main controller class
 */

@RestController
@Slf4j
@RequestMapping(path = UserController.API_PATH)
public class UserController {

    private final String dwpApiUrl;

    public static final String API_PATH = "api/v1/";

    private static final String INSTRUCTIONS_ENDPOINT  = "instructions";

    private static final String USER_ID_ENDPOINT  = "user/{id}";

    private static final String USERS_ENDPOINT = "users";

    private static final String CITY_DISTANCE_ENDPOINT  = "users/{city}/{distance}";

    @Autowired
    private FetchUserService fetchUserService;

    public UserController(@Value("${java-online-test.url:NA}") final String dwpApiUrl){
        this.dwpApiUrl = dwpApiUrl;
    }

    @GetMapping("/")
    public String instructions() {
        log.info("Fetching instructions..");
        ResponseEntity responseEntity = RestClient.restTemplate().getForEntity(dwpApiUrl +"/"+INSTRUCTIONS_ENDPOINT, Instruction.class);
        Instruction instruction = (Instruction) responseEntity.getBody();
        return instruction.getTodo();
    }

    @GetMapping(value = USERS_ENDPOINT)
    public List<User> allUsers() {
        log.info("Fetching all users..");
        ResponseEntity responseEntity = RestClient.restTemplate().exchange(dwpApiUrl + "/" + USERS_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        List<User> users = (List<User>) responseEntity.getBody();
        return users;
    }

    @GetMapping(value = USER_ID_ENDPOINT)
    public User userDetails(@PathVariable("id") final String id) {
        log.info("Fetching user details..");
        try {
            ResponseEntity responseEntity = RestClient.restTemplate().exchange(dwpApiUrl + "/" + USER_ID_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
            }, Collections.singletonMap("id", id));
            User user = (User) responseEntity.getBody();
            return user;
        }catch(Exception exception){
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping(value = CITY_DISTANCE_ENDPOINT, produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity allUsersWithinADistance(@PathVariable("city") final String city, @PathVariable double distance) throws Exception{
        log.info("Fetching all users within {} city and a given distance of {} miles ..", city, distance);
        return new ResponseEntity<>(fetchUserService.fetchUsers(city, distance), HttpStatus.OK);
    }

   }
