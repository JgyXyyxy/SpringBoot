package com.sdu.service;

import com.sdu.bean.ManagerUser;
import com.sdu.repositories.ManagerUserRepository;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by J on 2017/3/4.
 */
@Service
public class ManagerUserServiceImpl implements ManagerUserService {


    @Autowired
    ManagerUserRepository managerUserRepository;

    @Override
    public ManagerUser findByUsername(String username) {
        ManagerUser user = managerUserRepository.findOne(username);
        return user;
    }
}

