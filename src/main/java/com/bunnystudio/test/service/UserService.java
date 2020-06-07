package com.bunnystudio.test.service;

import com.bunnystudio.test.models.User;
import org.springframework.data.domain.Page;


public interface UserService {

    void saveUser(User user);

    Page<User> findAllUsers(Integer pageNo, Integer itemsPerPage, String[] sortBy, String[] desc, String name);

}
