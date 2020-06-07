package com.bunnystudio.test.models;

import java.io.Serializable;

import javax.persistence.*;

import com.bunnystudio.test.enums.TaskState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_tasks")
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Task implements Serializable  {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false) })
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(nullable = false)
    private String taskState;

    @Column(nullable = false)
    private String description;


    public TaskState getTaskState(){
        return TaskState.getTaskStateEnmu(this.taskState);
    }

    public void setTaskState(TaskState taskState){
        this.taskState = taskState != null ? taskState.getCode() : null;
    }
}
