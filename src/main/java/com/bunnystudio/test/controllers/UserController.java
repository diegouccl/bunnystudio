package com.bunnystudio.test.controllers;


import com.bunnystudio.test.models.Task;
import com.bunnystudio.test.models.User;
import com.bunnystudio.test.repositories.TaskRepository;
import com.bunnystudio.test.repositories.UserRespository;
import com.bunnystudio.test.response.ApiResponse;
import com.bunnystudio.test.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
@Validated
@Getter
@Setter
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRespository userRespository;

    @Autowired
    UserService userService;

    @Autowired
    TaskRepository taskRepository;


    /**
     * Methos used for listing all users
     * @return List<User>
     */
    @RequestMapping(value = {"", "/", "/list"}, method = RequestMethod.GET)
    public Page<User> index(@RequestParam(defaultValue = "0", required = false) Integer pageNo,
                            @RequestParam(defaultValue = "10", required = false) Integer itemsPerPage,
                            @RequestParam(defaultValue = "id", required = false) String[] sortBy,
                            @RequestParam(defaultValue = "false",required = false) String[] desc,
                            @RequestParam(required = false) String searchName) {

        return userService.findAllUsers(pageNo, itemsPerPage, sortBy, desc, searchName);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(new ApiResponse(true, "User created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, @PathVariable("id") Long id) {
        userService.saveUser(user);
        return ResponseEntity.ok(new ApiResponse(true, "User updated"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        if (userRespository.existsById(id)) {
            userRespository.deleteById(id);
        } else {
            return new ResponseEntity(new ApiResponse(false, "User doesn't exits!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ApiResponse(true, "User deleted"));

    }

    /**
     * Method used for listing all taskFromUser
     * @return List<User>
     */
    @RequestMapping("/{idUser}/task")
    public ResponseEntity<List<Task>> index(@PathVariable("idUser") long idUser) {
        List<Task> tasks = new ArrayList<>();
        if(userRespository.existsById(idUser)){
            User user = userRespository.getOne(idUser);
            tasks = taskRepository.findAllByUserOrderByTaskStateDesc(user);
        }
        return new ResponseEntity<List<Task>>(tasks, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/{idUser}")
    public User getUser(@PathVariable("idUser") long idUser){
        User user = null;
        if(userRespository.existsById(idUser)){
            user = userRespository.findById(idUser).orElse(new User());
        }
        return user;
    }


    /**
     * Method used for listing all taskFromUser
     * @return List<User>
     */
    @PostMapping("/{idUser}/task")
    public ResponseEntity<?> saveTask(@Valid @RequestBody Task task, @PathVariable("idUser") long idUser) {
        if(userRespository.existsById(idUser)){
            User user = userRespository.getOne(idUser);
            task.setUser(user);
            taskRepository.save(task);
        }
        return ResponseEntity.ok(new ApiResponse(true, "Task created"));
    }
}
