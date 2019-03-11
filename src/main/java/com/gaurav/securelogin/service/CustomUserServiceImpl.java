package com.gaurav.securelogin.service;

import com.gaurav.securelogin.entity.User;
import com.gaurav.securelogin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserServiceImpl implements CustomUsersService {

    private UserRepository userRepository;
    public static String tempPassword = null;

    public CustomUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> forgotPassword(String username) {
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            return Optional.empty();
        } else {
            tempPassword=randomPasswordGenerator();
            user.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(tempPassword));
            userRepository.save(user);
            return Optional.of(user);
        }
    }

    public String randomPasswordGenerator() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 15; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
