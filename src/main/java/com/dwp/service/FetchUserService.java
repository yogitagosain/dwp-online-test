package com.dwp.service;

import com.dwp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Main service to fetch users from London and Nearby London Services
 */

@Service
@Slf4j
public class FetchUserService {

    @Autowired
    private LondonService londonService;

    @Autowired
    private NearbyLondonService nearbyLondonService;

    /**
     *
     * @param city
     * @param distance
     * @return
     * @throws InterruptedException
     */
    public List<User>fetchUsers(String city, double distance) throws InterruptedException{

        List<CompletableFuture<List<User>>> usersFutures = new ArrayList<>();
        usersFutures.add(londonService.fetchUsersFromLondon(city));
        usersFutures.add(nearbyLondonService.fetchUsersFromNearLondon(distance));
        final List<User> allUsersList = new LinkedList<>();
        final long count = usersFutures.stream().map(CompletableFuture::join).map(allUsersList::addAll).count();
        return allUsersList;

    }

    public void setLondonService(LondonService londonService) {
        this.londonService = londonService;
    }

    public void setNearbyLondonService(NearbyLondonService nearbyLondonService) {
        this.nearbyLondonService = nearbyLondonService;
    }
}
