package com.capg.task;

public class Task {

    private Integer id;
    private String name;
    private Integer ownersId;

    public Task(Integer id, String name, Integer ownersId) {
        this.id = id;
        this.name = name;
        this.ownersId = ownersId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOwnersId() {
        return ownersId;
    }

    public void setOwnersId(String taskOwner) {
        if(taskOwner.equals("taskspool")) {
            this.ownersId = 0;
        } else if(taskOwner.equals("first-user-taskbox")) {
            this.ownersId = 1;
        } else if(taskOwner.equals("second-user-taskbox")) {
            this.ownersId = 2;
        } else if(taskOwner.equals("third-user-taskbox")) {
            this.ownersId = 3;
        }
    }
}
