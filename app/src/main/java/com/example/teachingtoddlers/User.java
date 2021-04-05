package com.example.teachingtoddlers;

public class User {

    public String email, fname, lname, utaID, prof, pw, cfname, clname, ageRange;

    public User(){
    }

    public User(String email, String fname, String lname, String utaID, String prof, String pw, String cfname, String clname, String ageRange){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.utaID = utaID;
        this.prof = prof;
        this.pw = pw;
        this.cfname = cfname;
        this.clname = clname;
        this.ageRange = ageRange;
    }
}