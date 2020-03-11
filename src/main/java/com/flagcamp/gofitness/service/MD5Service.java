package com.flagcamp.gofitness.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class MD5Service {

    public String md5(String email, String password) {
        return DigestUtils.md5DigestAsHex(email.getBytes()) + DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
