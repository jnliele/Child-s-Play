package com.example.teachingtoddlers;

public class alphabetQuestions {

    public String questionsLvl1[] = {
            "What letter goes in the blank\n_ b c d e",
            "What letter goes in the blank\na _ c d e",
            "What letter goes in the blank\na b _ d e",
            "What letter goes in the blank\nb c _ e f",
            "What letter goes in the blank\nc d _ f g",
            "What letter goes in the blank\nd e _ g h",
            "What letter goes in the blank\ne f _ h i",
            "What letter goes in the blank\nf g _ i j",
    };

    public String questionsLvl2[] = {
            "What letter goes in the blank\n_ b c d e",
            "What letter goes in the blank\na _ c d e",
            "What letter goes in the blank\na b _ d e",
            "What letter goes in the blank\nb c _ e f",
            "What letter goes in the blank\nc d _ f g",
            "What letter goes in the blank\nd e _ g h",
            "What letter goes in the blank\ne f _ h i",
            "What letter goes in the blank\nf g _ i j",
            "What letter goes in the blank\ng h _ j k",
            "What letter goes in the blank\nh i _ k l",
            "What letter goes in the blank\ni j _ l m",
            "What letter goes in the blank\nj k _ m n",
            "What letter goes in the blank\nk l _ n o",
            "What letter goes in the blank\nl m _ o p",
            "What letter goes in the blank\nm n _ p q",
            "What letter goes in the blank\nn o _ q r"
    };
    public String questionsLvl3[] = {
            "What letter goes in the blank\n_ b c d e",
            "What letter goes in the blank\na _ c d e",
            "What letter goes in the blank\na b _ d e",
            "What letter goes in the blank\nb c _ e f",
            "What letter goes in the blank\nc d _ f g",
            "What letter goes in the blank\nd e _ g h",
            "What letter goes in the blank\ne f _ h i",
            "What letter goes in the blank\nf g _ i j",
            "What letter goes in the blank\ng h _ j k",
            "What letter goes in the blank\nh i _ k l",
            "What letter goes in the blank\ni j _ l m",
            "What letter goes in the blank\nj k _ m n",
            "What letter goes in the blank\nk l _ n o",
            "What letter goes in the blank\nl m _ o p",
            "What letter goes in the blank\nm n _ p q",
            "What letter goes in the blank\nn o _ q r",
            "What letter goes in the blank\no p _ r s",
            "What letter goes in the blank\np q _ s t",
            "What letter goes in the blank\nq r _ t u",
            "What letter goes in the blank\nr s _ u v",
            "What letter goes in the blank\ns t _ v w",
            "What letter goes in the blank\nt u _ w x",
            "What letter goes in the blank\nu v _ x y",
            "What letter goes in the blank\nv w _ y z",
            "What letter goes in the blank\nv w x _ z",
            "What letter goes in the blank\nv w x y _"
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
