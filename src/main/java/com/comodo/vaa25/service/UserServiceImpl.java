package com.comodo.vaa25.service;

import com.comodo.vaa25.dao.UserDao;
import com.comodo.vaa25.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public Page<User> getUserPage(Integer page, Integer size) {
        return userDao.findAll(new PageRequest(page, size, new Sort(new Sort.Order("id"))));
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
