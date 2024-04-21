package com.hungerhub.service;

import org.springframework.stereotype.Service;

import com.hungerhub.model.User;

@Service
public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
