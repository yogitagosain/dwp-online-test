import com.dwp.model.User;
import com.dwp.service.FetchUserService;
import com.dwp.service.LondonService;
import com.dwp.service.NearbyLondonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RunWith(MockitoJUnitRunner.class)
public class FetchUserServiceTest {


    @Mock
    private LondonService londonService;

    @Mock
    private NearbyLondonService nearbyLondonService;

    private FetchUserService fetchUserService;

    @Before
    public void setup(){
        fetchUserService = new FetchUserService();
        fetchUserService.setLondonService(londonService);
        fetchUserService.setNearbyLondonService(nearbyLondonService);
    }

    @Test
    public void basicTest() throws InterruptedException {
        //Given
        String city = "London";
        double distance = 50;
        User user1 = new User(1L , "Y", "G", "","",0,0);
        User user2 = new User(2L , "A", "B", "","",0,0);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(londonService.fetchUsersFromLondon(city)).thenReturn(CompletableFuture.completedFuture(userList));
        Mockito.when(nearbyLondonService.fetchUsersFromNearLondon(distance)).thenReturn(CompletableFuture.completedFuture(userList));

        //When
        List<User> users = fetchUserService.fetchUsers(city, distance);

        //Then
        Assert.assertEquals(4,users.size());
    }
}
