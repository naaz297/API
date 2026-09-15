package org.example.demofirst.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> users = new ArrayList<>();

    // GET - Get all users
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    // POST - Add user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {

        users.add(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Created");
    }

    // PUT - Update user
    @PutMapping
    public User updateUser(@RequestBody User user) {

        for (User u : users) {
            if (u.getId() == user.getId()) {

                u.setName(user.getName());
                u.setEmail(user.getEmail());

                return u;
            }
        }

        return null;
    }

    // DELETE - Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        boolean removed = users.removeIf(u -> u.getId() == id);

        if (removed) {
            return "User deleted successfully";
        }

        return "User not found";
    }

    @GetMapping("/search")
    public List<User> searchUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        List<User> result = new ArrayList<>();

        for (User u : users) {

            if ((name == null || u.getName().equalsIgnoreCase(name))
                    && (email == null || u.getEmail().equalsIgnoreCase(email))) {

                result.add(u);
            }
        }

        return result;
    }
    @GetMapping("/info/{id}")
    public String getInfo(
            @PathVariable int id,
            @RequestParam String name,
            @RequestHeader("User-Agent") String userAgent) {

        return "ID: " + id +
                ", Name: " + name +
                ", User Agent: " + userAgent;
    }
}