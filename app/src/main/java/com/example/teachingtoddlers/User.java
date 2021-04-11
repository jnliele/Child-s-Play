package com.example.teachingtoddlers;

public class User {

    public String email, fname, lname, utaID, prof, pw, childFirst, childLast, ageRange, image;

    public User(){
    }

    public User(String email, String fname, String lname, String utaID, String prof, String pw,
                String childFirst, String childLast, String ageRange, String image){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.utaID = utaID;
        this.prof = prof;
        this.pw = pw;
        this.childFirst = childFirst;
        this.childLast = childLast;
        this.ageRange = ageRange;
        this.image = image;
    }
}