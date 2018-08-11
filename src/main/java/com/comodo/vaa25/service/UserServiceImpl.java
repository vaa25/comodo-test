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
    public void editUser(final User user) {
        if (userDao.exists(user.getId())){
            userDao.save(user);
        } else {
            log.error("Try edit user with not exist id = {}", user.getId());
            throw new IllegalArgumentException(format("User with id = {0} not exists", user.getId()));
        }
    }

    @Override
    @Transactional
    public void deleteUser(final User user) {
        if (userDao.exists(user.getId())){
            userDao.delete(user);
        } else {
            log.error("Try delete user with not exist id = {}", user.getId());
            throw new IllegalArgumentException(format("User with id = {0} not exists", user.getId()));
        }
    }
}
