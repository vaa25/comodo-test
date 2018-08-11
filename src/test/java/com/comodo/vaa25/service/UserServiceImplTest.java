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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void saveUser() {
        final User user = new User();

        service.saveUser(user);

        verify(userDao).save(user);
    }

    @Test
    public void deleteUser() {
        final User user = new User();
        service.deleteUser(user);

        verify(userDao).delete(user);
    }
}