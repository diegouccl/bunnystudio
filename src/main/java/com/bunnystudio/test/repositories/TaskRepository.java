package com.bunnystudio.test.repositories;

import com.bunnystudio.test.models.Task;
import com.bunnystudio.test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, PagingAndSortingRepository<Task, Long> {
    List<Task> findAllByUserOrderByTaskStateDesc(User user);
}
