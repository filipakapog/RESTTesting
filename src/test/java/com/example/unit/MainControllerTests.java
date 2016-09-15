package com.example.unit;

import com.example.controller.MainController;
import com.example.exceptions.UserAlreadyDeletedException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static com.example.messages.ReturnMessages.*;
import static org.mockito.Mockito.*;

/**
 * Created by fghimpeteanu on 9/14/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTests {
    @Mock
    UserService userServiceMock;

    @InjectMocks
    MainController mainController;

    private static Map<Integer, User> usersPool;

    @BeforeClass
    public static void initUsersPool() {
        usersPool = new HashMap<>();
        int userId = 1;
        User user = new User();
        user.setFirstName("Ion");
        user.setLastName("Popescu");
        user.setAge(23);
        usersPool.put(userId, user);

        userId++;
        user = new User();
        user.setFirstName("Gigi");
        user.setLastName("Dolanescu");
        user.setAge(25);
        usersPool.put(userId, user);

        userId++;
        user = new User();
        user.setFirstName("Gigi");
        user.setLastName("Dolanescu");
        user.setAge(26);
        usersPool.put(userId, user);
    }

    @AfterClass
    public static void deleteUserPool() {
        usersPool = null;
    }

    @Test
    public void testAddUser_ServiceMethodCalled() throws UserAlreadyExistsException {
        int userId = 1;
        User user = usersPool.get(userId);
        RequestEntity<User> requestEntity = new RequestEntity<>(user, HttpMethod.POST, URI.create("dummy"));

        mainController.addUser(userId, requestEntity);
        verify(userServiceMock, times(1)).addUser(userId, user);
    }

    @Test
    public void testAddUser_AddingSameUserTwiceCase() throws UserAlreadyExistsException {
        int userId = 1;
        User user = usersPool.get(userId);
        RequestEntity<User> requestEntity = new RequestEntity<>(user, HttpMethod.POST, URI.create("dummy"));

        doThrow(new UserAlreadyExistsException())
                .when(userServiceMock)
                .addUser(userId, user);

        mainController.addUser(userId, requestEntity);
        ResponseEntity<String> responseEntity = mainController.addUser(userId, requestEntity);
        String expectedMessage = MESSAGE_USER_INSERT_NOT_OK;
        String actualMessage = responseEntity.getBody();

        verify(userServiceMock, times(2)).addUser(userId, user);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testDeleteUser_ServiceMethodCalled() throws UserAlreadyDeletedException {
        int userId = 1;
        mainController.deleteUser(userId);
        verify(userServiceMock, times(1)).deleteUser(userId);
    }


    @Test
    public void testDeleteUser_NonExitingUserCase()  throws UserAlreadyDeletedException {
        int userId = 1;

        doThrow(new UserAlreadyDeletedException())
                .when(userServiceMock)
                .deleteUser(userId);

        ResponseEntity<String> responseEntity =  mainController.deleteUser(userId);
        String expectedMessage = MESSAGE_USER_DELETED_NOT_OK;
        String actualMessage = responseEntity.getBody();
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testGetUser_ServiceMethodCalled() {
        int userId = 1;
        mainController.getUser(userId);
        verify(userServiceMock, times(1)).getUser(userId);
    }

    @Test
    public void testGetUser_ExistingUserCase() {
        int userId = 1;
        User expectedUser = usersPool.get(userId);
        HttpStatus expectedStatus = HttpStatus.OK;

        when(userServiceMock.getUser(userId))
                .thenReturn(expectedUser);

        ResponseEntity<User> actualResponseEntity = mainController.getUser(1);
        verify(userServiceMock, times(1)).getUser(userId);
        User actualUser = actualResponseEntity.getBody();
        HttpStatus actualStatus = actualResponseEntity.getStatusCode();

        assertEquals(expectedStatus, actualStatus);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetUser_NonExistingUserCase() {
        int userId = 1;
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;

        when(userServiceMock.getUser(userId))
                .thenReturn(null);

        ResponseEntity<User> actualResponseEntity = mainController.getUser(1);
        verify(userServiceMock, times(1)).getUser(userId);
        HttpStatus actualStatus = actualResponseEntity.getStatusCode();

        assertEquals(expectedStatus, actualStatus);

        assertNull(actualResponseEntity.getBody());
    }

    @Test
    public void testGetUsers_ServiceMethodCalled() {
        mainController.getUsers();
        verify(userServiceMock, times(1)).getAllUsers();
    }

    @Test
    public void testGetUsers_ExistingUserListCase() {
        List<User> expectedUserList = new ArrayList<>();
        HttpStatus expectedStatus = HttpStatus.OK;
        int userId = 1;
        expectedUserList.add(usersPool.get(userId++));
        expectedUserList.add(usersPool.get(userId));


        when(userServiceMock.getAllUsers())
                .thenReturn(expectedUserList);

        ResponseEntity<List<User>> responseEntity = mainController.getUsers();
        List<User> actualUserList = responseEntity.getBody();
        HttpStatus actualStatus = responseEntity.getStatusCode();

        assertEquals(expectedStatus, actualStatus);

        assertNotNull(actualUserList);
        assertEquals(expectedUserList.size(), actualUserList.size());
        for (int i = 0; i < actualUserList.size(); i++) {
            assertEquals(expectedUserList.get(i), actualUserList.get(i));
        }
    }

    @Test
    public void testGetUsers_EmptyUserListCase() {
        List<User> expectedUserList = new ArrayList<>();
        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;

        when(userServiceMock.getAllUsers())
                .thenReturn(expectedUserList);

        ResponseEntity<List<User>> responseEntity = mainController.getUsers();
        List<User> actualUserList = responseEntity.getBody();
        HttpStatus actualStatus = responseEntity.getStatusCode();

        assertEquals(expectedStatus, actualStatus);

        assertNotNull(actualUserList);
        assertTrue(actualUserList.isEmpty());
    }

}
