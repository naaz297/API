package org.example.demofirst.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET - Get all users
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // POST - Create user
    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody User user) {

        userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Created");
    }

    // PUT - Update user
    @PutMapping
    public User updateUser(
            @RequestBody User user) {

        return userService.updateUser(user);
    }

    // DELETE - Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable int id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }

    // SEARCH - Search by name and email
    @GetMapping("/search")
    public List<User> searchUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        return userService.searchUser(name, email);
    }

    // INFO - PathVariable + RequestParam + RequestHeader
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