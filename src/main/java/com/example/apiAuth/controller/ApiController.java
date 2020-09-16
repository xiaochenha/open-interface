package com.example.apiAuth.controller;

import com.example.apiAuth.utils.OpenInterfaceRedisUtil;
import com.example.apiAuth.utils.OpenInterfaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private OpenInterfaceRedisUtil openInterfaceRedisUtil;

    @GetMapping("/test")
    public String test(@RequestParam String magicStr,@RequestParam String token){

        boolean result = openInterfaceRedisUtil.verifyMagic(magicStr,token);
        if(!result){
            return "Warn!";
        }

        boolean verifyResult = OpenInterfaceUtil.verify(token,magicStr);
        if(!verifyResult){
            return "Warn!";
        }

        openInterfaceRedisUtil.setMagic(magicStr,token);

        return "Hello World";
    }

}
