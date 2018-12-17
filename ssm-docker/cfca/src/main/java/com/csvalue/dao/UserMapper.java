package com.csvalue.dao;

import com.csvalue.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    public List<User> queryUserList();
}
