package com.comodo.vaa25.service;

import com.comodo.vaa25.dao.UserDao;
import com.comodo.vaa25.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public Page<User> getUserPage(final Integer page, final Integer size) {
        return userDao.findAll(new PageRequest(page, size, new Sort(new Sort.Order("id"))));
    }

    @Override
    @Transactional
    public void createUser(final User user) {
        if (user.getId() == null){
            userDao.save(user);
        } else {
            log.error("Try create user with not null id = {}", user.getId());
            throw new IllegalArgumentException("User should have null id");
        }
    }

    @Override
    @Transactional
    public void editUser(final Long userId, final User user) {
        if (userDao.exists(userId)){
            userDao.save(user.setId(userId));
        } else {
            log.error("Try edit user with not exist id = {}", userId);
            throw new IllegalArgumentException(format("User with id = {0} not exists", userId));
        }
    }

    @Override
    @Transactional
    public void deleteUser(final Long userId) {
        if (userDao.exists(userId)){
            userDao.delete(userId);
        } else {
            log.error("Try delete user with not exist id = {}", userId);
            throw new IllegalArgumentException(format("User with id = {0} not exists", userId));
        }
    }
}
