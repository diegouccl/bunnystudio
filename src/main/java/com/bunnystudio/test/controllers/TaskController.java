package com.bunnystudio.test.controllers;

import com.bunnystudio.test.models.Task;
import com.bunnystudio.test.models.User;
import com.bunnystudio.test.repositories.TaskRepository;
import com.bunnystudio.test.repositories.UserRespository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@Getter
@Setter
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRespository userRespository;



}
