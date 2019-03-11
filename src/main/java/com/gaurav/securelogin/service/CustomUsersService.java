package com.gaurav.securelogin.service;

import com.gaurav.securelogin.entity.User;

import java.util.Optional;

public interface CustomUsersService {

    Optional<User> forgotPassword(String username);
}
