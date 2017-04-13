package com.sdu.repositories;

import com.sdu.bean.ManagerUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by J on 2017/3/4.
 */
public interface ManagerUserRepository extends CrudRepository<ManagerUser,String> {


    ManagerUser findByPassword(String password);
}
