package com.bunnystudio.test.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TaskState {

    TO_DO("TO_DO", "This task is to do"),
    DONE("DONE", "this task is done");

    private String code;
    private String description;


    TaskState(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TaskState getTaskStateEnmu(final String code){
        TaskState taskState = null;
        for(TaskState activeEnum :  values()){
            if (activeEnum.getCode().equals(code)) {
                taskState = activeEnum;
                break;
            }
        }
        return taskState;
    }


}
