package com.comodo.vaa25.service;

import com.comodo.vaa25.entity.User;
import org.springframework.data.domain.Page;

/**
 * Service to work with users.
 */
public interface UserService {
    /**
     * Fetches one page of users.
     * 
     * @param page page number, 0 - first.
     * @param size page size.
     * @return one page of users.
     */
    Page<User> getUserPage(Integer page, Integer size);

    /**
     * Creates new user.
     * 
     * @param user user to create.
     * @throws IllegalArgumentException if creating user has not null id.
     */
    void createUser(User user);

    /**
     * Edits user.
     *
     * @param user editing user.
     * @throws IllegalArgumentException if editing user not exists.
     */
    void editUser(User user);

    /**
     * Deletes user with specified in {@link User} id. Other fields of {@link User} not important. 
     *
     * @param user user to delete.
     * @throws IllegalArgumentException if deleting user not exists.
     */
    void deleteUser(User user);
}
