package com.bunnystudio.test.controllers;

import com.bunnystudio.test.models.Task;
import com.bunnystudio.test.models.User;
import com.bunnystudio.test.repositories.TaskRepository;
import com.bunnystudio.test.repositories.UserRespository;
import com.bunnystudio.test.response.ApiResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     *  Method to delete a Task
     * @param id
     * @return ResponseEntity with status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {

        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            return new ResponseEntity(new ApiResponse(false, "Task doesn't exits!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ApiResponse(true, "Task deleted"));

    }

    /**
     * Method to Update a task
     * @param task
     * @param id
     * @return ResponseEntity with status
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@Valid @RequestBody Task task, @PathVariable("id") Long id) {
        taskRepository.save(task);
        return ResponseEntity.ok(new ApiResponse(true, "Task updated"));
    }


}
