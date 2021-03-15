package com.dwp.service;

import com.dwp.client.RestClient;
import com.dwp.enums.Status;
import com.dwp.model.User;
import com.dwp.utility.DistanceUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Service to fetch users from nearby London city
 */

@Service
@Slf4j
public class NearbyLondonService {

    private final String dwpApiUrl;

    private final double londonLat;

    private final double londonLon;

    private static final String USERS_ENDPOINT = "users";


    public NearbyLondonService(@Value("${java-online-test.url:NA}") final String dwpApiUrl,
                               @Value("${london.latitude:NA}") final double londonLat,
                               @Value("${london.longitude:NA}") final double londonLon){
        this.dwpApiUrl = dwpApiUrl;
        this.londonLat = londonLat;
        this.londonLon = londonLon;
    }

    /**
     *
     * @param distance
     * @return
     * @throws InterruptedException
     */

    @Async
    public CompletableFuture<List<User>> fetchUsersFromNearLondon(double distance) throws InterruptedException{
        log.info("Fetching all users within {} miles of London.", distance);
        ResponseEntity<List<User>> responseEntity = RestClient.restTemplate().exchange(dwpApiUrl+"/"+USERS_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        List<User> users = (List<User>) responseEntity.getBody();
        log.info("Fetched {} users.", users.size());
        final List<User> finalUsers = users.stream().filter(user ->
                DistanceUtility.distance(londonLat,londonLon,user.getLatitude(), user.getLongitude(),"N")<=distance)
                .collect(Collectors.toList());
        finalUsers.stream().forEach(user -> user.setResidenceStatus(Status.NEAR_LONDON));
        log.info("Found {} eligible users.", finalUsers.size());
        return CompletableFuture.completedFuture(finalUsers);
    }
}
