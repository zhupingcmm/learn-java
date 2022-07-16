package com.mf.server.mapper;

import com.mf.server.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findUsers(HashMap<String, Integer> map);
}
