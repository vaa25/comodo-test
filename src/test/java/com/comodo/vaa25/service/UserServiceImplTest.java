package com.comodo.vaa25.service;

import com.comodo.vaa25.dao.UserDao;
import com.comodo.vaa25.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    private UserService service;

    @Before
    public void setUp() {
        service = new UserServiceImpl(userDao);
    }

    @Test
    public void getUserPage() {
        final Page<User> expected = new PageImpl<>(singletonList(new User()));
        when(userDao.findAll(new PageRequest(1, 2, new Sort(new Sort.Order("id"))))).thenReturn(expected);

        final Page<User> actual = service.getUserPage(1, 2);

        assertThat(actual, is(expected));
    }

    @Test
    public void createsUser() {
        final User user = new User();

        service.createUser(user);

        verify(userDao).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenTryCreateExistingUser(){
        final User user = new User().setId(-1L);
        when(userDao.exists(-1L)).thenReturn(true);

        service.createUser(user);

        verify(userDao, never()).save(user);
    }

    @Test
    public void editsUser() {
        final User user = new User().setId(1L);
        when(userDao.exists(1L)).thenReturn(true);

        service.editUser(1L, user);

        verify(userDao).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenTryEditAbsentUser(){
        final User user = new User().setId(-1L);
        when(userDao.exists(-1L)).thenReturn(false);

        service.createUser(user);

        verify(userDao, never()).save(user);
    }

    @Test
    public void deleteUser() {
        when(userDao.exists(-1L)).thenReturn(true);
        service.deleteUser(-1L);

        verify(userDao).delete(-1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenTryDeleteAbsentUser(){
        when(userDao.exists(-1L)).thenReturn(false);

        service.deleteUser(-1L);

        verify(userDao, never()).delete(-1L);
    }
}