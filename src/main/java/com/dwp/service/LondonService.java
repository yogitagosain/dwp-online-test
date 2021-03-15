package com.dwp.service;

import com.dwp.client.RestClient;
import com.dwp.enums.Status;
import com.dwp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Service to fetch users from London city
 */
@Service
@Slf4j
public class LondonService {

    private final String dwpApiUrl;

    private static final String CITY_ENDPOINT  = "/city/{city}/users";

    public LondonService(@Value("${java-online-test.url:NA}") final String dwpApiUrl){
        this.dwpApiUrl = dwpApiUrl;
    }

    /**
     *
     * @param city
     * @return
     * @throws InterruptedException
     */
    @Async
    public CompletableFuture<List<User>> fetchUsersFromLondon(String city) throws InterruptedException{
        log.info("Fetching all users in {} city.", city);
        ResponseEntity<List<User>> responseEntity = RestClient.restTemplate().exchange(dwpApiUrl+CITY_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }, Collections.singletonMap("city",city));
        List<User> users = (List<User>) responseEntity.getBody();
        log.info("Fetched {} users.", users.size());
        users.stream().forEach(user -> user.setResidenceStatus(Status.LONDON));
        return CompletableFuture.completedFuture(users);
    }
}
