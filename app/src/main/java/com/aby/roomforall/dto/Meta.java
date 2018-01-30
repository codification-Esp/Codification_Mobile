package com.aby.roomforall.dto;

import java.io.Serializable;


public class Meta implements Serializable {

    private String uid;
    private String creationDate;

    public Meta() {
    }

    public Meta(String uid, String creationDate) {
        this.uid = uid;
        this.creationDate = creationDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
