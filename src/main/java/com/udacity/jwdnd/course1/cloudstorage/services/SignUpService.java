package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SignUpService {
    private UserMapper userMapper;
    private HashService hashService;
    public SignUpService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }
    @Transactional
    public int create(User userModel){

        if (userMapper.existsUsername(userModel.getUserName()) != 0) {
            return 0;
        }

        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        userModel.setSalt(encodedSalt);
        userModel.setPassword(hashService.getHashedValue(userModel.getPassword(), encodedSalt));
        return userMapper.insert(userModel);
    }
}
