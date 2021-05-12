package com.motivation.team3.motivateme.model;

public class Note
{
    int id;
    String title,body,time,date;
    public Note(){}

    public Note(String title, String body, String time, String date) {
        this.title = title;
        this.body = body;
        this.time = time;
        this.date = date;
    }

    public Note(int id, String title, String body, String time, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
