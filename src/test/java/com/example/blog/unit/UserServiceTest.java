package com.example.blog.unit;
import com.example.blog.entity.User;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser() {
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                .id(1)
                .name("Fellipe")
                .username("felliperey")
                .build()));
        User response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Fellipe", response.getName()),
                () -> assertEquals("felliperey", response.getUsername())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.findById(2));
    }

    @Test
    @DisplayName("#getAllUsers > When there is no registered user > Return an empty list")
    void getAllUsersWhenThereIsNoUserReturnAnEmptyList () {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllUsers().isEmpty());
        // AssertEquals(0, service.getAllUsers().size
    }

    @Test
    @DisplayName("#getAllUsers > When at least one user is registered > Return a non empty list")
    void getAllUsersWhenAtLeastOneUserIsRegisteredReturnANonEmptyList () {
        when(repository.findAll()).thenReturn(new ArrayList<>() {
            {
            add(new User(1, "user", "user123", "user@user.com",
                    "1111-1111", "user.com.br", null));
            }
        }).thenReturn(new ArrayList<>() {
            {
                add(new User(1, "user1", "user123", "user1@user.com",
                        "1111-1111", "user1.com.br", null));
                add(new User(2, "user2", "user456", "user2@user.com",
                        "2222-2222", "user2.com.br", null));
            }
        });

        List<User> firstCallUsers = service.getAllUsers();
        List<User> secondCallUsers = service.getAllUsers();

        assertEquals(1, firstCallUsers.size());
        assertEquals(1, firstCallUsers.get(0).getId());

        assertEquals(2, secondCallUsers.size());
        assertEquals(1, secondCallUsers.get(0).getId());
        assertEquals(2, secondCallUsers.get(1).getId());

    }
}