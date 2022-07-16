package com.mf.server.service;

import com.mf.server.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<User> findUsers(HashMap<String, String> pageConfig);
}
