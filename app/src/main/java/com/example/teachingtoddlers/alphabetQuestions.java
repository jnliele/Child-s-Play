package com.example.teachingtoddlers;

public class alphabetQuestions {

    public String questionsLvl1[] = {
            "What letter goes in the blank?\n\n_ B C D E",
            "What letter goes in the blank?\n\nA _ C D E",
            "What letter goes in the blank?\n\nA B _ D E",
            "What letter goes in the blank?\n\nB C _ E F",
            "What letter goes in the blank?\n\nC D _ F G",
            "What letter goes in the blank?\n\nD E _ G H",
            "What letter goes in the blank?\n\nE F _ H I",
            "What letter goes in the blank?\n\nF G _ I J",
    };

    public String questionsLvl2[] = {
            "What letter goes in the blank?\n\n_ B C D E",
            "What letter goes in the blank?\n\nA _ C D E",
            "What letter goes in the blank?\n\nA B _ D E",
            "What letter goes in the blank?\n\nB C _ E F",
            "What letter goes in the blank?\n\nC D _ F G",
            "What letter goes in the blank?\n\nD E _ G H",
            "What letter goes in the blank?\n\nE F _ H I",
            "What letter goes in the blank?\n\nF G _ I J",
            "What letter goes in the blank?\n\nG H _ J K",
            "What letter goes in the blank?\n\nH I _ K L",
            "What letter goes in the blank?\n\nI J _ L M",
            "What letter goes in the blank?\n\nJ K _ M N",
            "What letter goes in the blank?\n\nK L _ N O",
            "What letter goes in the blank?\n\nL M _ O P",
            "What letter goes in the blank?\n\nM N _ P Q",
            "What letter goes in the blank?\n\nN O _ Q R"
    };
    public String questionsLvl3[] = {
            "What letter goes in the blank?\n\n_ B C D E",
            "What letter goes in the blank?\n\nA _ C D E",
            "What letter goes in the blank?\n\nA B _ D E",
            "What letter goes in the blank?\n\nB C _ E F",
            "What letter goes in the blank?\n\nC D _ F G",
            "What letter goes in the blank?\n\nD E _ G H",
            "What letter goes in the blank?\n\nE F _ H I",
            "What letter goes in the blank?\n\nF G _ I J",
            "What letter goes in the blank?\n\nG H _ J K",
            "What letter goes in the blank?\n\nH I _ K L",
            "What letter goes in the blank?\n\nI J _ L M",
            "What letter goes in the blank?\n\nJ K _ M N",
            "What letter goes in the blank?\n\nK L _ N O",
            "What letter goes in the blank?\n\nL M _ O P",
            "What letter goes in the blank?\n\nM N _ P Q",
            "What letter goes in the blank?\n\nN O _ Q R",
            "What letter goes in the blank?\n\nO P _ R S",
            "What letter goes in the blank?\n\nP Q _ S T",
            "What letter goes in the blank?\n\nQ R _ T U",
            "What letter goes in the blank?\n\nR S _ U V",
            "What letter goes in the blank?\n\nS T _ V W",
            "What letter goes in the blank?\n\nT U _ W X",
            "What letter goes in the blank?\n\nU V _ X Y",
            "What letter goes in the blank?\n\nV W _ Y Z",
            "What letter goes in the blank?\n\nV W X _ Z",
            "What letter goes in the blank?\n\nV W X Y _"
    };

    private String choicesLvl1[][] = {
            {"f", "a", "g", "e"},
            {"e", "r", "w", "b"},
            {"c", "t", "s", "f"},
            {"h", "d", "g", "e"},
            {"s", "e", "v", "c"},
            {"k", "e", "d", "f"},
            {"h", "y", "g", "q"},
            {"s", "h", "r", "f"}
    };

    private String choicesLvl2[][] = {
            {"f", "a", "g", "e"},
            {"e", "r", "w", "b"},
            {"c", "t", "s", "f"},
            {"h", "d", "g", "e"},
            {"s", "e", "v", "c"},
            {"k", "e", "d", "f"},
            {"h", "y", "g", "q"},
            {"s", "h", "r", "f"},
            {"y", "e", "i", "f"},
            {"j", "y", "g", "n"},
            {"n", "k", "f", "c"},
            {"i", "j", "s", "l"},
            {"i", "f", "e", "m"},
            {"v", "j", "n", "y"},
            {"o", "w", "h", "r"},
            {"j", "p", "s", "f"}
    };

    private String choicesLvl3[][] = {
            {"f", "a", "g", "e"},
            {"e", "r", "w", "b"},
            {"c", "t", "s", "f"},
            {"h", "d", "g", "e"},
            {"s", "e", "v", "c"},
            {"k", "e", "d", "f"},
            {"h", "y", "g", "q"},
            {"s", "h", "r", "f"},
            {"y", "e", "i", "f"},
            {"j", "y", "g", "n"},
            {"n", "k", "f", "c"},
            {"i", "j", "s", "l"},
            {"i", "f", "e", "m"},
            {"v", "j", "n", "y"},
            {"o", "w", "h", "r"},
            {"j", "p", "s", "f"},
            {"s", "q", "w", "t"},
            {"r", "f", "n", "g"},
            {"a", "w", "s", "k"},
            {"j", "y", "u", "t"},
            {"t", "u", "e", "b"},
            {"i", "f", "v", "h"},
            {"w", "o", "g", "z"},
            {"u", "x", "k", "z"},
            {"e", "r", "c", "y"},
            {"f", "z", "k", "p"}
    };

    private String answersLvl1[] = {"a","b","c","d","e","f","g","h"};
    private String answersLvl2[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
    private String answersLvl3[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    //Level 1
    public String getQuestionLvl1(int a){
        String question = questionsLvl1[a];
        return question;
    }

    public String getChoice1Lvl1(int a){
        String choice = choicesLvl1[a][0];
        return choice;
    }

    public String getChoice2Lvl1(int a){
        String choice = choicesLvl1[a][1];
        return choice;
    }

    public String getChoice3Lvl1(int a){
        String choice = choicesLvl1[a][2];
        return choice;
    }

    public String getChoice4Lvl1(int a){
        String choice = choicesLvl1[a][3];
        return choice;
    }

    public String correctLvl1(int a){
        String answer = answersLvl1[a];
        return answer;
    }

    //Level 2
    public String getQuestionLvl2(int a){
        String question = questionsLvl2[a];
        return question;
    }

    public String getChoice1Lvl2(int a){
        String choice = choicesLvl2[a][0];
        return choice;
    }

    public String getChoice2Lvl2(int a){
        String choice = choicesLvl2[a][1];
        return choice;
    }

    public String getChoice3Lvl2(int a){
        String choice = choicesLvl2[a][2];
        return choice;
    }

    public String getChoice4Lvl2(int a){
        String choice = choicesLvl2[a][3];
        return choice;
    }

    public String correctLvl2(int a){
        String answer = answersLvl2[a];
        return answer;
    }

    //Level 3
    public String getQuestionLvl3(int a){
        String question = questionsLvl3[a];
        return question;
    }

    public String getChoice1Lvl3(int a){
        String choice = choicesLvl3[a][0];
        return choice;
    }

    public String getChoice2Lvl3(int a){
        String choice = choicesLvl3[a][1];
        return choice;
    }

    public String getChoice3Lvl3(int a){
        String choice = choicesLvl3[a][2];
        return choice;
    }

    public String getChoice4Lvl3(int a){
        String choice = choicesLvl3[a][3];
        return choice;
    }

    public String correctLvl3(int a){
        String answer = answersLvl3[a];
        return answer;
    }

}
