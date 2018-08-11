package com.comodo.vaa25.dao;

import com.comodo.vaa25.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to work with {@link User}.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
