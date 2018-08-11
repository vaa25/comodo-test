package com.comodo.vaa25.api;

import com.comodo.vaa25.entity.User;
import com.comodo.vaa25.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
    public Page<User> getUsersPage(
            @RequestParam(value = "page") final Integer page,
            @RequestParam(value = "size") final Integer size
    ) {
        return userService.getUserPage(page, size);
    }

    @PostMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void addNewUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void editUser(@RequestBody User user) {
        userService.editUser(user);
    }

    @DeleteMapping(path = "/users", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> logIllegalArgumentException(
            final HttpServletRequest req, final IllegalArgumentException ex
    ) {
        log.error("Request: {} raised IllegalArgumentException:{}", req.getRequestURL(), ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
