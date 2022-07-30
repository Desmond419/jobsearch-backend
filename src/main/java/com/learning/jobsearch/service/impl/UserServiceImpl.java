package com.learning.jobsearch.service.impl;

import com.learning.jobsearch.dao.UserDao;
import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User findUserById(String id){
        return userDao.findUserById(id);
    }

    @Override
    public User findByUserEmail(String email){
        return userDao.findByUserEmail(email);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }

    @Override
    public void deleteUserById(String id){
        userDao.deleteUserById(id);
    }
}
