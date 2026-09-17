package org.example.demofirst.app;

import org.example.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    // GET - Get all users
    public List<User> getUsers() {
        return users;
    }

    // POST - Create user
    public void createUser(User user) {
        users.add(user);
    }

    // PUT - Update user
    public User updateUser(User user) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getId() == user.getId()) {

                users.set(i, user);

                return user;
            }
        }

        throw new UserNotFoundException(
                "User not found with id: " + user.getId()
        );
    }

    // DELETE - Delete user
    public void deleteUser(int id) {

        boolean removed = users.removeIf(
                user -> user.getId() == id
        );

        if (!removed) {
            throw new UserNotFoundException(
                    "User not found with id: " + id
            );
        }
    }

    // SEARCH - Search by name and email
    public List<User> searchUser(String name, String email) {

        return users.stream()
                .filter(user ->
                        (name == null ||
                                user.getName().equalsIgnoreCase(name))
                                &&
                                (email == null ||
                                        user.getEmail().equalsIgnoreCase(email))
                )
                .collect(Collectors.toList());
    }
}