package com.capg.task;

public class Task {

    private Integer id;
    private String name;
    private Integer taskboxId;

    public Task(Integer id, String name, Integer taskboxId) {
        this.id = id;
        this.name = name;
        this.taskboxId = taskboxId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTaskboxId() {
        return taskboxId;
    }

    public void setTaskboxId(String taskboxName) {
        if(taskboxName.equals("taskspool")) {
            this.taskboxId = 0;
        } else if(taskboxName.equals("first-user-taskbox")) {
            this.taskboxId = 1;
        } else if(taskboxName.equals("second-user-taskbox")) {
            this.taskboxId = 2;
        } else if(taskboxName.equals("third-user-taskbox")) {
            this.taskboxId = 3;
        }
    }
}
