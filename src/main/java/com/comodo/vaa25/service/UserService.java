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
     * Saves user. If user has id, then rewrites user with specified id, if not - then adds new user. 
     * 
     * @param user user to save.
     */
    void saveUser(User user);

    /**
     * Deletes user with specified in {@link User} id. Other fields of {@link User} not important. 
     *
     * @param user user to delete.
     */
    void deleteUser(User user);
}
