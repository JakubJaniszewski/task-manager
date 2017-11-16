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
}
