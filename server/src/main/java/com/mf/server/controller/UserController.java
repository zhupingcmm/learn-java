package com.mf.server.controller;

import com.mf.server.model.User;
import com.mf.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{pagesize}/{start}")
    public List<User> getUser(@PathVariable(required = false) String pagesize, @PathVariable(required = false) String start){
        HashMap<String, String> pageConfig = new HashMap<>();
        pageConfig.put("pageSize", pagesize);
        pageConfig.put("start", start);
        return userService.findUsers(pageConfig);
    }
}
