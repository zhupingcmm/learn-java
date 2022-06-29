package com.mf.server.controller;

import com.mf.common.api.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    @GetMapping("/test")
    public Response<String> test() {
        Response<String> response = new Response<>();
        response.setResult("zp");
        response.setStatus(true);
        return response;
    }
}
