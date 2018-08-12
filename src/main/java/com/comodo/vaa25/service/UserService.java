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
     * @param userId user id.
     * @param user editing user.
     * @throws IllegalArgumentException if editing user not exists.
     */
    void editUser(Long userId, User user);

    /**
     * Deletes user with specified id.
     *
     * @param userId id of user to delete.
     * @throws IllegalArgumentException if deleting user not exists.
     */
    void deleteUser(Long userId);
}
