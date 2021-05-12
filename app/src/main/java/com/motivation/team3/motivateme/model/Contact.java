package com.motivation.team3.motivateme.model;

public class Contact
{
    int id;
    int count;
    String title;
    String body;
    String time;
    String date;

    public Contact(){}

    public Contact(int count, String title, String body, String time, String date)
    {
        this.count = count;
        this.title = title;
        this.body = body;
        this.time = time;
        this.date = date;
    }

    public Contact(int id, int count, String title, String body, String time, String date)
    {
        this.id = id;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
}
