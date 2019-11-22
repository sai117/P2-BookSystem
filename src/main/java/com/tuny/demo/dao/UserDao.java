package com.tuny.demo.dao;

import com.tuny.demo.domain.UserDomain;
import com.tuny.demo.util.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<UserDomain> {
    UserDomain login(String userName);
}
