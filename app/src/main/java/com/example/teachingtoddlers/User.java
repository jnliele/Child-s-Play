package com.example.teachingtoddlers;

public class User {

    public String email, fname, lname, utaID, prof, pw, childFirst, childLast, ageRange, image;
    // the total number of times a level is played
    public long alphabetLevelOneTotalPlay, alphabetLevelTwoTotalPlay, alphabetLevelThreeTotalPlay;
    public long countingLevelOneTotalPlay, countingLevelTwoTotalPlay, countingLevelThreeTotalPlay;
    public long additionLevelOneTotalPlay, additionLevelTwoTotalPlay, additionLevelThreeTotalPlay;

    // the total number of questions the user gets right in a level
    public long alphabetLevelOneCorrect, alphabetLevelTwoCorrect, alphabetLevelThreeCorrect;
    public long countingLevelOneCorrect, countingLevelTwoCorrect, countingLevelThreeCorrect;
    public long additionLevelOneCorrect, additionLevelTwoCorrect, additionLevelThreeCorrect;

    // the total number of questions done/attempted in a level
    public long alphabetLevelOneTotal, alphabetLevelTwoTotal, alphabetLevelThreeTotal;
    public long countingLevelOneTotal, countingLevelTwoTotal, countingLevelThreeTotal;
    public long additionLevelOneTotal, additionLevelTwoTotal, additionLevelThreeTotal;

    public User(){
    }

    // constructor to store all the information upon registration for database
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
        alphabetLevelOneTotalPlay = 0;
        alphabetLevelTwoTotalPlay = 0;
        alphabetLevelThreeTotalPlay = 0;
        countingLevelOneTotalPlay = 0;
        countingLevelTwoTotalPlay = 0;
        countingLevelThreeTotalPlay = 0;
        additionLevelOneTotalPlay = 0;
        additionLevelTwoTotalPlay = 0;
        additionLevelThreeTotalPlay = 0;
        alphabetLevelOneCorrect = 0;
        alphabetLevelTwoCorrect = 0;
        alphabetLevelThreeCorrect = 0;
        countingLevelOneCorrect = 0;
        countingLevelTwoCorrect = 0;
        countingLevelThreeCorrect = 0;
        additionLevelOneCorrect = 0;
        additionLevelTwoCorrect = 0;
        additionLevelThreeCorrect = 0;
        alphabetLevelOneTotal = 0;
        alphabetLevelTwoTotal = 0;
        alphabetLevelThreeTotal = 0;
        countingLevelOneTotal = 0;
        countingLevelTwoTotal = 0;
        countingLevelThreeTotal = 0;
        additionLevelOneTotal = 0;
        additionLevelTwoTotal = 0;
        additionLevelThreeTotal = 0;
    }
}