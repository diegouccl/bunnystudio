package com.bunnystudio.test.service.impl;

import com.bunnystudio.test.models.User;
import com.bunnystudio.test.repositories.UserRespository;
import com.bunnystudio.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRespository userRespository;

    @Override
    public void saveUser(User user) {
        userRespository.save(user);
    }

    @Override
    public Page<User> findAllUsers(Integer pageNo, Integer itemsPerPage, String[] sortBy, String[] desc, String name) {

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if(sortBy.length > 0 && desc.length > 0){
            for(int i=0;i < sortBy.length ; i++){
                if("false".equals(desc[i])){
                    orders.add(new Sort.Order(Sort.Direction.ASC, sortBy[i]));
                } else {
                    orders.add(new Sort.Order(Sort.Direction.DESC, sortBy[i]));
                }
            }
        }
        Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(orders));
        Page<User> pagedResult = userRespository.findByFilterName(pageable, name);
        return pagedResult;
    }
}
