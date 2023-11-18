package com.example.todoapp;

import java.io.Serializable;

public class ToDo implements Serializable { // MVC

    private  String task;
    private  String date;
    private  boolean isUrgent;
    private boolean isDone;

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public ToDo(String task, String date, boolean isUrgent, boolean isDone) {
        this.task = task;
        this.date = date;
        this.isUrgent = isUrgent;
        this.isDone = isDone;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }
}
