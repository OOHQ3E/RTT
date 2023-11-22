import org.example.User.User;
import org.example.User.UserManager;
import org.example.User.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserManagerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserManager userManager;

    @Test
    public void testGetUsernameById(){
        when(userService.getUserById(1)).thenReturn(new User(1,"jozsika"));
        String actualUsername = userManager.getUsernameById(1);
        String expected = "jozsika";
        assertEquals(expected,actualUsername);

        verify(userService).getUserById(1);
    }
    @Test
    public void testUpdateUsername(){
        when(userService.getUserById(1)).thenReturn(new User(1,"jozsika"));
        userManager.updateUserUsername(1,"jozsi");
        verify(userService).getUserById(1);
        verify(userService).updateUser(argThat(user ->user.getUsername().equals("jozsi")));
    }
    @Test
    public void testGetAllUsers(){
        when(userService.getAllUsers()).thenReturn(Arrays.asList(
                new User(1,"user1"),
                new User (2,"user2")
        ));
        List<User> users = userManager.getAllUsers();
        verify(userService).getAllUsers();

        int expectedListSize = 2;
        int actualListSize = users.size();
        assertEquals(expectedListSize,actualListSize);

        String expectedU1 = "user1";
        String actualU1 = users.get(0).getUsername();
        String expectedU2 = "user2";
        String actualU2 = users.get(1).getUsername();
        assertEquals(expectedU1,actualU1);
        assertEquals(expectedU2,actualU2);

    }
}
