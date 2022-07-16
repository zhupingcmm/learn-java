package com.mf.server.service.impl;

import com.mf.server.mapper.UserMapper;
import com.mf.server.model.User;
import com.mf.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    @Override
    public List<User> findUsers(HashMap<String, String> pageConfig) {
        return userMapper.findUsers(pageConfig);
    }
}
