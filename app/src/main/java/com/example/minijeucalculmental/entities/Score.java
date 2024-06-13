package com.example.minijeucalculmental.entities;

public class Score extends BaseEntity{
    String username;
    Integer scoreUser;
    public String getUsernameElement() {
        return username;
    }

    public void setUsernameElement(String username) {
        this.username = username;
    }

    public Integer getScoreUserElement() {
        return scoreUser;
    }

    public void setScoreUserElement(Integer scoreUser) {
        this.scoreUser = scoreUser;
    }
}
