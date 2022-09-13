package com.learning.jobsearch.service;

import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.exception.UserNotFoundException;

public interface UserService {
    void addUser(User user);
    User findByUserEmail(String email);
    User findUserById(String id);
    void updateUser(User user);
    void deleteUserById(String id);
    void uploadImageByUserId(String id, String image);
}
