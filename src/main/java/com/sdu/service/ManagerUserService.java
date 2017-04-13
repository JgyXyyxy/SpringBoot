package com.sdu.service;

import com.sdu.bean.ManagerUser;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by J on 2017/3/4.
 */
public interface ManagerUserService {

    ManagerUser findByUsername(String username);
}
