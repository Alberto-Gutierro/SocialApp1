package com.example.gerard.socialapp.model;

public class Comment {
    private String name;
    private String coment;

    public Comment(){}

    public Comment(String name, String comment){
        setName(name);
        setComent(comment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
