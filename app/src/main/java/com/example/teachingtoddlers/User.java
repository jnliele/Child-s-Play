package com.example.teachingtoddlers;

public class User {

    public String email, fname, lname, utaID, prof, pw;

    public User(){
    }

    public User(String email, String fname, String lname, String utaID, String prof, String pw){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.utaID = utaID;
        this.prof = prof;
        this.pw = pw;
    }
}